package com.otsi.tconnect.ms.fup.partner.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.otsi.tconnect.ms.fup.partner.entity.PartnerDetails;

@Repository
public interface PartnerDetailsRepository
		extends JpaRepository<PartnerDetails, String>, JpaSpecificationExecutor<PartnerDetails> {

	@Query(nativeQuery = true, value = "select * from partner_details where partner_id=:partnerId")
	public List<PartnerDetails> getPartnerDetails(@Param("partnerId") String partnerId);

	@Query(nativeQuery = true, value = "SELECT a.partner_number FROM partner a where partner_number=:partnerNo union select a.partner_number from partner a where parent_partner_id=:partnerNo union select a.partner_number from partner a where parent_partner_id in (select partner_number from partner.partner where parent_partner_id=:partnerNo)")
	public List<Object[]> getAllPartnerDetailsByParent(@Param("partnerNo") String partnerNo);

}
