package com.otsi.tconnect.ms.fup.billing.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "balance")
public class Balance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "balance_id")
	private Long balanceId;

	@Column(name = "inv_id")
	private Long invId;
	
	@Column(name = "acnt_id")
	private Long acntId;
	
	@Column(name = "subsc_id")
	private Long subscId;
	
	@Column(name = "invoice_due_date")
	private LocalDateTime invoiceDueDate;
	
	@Column(name = "pymnt_due_dt")
	private LocalDateTime pymntDueDt;
	
	@Column(name = "invoice_amnt")
	private Double invoiceAmnt;
	
	@Column(name = "net_new_charges")
	private Double netNewCharges;
	
	@Column(name = "payment_id")
	private Long paymentId;
	
	@Column(name = "ttl_paid")
	private Double ttlPaid;
	
	@Column(name = "bal_due")
	private Double balDue;
	
	
	@Column(name = "created_dt")
	private LocalDateTime createdDt;
	
	@Column(name = "created_by")
	private String createdBy;
	
//
//	@Column(name = "RemainingAmount")
//	private BigDecimal remainingAmount;
//
//	@Column(name = "PaymentIdentifier")
//	private String paymentIdentifier;

}