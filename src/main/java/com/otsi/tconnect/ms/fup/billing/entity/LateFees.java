package com.otsi.tconnect.ms.fup.billing.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@Table(name = "late_fees")
public class LateFees {

	@Id
	@Column(name = "late_fee_id")
	private String lateFeed;
	
	@Column(name = "late_fee_srvc_id")
	private String lateFeeSrvcId;
	
	@Column(name = "passed_due_days")
	private int passedDueDays;
	
	@Column(name = "late_fee_amnt")
	private Double lateFeeAmnt;
	
	@Column(name = "start_dt")
	private LocalDate startDt;
	
	@Column(name = "end_dt")
	private LocalDate endDt;
	
	
	
//
//	@Column(name = "Description")
//	private String description;
//
//	@Column(name = "Days")
//	private Short days;
//
//	@Column(name = "LateFeeDate")
//	private Date lateFeeDate;
//
//	@Column(name = "SubscriptionIdentifier")
//	private String subscriptionIdentifier;
//
//	@Column(name = "PreviousInvoiceIdentifier")
//	private String previousInvoiceIdentifier;
//
//	@Column(name = "LatestInvoiceIdentifier")
//	private String latestInvoiceIdentifier;
}