package com.otsi.tconnect.ms.fup.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.otsi.tconnect.ms.fup.catalog.entity.FubTemplate;
import com.otsi.tconnect.ms.fup.catalog.entity.ProductOffering;
import com.otsi.tconnect.ms.fup.catalog.entity.SubscriptionAgreement;
import com.otsi.tconnect.ms.fup.catalog.repository.FubTemplateRepository;
import com.otsi.tconnect.ms.fup.catalog.repository.ProductOfferingRepository;
import com.otsi.tconnect.ms.fup.catalog.repository.SubscriptionAgreementRepository;
import com.otsi.tconnect.ms.fup.customer.entity.Customer;
import com.otsi.tconnect.ms.fup.customer.repository.CustomerRepository;
import com.otsi.tconnect.ms.fup.dto.FUPDetailUsageResponse;
import com.otsi.tconnect.ms.fup.dto.FUPUsageResponse;
import com.otsi.tconnect.ms.fup.dto.Usage;
import com.otsi.tconnect.ms.fup.dto.WebSocketResponse;
import com.otsi.tconnect.ms.fup.fup.entity.FUPRecord;
import com.otsi.tconnect.ms.fup.fup.entity.FUPUsageNotification;
import com.otsi.tconnect.ms.fup.fup.entity.FUPUsageNotificationID;
import com.otsi.tconnect.ms.fup.fup.repository.FUPRecordRepository;
import com.otsi.tconnect.ms.fup.fup.repository.FUPUsageNotificationRepository;
import com.otsi.tconnect.ms.fup.util.EmailUtils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FUPService {

	@Autowired
	private FUPRecordRepository fUPRecordRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	SubscriptionAgreementRepository subscriptionAgreementRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	ProductOfferingRepository productOfferingRepository;

	@Autowired
	FubTemplateRepository fubTemplateRepository;

	@Autowired
	FUPUsageNotificationRepository fUPUsageNotificationRepository;

	@Autowired
	private NotificationService notificationService;

	@Value("${file.crd.directory.path}")
	private String directoryPath;

	public static final String MSG = "Dear User, Your Usage is reached to ";
	public static final String SUBJECT = "Tconnect - USAGE";
	DecimalFormat df = new DecimalFormat("0.0");

	@Transactional
	public FUPRecord getCdrRecord(String line) {
		String[] parts = line.split(",");
		FUPRecord cdrRecord = new FUPRecord();
		if (parts.length >= 16) {
			cdrRecord.setClient(parts[0]);
			cdrRecord.setDevice(parts[1]);
			cdrRecord.setService(Integer.parseInt(parts[2]));
			cdrRecord.setNasIpAddress(parts[3]);
			cdrRecord.setFramedIpAddress(parts[4]);
			cdrRecord.setFramedIpv6Prefix(parts[5]);
			cdrRecord.setAcctStatusType(parts[6]);
			cdrRecord.setAcctInputOctets(Long.parseLong(parts[7]));
			cdrRecord.setAcctOutputOctets(Long.parseLong(parts[8]));
			cdrRecord.setAcctInputGigawords(Integer.parseInt(parts[9]));
			cdrRecord.setAcctOutputGigawords(Integer.parseInt(parts[10]));
			cdrRecord.setAcctSessionTime(Integer.parseInt(parts[11]));
			cdrRecord.setEventTimestamp(Long.parseLong(parts[12]));
			cdrRecord.setNasPortId(parts[13]);
			cdrRecord.setAcctSessionId(parts[14]);
			cdrRecord.setAcctDelayTime(Integer.parseInt(parts[15]));
		}
		if (parts.length >= 17) {
			cdrRecord.setAcctTerminateCause(parts[16]);
		}
		return cdrRecord;
	}

	@Transactional
	public void truncateTable() {
		entityManager.createNativeQuery("TRUNCATE TABLE fup.fup_record").executeUpdate();
	}

	public void calculateFUPUsage() {

		readAllCRDFilesAndStoreToDB();

		Map<String, List<FUPRecord>> fupRecordListGrpByDeviceMap = new HashMap<>();
		List<String> deviceList = fUPRecordRepository.getAllDeviceIds();
		if (null != deviceList && deviceList.size() > 0) {
			for (String deviceId : deviceList) {
				LocalDate now = LocalDate.now();
				long endTimestamp = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli()/1000;
				LocalDateTime firstDayStart = LocalDateTime.of(now.getYear(), now.getMonth(), 1, 0, 0, 0, 0);
				long startTimestamp = firstDayStart.toInstant(ZoneOffset.UTC).toEpochMilli();
				List<FUPRecord> fupRecordList = fUPRecordRepository.findRecordsByDeviceIdAndTimeInRange(deviceId,
						startTimestamp, endTimestamp);
				fupRecordListGrpByDeviceMap.put(deviceId, fupRecordList);
			}
		}

		for (Map.Entry<String, List<FUPRecord>> entry : fupRecordListGrpByDeviceMap.entrySet()) {
			System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
			String device = entry.getKey();
			List<FUPRecord> fupList = entry.getValue();
			try {
				if (device != null) {
					SubscriptionAgreement subscriptionAgreement = subscriptionAgreementRepository
							.findByMacAddress(device);
					if (null != subscriptionAgreement) {
						String email = null;
						String custId = subscriptionAgreement.getCustId();
						Long productOfferingId = subscriptionAgreement.getProductOfferingId();

						Optional<Customer> optionalCustomer = customerRepository.findByCustNo(custId);
						if (optionalCustomer.isPresent()) {
							email = optionalCustomer.get().getEmailId();
							log.info("email " + email + " for MAC " + device);
						}
						if (productOfferingId != null && (custId != null || email != null)) {
							Optional<ProductOffering> optionalProductOffering = productOfferingRepository
									.findById(productOfferingId);
							if (optionalProductOffering.isPresent()) {
								ProductOffering productOffering = optionalProductOffering.get();
								Long fupTemplateId = productOffering.getFupTemplateId();
								FubTemplate fubTemplate = null;
								if (fupTemplateId != null) {
									Optional<FubTemplate> optinalFubTemplate = fubTemplateRepository
											.findById(fupTemplateId);
									if (optinalFubTemplate.isPresent()) {
										fubTemplate = optinalFubTemplate.get();
									}
								}
								if (fubTemplate == null) {
									Optional<FubTemplate> defaultOptinalFubTemplate = fubTemplateRepository
											.findOne(Boolean.TRUE);
									if (defaultOptinalFubTemplate.isPresent()) {
										fubTemplate = defaultOptinalFubTemplate.get();
									}
								}
								if (fubTemplate != null) {
									String dataLimit = fubTemplate.getDataLimit();
									long totalPlanDataInBytes = convertDataGBtoBytes(dataLimit);
									long totalUsed = calculateUsage(fupList);
									calculateUsageAndSendEmail(device, totalPlanDataInBytes, totalUsed, email, custId,
											fubTemplate, LocalDate.now().getMonthValue(), LocalDate.now().getYear());
								} else {
									log.error("No FUP template found for" + device);
								}
							} else {
								log.error("No product offering found for" + device);
							}
						} else {
							log.error("Calulation  stoped for the MAC address :  " + device
									+ "because of custId or email not found or product offering id is missed ");
						}
					} else {
						log.error("No data found for the MAC address" + device);
					}
				}
			} catch (Exception e) {
				log.error("Exception occured while calulating fup for  " + device + "error " + e.getMessage());
			}
		}
	}

	private void readAllCRDFilesAndStoreToDB() {

		try {
			File directory = new File(directoryPath);
			File[] files = directory.listFiles((dir, name) -> name.endsWith(".cdr"));
			if (files != null) {
				for (File file : files) {
					try {
						if (file.getName().startsWith("Processed_"))
							continue;
						processFile(file);
						renameFileWithTimestamp(file);
					} catch (Exception e) {
						System.err.println("Error processing file " + file.getName() + ": " + e.getMessage());
					}
				}
			} else {
				log.info("No CDR files found in the directory.");
			}

		} catch (Exception e) {
			log.info("Exception occured while reading CDR");
		}

	}

	private void renameFileWithTimestamp(File file) {
		String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String newFileName = "Processed_" + file.getName().replace(".cdr", "_" + timestamp + ".cdr");
		File renamedFile = new File(file.getParent(), newFileName);
		if (file.renameTo(renamedFile)) {
			log.info("File renamed to: " + renamedFile.getName());
		} else {
			log.error("Failed to rename file: " + file.getName());
		}

	}

	private void processFile(File file) {
		List<FUPRecord> fupRecordList = new ArrayList<FUPRecord>();
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = reader.readLine()) != null) {
				FUPRecord fUPRecord = getCdrRecord(line);
				fupRecordList.add(fUPRecord);
			}
		} catch (IOException e) {
			log.error("error while processing file" + file.getName());
			e.printStackTrace();
		}

		if (fupRecordList.size() > 0) {
			fUPRecordRepository.saveAllAndFlush(fupRecordList);
		}
	}

	private void calculateUsageAndSendEmail(String deviceId, long totalPlanDataInBytes, long totalUsed, String email,
			String custId, FubTemplate fubTemplate, Integer month, Integer year) {
		double usedPct = ((double) totalUsed / (double) totalPlanDataInBytes) * 100.00;
		Gson gson = new Gson();
		if (email != null || custId != null) {
			if (usedPct > (double) fubTemplate.getThirdThreshold()) {
				FUPUsageNotification fUPUsageNotification = new FUPUsageNotification();
				FUPUsageNotificationID fUPUsageNotificationID = new FUPUsageNotificationID(deviceId, month, year,
						fubTemplate.getThirdThreshold());
				fUPUsageNotification.setStatus("Success");
				fUPUsageNotification.setId(fUPUsageNotificationID);
				Optional<FUPUsageNotification> fupNotification = fUPUsageNotificationRepository
						.findById(fUPUsageNotificationID);
				if (!fupNotification.isPresent()) {
					if (email != null) {
						EmailUtils.sendEmail(email, SUBJECT, MSG + fubTemplate.getThirdThreshold() + "%");
						fUPUsageNotificationRepository.save(fUPUsageNotification);
					}
					if (custId != null) {
						WebSocketResponse webSocketResponse = new WebSocketResponse(custId,
								MSG + fubTemplate.getThirdThreshold() + "%");
						String jsonStr = gson.toJson(webSocketResponse);
						notificationService.notifyUser(custId, jsonStr);
					}
				}
			} else if (usedPct > (double) fubTemplate.getSecondThreshold()) {
				FUPUsageNotification fUPUsageNotification = new FUPUsageNotification();
				FUPUsageNotificationID fUPUsageNotificationID = new FUPUsageNotificationID(deviceId, month, year,
						fubTemplate.getSecondThreshold());
				fUPUsageNotification.setStatus("Success");
				fUPUsageNotification.setId(fUPUsageNotificationID);
				Optional<FUPUsageNotification> fupNotification = fUPUsageNotificationRepository
						.findById(fUPUsageNotificationID);
				if (!fupNotification.isPresent()) {
					if (email != null) {
						EmailUtils.sendEmail(email, SUBJECT, MSG + fubTemplate.getSecondThreshold() + "%");
					}
					if (custId != null) {
						WebSocketResponse webSocketResponse = new WebSocketResponse(custId,
								MSG + fubTemplate.getSecondThreshold() + "%");
						String jsonStr = gson.toJson(webSocketResponse);
						notificationService.notifyUser(custId, jsonStr);
					}
				}
			} else if (usedPct > (double) fubTemplate.getFirstThreshold()) {
				FUPUsageNotification fUPUsageNotification = new FUPUsageNotification();
				FUPUsageNotificationID fUPUsageNotificationID = new FUPUsageNotificationID(deviceId, month, year,
						fubTemplate.getFirstThreshold());
				fUPUsageNotification.setStatus("Success");
				fUPUsageNotification.setId(fUPUsageNotificationID);
				Optional<FUPUsageNotification> fupNotification = fUPUsageNotificationRepository
						.findById(fUPUsageNotificationID);
				if (!fupNotification.isPresent()) {
					if (email != null) {
						EmailUtils.sendEmail(email, SUBJECT, MSG + fubTemplate.getFirstThreshold() + "%");
					}
					if (custId != null) {
						WebSocketResponse webSocketResponse = new WebSocketResponse(custId,
								MSG + fubTemplate.getFirstThreshold() + "%");
						String jsonStr = gson.toJson(webSocketResponse);
						notificationService.notifyUser(custId, jsonStr);
					}
				}
			}
		}
	}

	private long calculateUsage(List<FUPRecord> fupList) {
		long totalUsed = 0;
		if (fupList != null && fupList.size() > 0) {
			for (FUPRecord fUPRecord : fupList) {
				totalUsed += ((1L << 32) * fUPRecord.getAcctInputGigawords()) + fUPRecord.getAcctInputOctets();
				totalUsed += ((1L << 32) * fUPRecord.getAcctOutputGigawords()) + fUPRecord.getAcctOutputOctets();
			}
		}
		return totalUsed;
	}

	private long convertDataGBtoBytes(String dataLimit) {
		Integer dataLimitInGB = Integer.parseInt(dataLimit);
		long dataLimitInMB = dataLimitInGB * 1024;
		long dataLimitInKB = dataLimitInMB * 1024;
		long dataLimitInBytes = dataLimitInKB * 1024;
		return dataLimitInBytes;
	}

	public void sendEmail2() {
		try {
			EmailUtils.sendEmail("narchintha@gmail.com", "FUP", "FUP USAGE");
			log.info("Email Successfully Sent");
		} catch (Exception e) {
			log.error("Error occured while sending email");
		}

	}

	public FUPUsageResponse getCurrentUsage(String device) {
		
		FUPUsageResponse fUPUsageResponse = new FUPUsageResponse();
		fUPUsageResponse.setDeviceId(device);
		double currentUsage = 0.0;
		LocalDate now = LocalDate.now();
		long endTimestamp = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli()/1000;
		LocalDateTime firstDayStart = LocalDateTime.of(now.getYear(), now.getMonth(), 1, 0, 0, 0, 0);
		long startTimestamp = firstDayStart.toInstant(ZoneOffset.UTC).toEpochMilli()/1000;
		List<FUPRecord> fupRecordList = fUPRecordRepository.findRecordsByDeviceIdAndTimeInRange(device, startTimestamp,
				endTimestamp);

		try {
			if (device != null) {
				SubscriptionAgreement subscriptionAgreement = subscriptionAgreementRepository.findByMacAddress(device);
				if (null != subscriptionAgreement) {
					String email = null;
					String custId = subscriptionAgreement.getCustId();
					Long productOfferingId = subscriptionAgreement.getProductOfferingId();

					Optional<Customer> optionalCustomer = customerRepository.findByCustNo(custId);
					if (optionalCustomer.isPresent()) {
						email = optionalCustomer.get().getEmailId();
						log.info("email " + email + " for MAC " + device);
					}
					if (productOfferingId != null && (custId != null || email != null)) {
						Optional<ProductOffering> optionalProductOffering = productOfferingRepository
								.findById(productOfferingId);
						if (optionalProductOffering.isPresent()) {
							ProductOffering productOffering = optionalProductOffering.get();
							Long fupTemplateId = productOffering.getFupTemplateId();
							FubTemplate fubTemplate = null;
							if (fupTemplateId != null) {
								Optional<FubTemplate> optinalFubTemplate = fubTemplateRepository
										.findById(fupTemplateId);
								if (optinalFubTemplate.isPresent()) {
									fubTemplate = optinalFubTemplate.get();
								}
							}
							if (fubTemplate == null) {
								Optional<FubTemplate> defaultOptinalFubTemplate = fubTemplateRepository
										.findOne(Boolean.TRUE);
								if (defaultOptinalFubTemplate.isPresent()) {
									fubTemplate = defaultOptinalFubTemplate.get();
								}
							}
							if (fubTemplate != null) {
								String dataLimit = fubTemplate.getDataLimit();
								long totalPlanDataInBytes = convertDataGBtoBytes(dataLimit);
								fUPUsageResponse.setTotalUsage(bytesToGigabytes(totalPlanDataInBytes));
								long totalUsed = calculateUsage(fupRecordList);
								fUPUsageResponse.setCurrentUsage(bytesToGigabytes(totalUsed));
								currentUsage = ((double) totalUsed / (double) totalPlanDataInBytes) * 100.00;
							} else {
								log.error("No FUP template found for" + device);
							}
						} else {
							log.error("No product offering found for" + device);
						}
					} else {
						log.error("Calulation  stoped for the MAC address :  " + device
								+ " because of custId or email not found or product offering id is missed ");
					}
				} else {
					log.error("No data found for the MAC address" + device);
				}
			}
		} catch (Exception e) {
			log.error("Exception occured while calulating fup for  " + device + "error " + e.getMessage());
			fUPUsageResponse.setCurrentUsagePct(df.format(currentUsage) + "%");
			return fUPUsageResponse;
		}
		fUPUsageResponse.setCurrentUsagePct(df.format(currentUsage) + "%");
		return fUPUsageResponse;
	}

	public static double bytesToGigabytes(long bytes) {
		final long BYTES_IN_ONE_GB = 1024L * 1024L * 1024L;
		return (double) bytes / BYTES_IN_ONE_GB;
	}

	public FUPDetailUsageResponse getDetailsCurrentUsage(String deviceId) {
		FUPDetailUsageResponse fUPDetailUsageResponse = new FUPDetailUsageResponse();
		List<Usage> usageList = new ArrayList<Usage>();
		double totalUsage = 0.00;
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime startOfMonth = now.withDayOfMonth(1);
		LocalDateTime endOfMonth = now.withDayOfMonth(now.getDayOfMonth());
		LocalDateTime currentDay = startOfMonth;
		while (!currentDay.isAfter(endOfMonth)) {
			LocalDateTime startTime = LocalDateTime.of(currentDay.getYear(), currentDay.getMonth(),
					currentDay.getDayOfMonth(), 0, 0, 0, 0);
			LocalDateTime endTime = LocalDateTime.of(currentDay.getYear(), currentDay.getMonth(),
					currentDay.getDayOfMonth(), 23, 59, 59, 999999999);
			long endTimestamp = endTime.toInstant(ZoneOffset.UTC).toEpochMilli()/1000;
			long startTimestamp = startTime.toInstant(ZoneOffset.UTC).toEpochMilli()/1000;
			List<FUPRecord> fupRecordList = fUPRecordRepository.findRecordsByDeviceIdAndTimeInRange(deviceId,
					startTimestamp, endTimestamp);
			Usage usage = new Usage();
			usage.setDate(startTime.toLocalDate());
			calculateUploadAndDownload(fupRecordList, usage, usageList);
			totalUsage+=(usage.getDownLoadUsage()+usage.getUploadUsage());
			currentDay = currentDay.plusDays(1);
		}
		fUPDetailUsageResponse.setUsageList(usageList);
		return fUPDetailUsageResponse;
	}

	private void calculateUploadAndDownload(List<FUPRecord> fupRecordList, Usage usage, List<Usage> usageList) {
		DecimalFormat df = new DecimalFormat("0.0");
		long uploadUsage = 0;
		long downloadUsage = 0;
		if (fupRecordList != null && fupRecordList.size() > 0) {
			for (FUPRecord fUPRecord : fupRecordList) {
				downloadUsage += ((1L << 32) * fUPRecord.getAcctInputGigawords()) + fUPRecord.getAcctInputOctets();
				uploadUsage += ((1L << 32) * fUPRecord.getAcctOutputGigawords()) + fUPRecord.getAcctOutputOctets();
			}
		}
		usage.setUploadUsage(bytesToMB(uploadUsage));
		usage.setDownLoadUsage(bytesToMB(downloadUsage));
		usage.setUploadUsageStr(df.format(bytesToMB(uploadUsage)));
		usage.setDownLoadUsageStr(df.format(bytesToMB(downloadUsage)));
		usage.setTotalUsage(usage.getUploadUsage()+usage.getDownLoadUsage());
		usage.setTotalUsageStr(df.format(usage.getUploadUsage()+usage.getDownLoadUsage()));
		usageList.add(usage);
	}

	public static double bytesToMB(long bytes) {
		return bytes / (1024.0 * 1024.0);
	}

	public void saveAll(List<FUPRecord> crdRecordList) {
		fUPRecordRepository.saveAllAndFlush(crdRecordList);
	}

	public void testWebSocket(String custId) {
		WebSocketResponse webSocketResponse = new WebSocketResponse(custId, MSG + " 90%");
		Gson gson = new Gson();
		String jsonStr = gson.toJson(webSocketResponse);
		notificationService.notifyUser(custId, jsonStr);
	}

}
