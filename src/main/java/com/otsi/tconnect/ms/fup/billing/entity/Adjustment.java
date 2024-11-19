package com.otsi.tconnect.ms.fup.billing.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "adjustment")
public class Adjustment {
	
	@Id
	@Column(name = "adj_dim_id" )
	private String adjDimId;

	@Column(name = "description" )
	private String description;

	@Column(name = "amnt")
	private Double amnt;
	
	@Column(name = "start_dt")
	private LocalDateTime startDt;
	
	@Column(name = "end_dt")
	private LocalDateTime endDt;

	@Column(name = "srvc_id" )
	private Long srvcId;


//	@Id
//	@Column(name = "adj_dim_id",nullable = false )
//	private String adjDimId;
//
//	@Column(name = "desc" )
//	private String desc;
//
//	@Column(name = "amnt")
//	private BigDecimal amnt;
//	
//	@Column(name = "start_dt")
//	private LocalDate startDt;
//
//	@Column(name = "end_dt")
//	private LocalDate endDt;
//	
//	@Column(name = "srvc_id" )
//	private String srvcId;
//	
//	@Column(name = "AdjustmentPercentage")
//	private Integer adjustmentPercentage;
//
//	@Column(name = "PreviousInvoiceIdentifier" )
//	private String previousInvoiceIdentifier;
//
//	@Column(name = "InvoiceIdentifier" )
//	private String invoiceIdentifier;
//
//	@Column(name = "Created_Date")
//	private Date createdDate;

}