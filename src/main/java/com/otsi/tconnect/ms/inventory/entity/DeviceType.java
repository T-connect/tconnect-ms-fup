package com.otsi.tconnect.ms.inventory.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "device_type")
public class DeviceType {

	@Id
	@Column(name = "device_type_id")
	private String deviceTypeId;

	@Column(name = "device_type_name", length = 20)
	private String deviceTypeName;

	@Column(name = "`desc`", length = 250)
	private String description;

	@Column(name = "created_dt")
	private LocalDate createdDate;

	@Column(name = "created_by", length = 30)
	private String createdBy;

	@Column(name = "modified_dt")
	private LocalDate modifiedDate;

	@Column(name = "modified_by", length = 30)
	private String modifiedBy;

	@ManyToOne
	@JoinColumn(name = "wh_id")
	private Warehouse warehouse;

	@PrePersist
	public void prePersist() {
	//	this.deviceTypeId = generateId();

		createdDate = LocalDate.now();
		modifiedDate = LocalDate.now();
	}

	/*
	 * private String generateId() { StringBuilder sb = new StringBuilder("DEV");
	 * String randomSuffix = UUID.randomUUID().toString().replace("-",
	 * "").substring(0, 7); sb.append(randomSuffix); return sb.toString(); }
	 */
}
