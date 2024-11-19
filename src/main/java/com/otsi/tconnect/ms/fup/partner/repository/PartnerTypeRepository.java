package com.otsi.tconnect.ms.fup.partner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.otsi.tconnect.ms.fup.partner.entity.PartnerType;

@Repository
public interface PartnerTypeRepository extends JpaRepository<PartnerType, Long> {

	//PartnerType save(PartnerType partnerType);

}
