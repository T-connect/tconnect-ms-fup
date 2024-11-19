package com.otsi.tconnect.ms.fup.billing.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "pymt_mthd")
public class PaymentMethod {

	@Id
	@Column(name = "pymt_mthd_id")
	private String pymtMthdId;

	@Column(name = "pymt_mthd_name")
	private String pymtMthdName;

	@Column(name = "description")
	private String description;

}