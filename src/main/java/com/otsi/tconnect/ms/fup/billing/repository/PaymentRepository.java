package com.otsi.tconnect.ms.fup.billing.repository;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.otsi.tconnect.ms.fup.billing.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Serializable> {

	@Query(value = "SELECT p.* FROM payment p " + "WHERE p.pymt_dt BETWEEN :startDate AND :endDate "
			+ "AND p.invoice_id IN (SELECT i.invoice_id FROM invoice_summary i "
			+ "WHERE i.payment_done = :paymentDone " + "AND i.cust_id IN (:custListId))", nativeQuery = true)
	List<Payment> getAllPaymentsByCustIds(@Param("custListId") List<String> custListId,
			@Param("paymentDone") Boolean paymentDone, @Param("startDate") LocalDateTime startDate,
			@Param("endDate") LocalDateTime endDate);

	@Query(value = "select p.* from payment p where p.pymt_dt BETWEEN :startDate and :endDate and p.invoice_id in (select i.invoice_id from invoice_summary i where i.payment_done = :payment_done and i.cust_id in(:custListId))", nativeQuery = true)
	public List<Payment> getInvoiceDetails(@Param("custListId") List<String> custListId,
			@Param("payment_done") Boolean paymentDone, @Param("startDate") LocalDateTime startDate,
			@Param("endDate") LocalDateTime endDate);
}
