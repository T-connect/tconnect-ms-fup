package com.otsi.tconnect.ms.fup.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otsi.tconnect.ms.fup.catalog.entity.SubscriptionAgreement;
import com.otsi.tconnect.ms.fup.catalog.repository.SubscriptionAgreementRepository;
import com.otsi.tconnect.ms.fup.customer.entity.Customer;
import com.otsi.tconnect.ms.fup.customer.repository.CustomerRepository;
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

	@Transactional
	public void saveCdrRecord(String line) {
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
		fUPRecordRepository.save(cdrRecord);
	}

	@Transactional
	public void truncateTable() {
		entityManager.createNativeQuery("TRUNCATE TABLE fup.fup_record").executeUpdate();
	}

	public void calculateFUPUsage() {
		List<FUPRecord> fupRecordList = fUPRecordRepository.findAll();
		Map<String, List<FUPRecord>> fupRecordListGrpByDevice = fupRecordList.stream()
				.collect(Collectors.groupingBy(FUPRecord::getDevice));
		fupRecordListGrpByDevice.forEach((device, fupList) -> {
			if (device != null) {
				SubscriptionAgreement subscriptionAgreement = subscriptionAgreementRepository.findByMacAddress(device);
				String custId = subscriptionAgreement.getCustId();
				Optional<Customer> optionalCustomer = customerRepository.findByCustNo(custId);
				if (optionalCustomer.isPresent()) {
					String email = optionalCustomer.get().getEmailId();
					log.info("email" + email);
				}
			}
			log.info("Calulation  Started for the device " + device);
		});
	}
	public void sendEmail() {
		try {
			EmailUtils.sendEmail("narchintha@gmail.com", "FUP", "FUP USAGE");
			log.info("Email Successfully Sent");
		} catch (Exception e) {
			log.error("Error occured while sending email");

		}

	}

}
