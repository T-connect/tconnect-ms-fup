package com.otsi.tconnect.ms.fup.billing.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "adjustment")
public class Adjustments {

	@Id
	@Column(name = "adj_id")
	private String adjId;

	@Column(name = "acnt_id")
	private String acntId;

	@Column(name = "susbc_id")
	private String susbcId;

	@Column(name = "adj_amnt")
	private BigDecimal adjAmnt;

	@Column(name = "prv_inv_id")
	private String prvInvId;

	@Column(name = "inv_id")
	private String invId;

	@Column(name = "post_dt")
	private LocalDate postDt;

	@Column(name = "billed_dt")
	private LocalDate billedDt;

	@Column(name = "created_dt")
	private LocalDate createdDt;

	@Column(name = "adj_dim_id")
	private String adjDimId;

}
