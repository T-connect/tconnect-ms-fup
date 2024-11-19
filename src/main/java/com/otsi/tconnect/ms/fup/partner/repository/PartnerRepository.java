package com.otsi.tconnect.ms.fup.partner.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.otsi.tconnect.ms.fup.partner.entity.Partner;


@Repository
public interface PartnerRepository extends JpaRepository<Partner, String>,JpaSpecificationExecutor<Partner> {
	@Query(value = "SELECT * FROM partner WHERE first_name LIKE %?1% " +
            "OR middle_name LIKE %?1% OR partner_name LIKE %?1% " +
            "OR email_id LIKE %?1% OR kyc_status LIKE %?1% ",
            nativeQuery = true)
	Page<Partner> findByKeyword(@Param("searchKey") String searchKey, Pageable pageable);
	
	Optional<Partner> findByEmailId(String emailId);

	List<Partner> findByParentPartnerId(String id);

	Partner getByPartnerNumber(String partnerNumber);
	
	//Page<Partner> findByParentPartnerId(String id, Pageable pageable);
	
}