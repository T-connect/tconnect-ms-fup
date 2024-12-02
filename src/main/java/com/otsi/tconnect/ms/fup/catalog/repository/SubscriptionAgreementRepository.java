package com.otsi.tconnect.ms.fup.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.otsi.tconnect.ms.fup.catalog.entity.SubscriptionAgreement;

public interface SubscriptionAgreementRepository extends JpaRepository<SubscriptionAgreement,Long>{

	SubscriptionAgreement findByActtlId(Long accountNumber);
	
	//SubscriptionAgreement findByMacAddress(String custId);

	SubscriptionAgreement findByCustId(String custId);

}
