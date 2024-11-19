package com.otsi.tconnect.ms.fup.catalog.entity;

import java.time.LocalDateTime;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tax_dtl_lkp")
@Data
public class TaxDtlLkp {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tax_dtl_id")
	private Integer id;

//    @ManyToOne
//    @JoinColumn(name = "tax_profile_id")
//    private TaxProfileLkp taxProfileLkp;

//	@Column(name = "tax_name", length = 250)
//	private String taxName;

	@Column(name = "tax_desc", length = 250)
	private String taxDesc;

	@Column(name = "tax_rate_in_pct")
	private Double taxRateInPct;
	
	@Column(name = "tax_rate_in_amt")
	private Double taxRateInAmt;

	@Column(name = "user_id", length=10)
	private String userId;

	@Column(name = "eff_start_dt")
	private LocalDateTime effStartDt;
	
	@Column(name = "eff_end_dt")
	private LocalDateTime effEndDt;
}
