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
@Table(name = "invoice_detail")
public class InvoiceDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "invoice_dtl_id")
	private Long invoiceDtlId;

	@Column(name = "invoice_id")
	private Long invoiceId;
	
	@Column(name = "chrg_type")
	private String chrgType;
	
	@Column(name = "prod_id")
	private Long prodId;
	
	@Column(name = "bundle_offrng_id")
	private Long bundleOffrngId;
	
	@Column(name = "prod_offrng_id")
	private Long prodOffrngId;
	
	@Column(name = "start_dt")
	private LocalDateTime startDt;

	@Column(name = "end_dt")
	private LocalDateTime endDt;
	
	@Column(name = "unit_price")
	private Double unitPrice;
	
	@Column(name = "tax_amnt")
	private Double taxAmnt;
	
	@Column(name = "ttl_amnt")
	private Double ttlAmnt;
	
//	@Column(name = "AdjustmentIdentifier")
//	private String adjustmentIdentifier;
//
//	@Column(name = "LateFeeIdentifier")
//	private String lateFeeIdentifier;
//
//	@Column(name = "SubscriptionDetailIdentifier")
//	private String subscriptionDetailIdentifier;
//
//	@Column(name = "DiscountAmount")
//	private Double discountAmount;
//
//	@Column(name = "AdjustmentAmount")
//	private BigDecimal adjustmentAmount;
//
//	@Column(name = "LateFeeAmount")
//	private Double lateFeeAmount;

}