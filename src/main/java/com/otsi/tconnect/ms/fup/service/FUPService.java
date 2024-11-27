package com.otsi.tconnect.ms.fup.service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.otsi.tconnect.ms.fup.fup.repository.FUPRecordRepository;
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
	private NotificationService notificationService;

	@Transactional
	public FUPRecord saveCdrRecord(String line) {
		String[] parts = line.split(";");
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

		Map<String, List<FUPRecord>> fupRecordListGrpByDeviceMap = new HashMap<>();
		List<String> deviceList = fUPRecordRepository.getAllDeviceIds();
		if (null != deviceList && deviceList.size() > 0) {
			for (String deviceId : deviceList) {
				LocalDate now = LocalDate.now();
				long endTimestamp = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
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
									calculateUsageAndSendEmail(totalPlanDataInBytes, totalUsed, email, custId,
											fubTemplate);
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

	private void calculateUsageAndSendEmail(long totalPlanDataInBytes, long totalUsed, String email, String custId,
			FubTemplate fubTemplate) {
		double usedPct = ((double) totalUsed / (double) totalPlanDataInBytes) * 100.00;
		Gson gson = new Gson();
		if (email != null) {
			if (usedPct > (double) fubTemplate.getThirdThreshold()) {
				EmailUtils.sendEmail(email, "Tconnect - USAGE",
						"Dear User, Your Usage is reached to " + fubTemplate.getThirdThreshold() + "%");
				if (custId != null) {
					WebSocketResponse webSocketResponse = new WebSocketResponse(custId,
							"Dear User, Your Usage is reached to " + fubTemplate.getThirdThreshold() + "%");
					String jsonStr = gson.toJson(webSocketResponse);
					notificationService.notifyUser(custId, jsonStr);
				}

			} else if (usedPct > (double) fubTemplate.getSecondThreshold()) {
				EmailUtils.sendEmail(email, "Tconnect - USAGE",
						"Dear User, Your Usage is reached to " + fubTemplate.getSecondThreshold() + "%");
				if (custId != null) {
					WebSocketResponse webSocketResponse = new WebSocketResponse(custId,
							"Dear User, Your Usage is reached to " + fubTemplate.getSecondThreshold() + "%");
					String jsonStr = gson.toJson(webSocketResponse);
					notificationService.notifyUser(custId, jsonStr);
				}
			} else if (usedPct > (double) fubTemplate.getFirstThreshold()) {
				EmailUtils.sendEmail(email, "Tconnect - USAGE",
						"Dear User, Your Usage is reached to " + fubTemplate.getFirstThreshold() + "%");
				if (custId != null) {
					WebSocketResponse webSocketResponse = new WebSocketResponse(custId,
							"Dear User, Your Usage is reached to " + fubTemplate.getFirstThreshold() + "%");
					String jsonStr = gson.toJson(webSocketResponse);
					notificationService.notifyUser(custId, jsonStr);
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
		DecimalFormat df = new DecimalFormat("#.##");
		FUPUsageResponse fUPUsageResponse = new FUPUsageResponse();
		fUPUsageResponse.setDeviceId(device);
		double currentUsage = 0.0;
		LocalDate now = LocalDate.now();
		long endTimestamp = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
		LocalDateTime firstDayStart = LocalDateTime.of(now.getYear(), now.getMonth(), 1, 0, 0, 0, 0);
		long startTimestamp = firstDayStart.toInstant(ZoneOffset.UTC).toEpochMilli();
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
								+ "because of custId or email not found or product offering id is missed ");
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
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime startOfMonth = now.withDayOfMonth(1);
		LocalDateTime endOfMonth = now.withDayOfMonth(now.getDayOfMonth());
		LocalDateTime currentDay = startOfMonth;
		while (!currentDay.isAfter(endOfMonth)) {
			LocalDateTime startTime = LocalDateTime.of(currentDay.getYear(), currentDay.getMonth(),
					currentDay.getDayOfMonth(), 0, 0, 0, 0);
			LocalDateTime endTime = LocalDateTime.of(currentDay.getYear(), currentDay.getMonth(),
					currentDay.getDayOfMonth(), 23, 59, 59, 999);
			long endTimestamp = endTime.toInstant(ZoneOffset.UTC).toEpochMilli();
			long startTimestamp = startTime.toInstant(ZoneOffset.UTC).toEpochMilli();
			List<FUPRecord> fupRecordList = fUPRecordRepository.findRecordsByDeviceIdAndTimeInRange(deviceId,
					startTimestamp, endTimestamp);
			Usage usage = new Usage();
			usage.setDate(startTime.toLocalDate());
			calculateUploadAndDownload(fupRecordList, usage, usageList);
			currentDay = currentDay.plusDays(1);
		}
		fUPDetailUsageResponse.setUsageList(usageList);
		return fUPDetailUsageResponse;
	}

	private void calculateUploadAndDownload(List<FUPRecord> fupRecordList, Usage usage, List<Usage> usageList) {
		long uploadUsage = 0;
		long downloadUsage = 0;
		if (fupRecordList != null && fupRecordList.size() > 0) {
			for (FUPRecord fUPRecord : fupRecordList) {
				uploadUsage += ((1L << 32) * fUPRecord.getAcctInputGigawords()) + fUPRecord.getAcctInputOctets();
				downloadUsage += ((1L << 32) * fUPRecord.getAcctOutputGigawords()) + fUPRecord.getAcctOutputOctets();
			}
		}
		usage.setUploadUsage(bytesToMB(uploadUsage));
		usage.setDownLoadUsage(bytesToMB(downloadUsage));
		usageList.add(usage);
	}

	public static double bytesToMB(long bytes) {
		return bytes / (1024.0 * 1024.0);
	}

	public void saveAll(List<FUPRecord> crdRecordList) {
		fUPRecordRepository.saveAllAndFlush(crdRecordList);
	}

	public void testWebSocket(String custId) {
		WebSocketResponse webSocketResponse = new WebSocketResponse(custId, "Dear User, Your Usage is reached to 90%");
		Gson gson = new Gson();
		String jsonStr = gson.toJson(webSocketResponse);
		notificationService.notifyUser(custId, jsonStr);
	}

}
