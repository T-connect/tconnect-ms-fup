package com.otsi.tconnect.ms.fup.customer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.otsi.tconnect.ms.fup.customer.entity.Customer;

/**
 * Spring Data JPA repository for the Customer entity.
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	@Query(value = "SELECT * FROM customer WHERE first_name LIKE %?1% "
			+ "OR last_name LIKE %?1% OR middle_name LIKE %?1% " + "OR email LIKE %?1% OR mobile LIKE %?1% "
			+ "OR active LIKE %?1%", countQuery = "SELECT count(*) FROM customer WHERE first_name LIKE %?1% "
					+ "OR last_name LIKE %?1% OR middle_name LIKE %?1% " + "OR email LIKE %?1% OR mobile LIKE %?1% "
					+ "OR active LIKE %?1%", nativeQuery = true)
	Page<Customer> findByKeyword(@Param("searchKey") String searchKey, Pageable pageable);

	@Query(value = "SELECT * FROM customer c WHERE c.partner_id  IN (:partnerIdList)", nativeQuery = true)
	List<Customer> getCustomersByPartnerNumberList(@Param("partnerIdList") List<String> partnerIdList);
	
	@Query(value = "SELECT cust_no FROM customer c WHERE c.partner_id  =:partnerId", nativeQuery = true)
	List<String> getCustomersByPartnerNumber(@Param("partnerId") String partnerId);

	List<Customer> findByPartnerId(String partnerId);

	Optional<Customer> findByCustNo(String custNo);
}
