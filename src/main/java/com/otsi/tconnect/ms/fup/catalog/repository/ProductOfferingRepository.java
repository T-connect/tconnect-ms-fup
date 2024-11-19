package com.otsi.tconnect.ms.fup.catalog.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.otsi.tconnect.ms.fup.catalog.entity.PlanType;
import com.otsi.tconnect.ms.fup.catalog.entity.ProductOffering;

@Repository
public interface ProductOfferingRepository extends JpaRepository<ProductOffering, Long> {
	
	List<ProductOffering> findAllByPlanType(PlanType planType);

	@Query(nativeQuery = true, value = "SELECT count(*), prod_offr_name  FROM subscription_agreement s ,product_offering p where \r\n"
			+ "p.prod_offr_id = s.prod_offrg_id \r\n" + "group by prod_offr_name order by count(*) desc ;\r\n" + "\r\n"
			+ "")
	List<Object[]> getPackageWiseCountOfInvoices();

	@Query(nativeQuery = true, value = "SELECT count(*), p.prod_offr_name " + "FROM subscription_agreement s "
			+ "JOIN product_offering p ON p.prod_offr_id = s.prod_offrg_id "
			+ "WHERE s.subsc_start_dt BETWEEN :startDate AND :endDate " + "GROUP BY p.prod_offr_name "
			+ "ORDER BY count(*) DESC")
	List<Object[]> getPackageWiseCountOfInvoicesByDateRange(@Param("startDate") LocalDate startDate,
			@Param("endDate") LocalDate endDate);
}