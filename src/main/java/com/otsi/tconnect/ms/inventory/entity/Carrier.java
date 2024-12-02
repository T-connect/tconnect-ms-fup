package com.otsi.tconnect.ms.inventory.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "carrier")
public class Carrier {

	@Id
	@Column(name = "carrier_id", length = 10, nullable = false)
	private String carrierId;

	@Column(name = "carrier_name", length = 30)
	private String carrierName;

	@Column(name = "carrier_type", length = 10)
	private String carrierType;

	@Column(name = "created_dt")
	private LocalDate createdDate;

	@Column(name = "created_by", length = 30)
	private String createdBy;

	@Column(name = "modified_dt")
	private LocalDate modifiedDate;

	@Column(name = "modified_by", length = 30)
	private String modifiedBy;

	@PrePersist
	public void prePersist() {
		createdDate = LocalDate.now();
		modifiedDate = LocalDate.now();
	}

}
