package com.otsi.tconnect.ms.fup.billing.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.otsi.tconnect.ms.fup.billing.entity.InvoiceSummary;

@Repository
public interface InvoiceSummaryRepository extends JpaRepository<InvoiceSummary, Long> {

	List<InvoiceSummary> findByAcntId(Long acntId);

	List<InvoiceSummary> findByCustId(String custId);

	@Query(value = "select i.* from invoice_summary i where i.payment_done = :payment_done and i.cust_id in(:custListId) and i.due_dt between :startDate and :endDate", nativeQuery = true)
	List<InvoiceSummary> getAllPendingPaymentsByCustIdList(@Param("custListId") List<String> custListId,
			@Param("payment_done") Boolean paymentDone, @Param("startDate") LocalDateTime startDate,
			@Param("endDate") LocalDateTime endDate);
	

	
}
