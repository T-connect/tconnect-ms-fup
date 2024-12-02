package com.otsi.tconnect.ms.inventory.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "device_allocate")
public class DeviceAllocate {

	@Id
	@Column(name = "device_alloc_id")
	private String deviceAllocId;

	@ManyToOne
	@JoinColumn(name = "shipment_id")
	private Shipment shipment;

	@Column(name = "partner_id", length = 10)
	private String partnerId;

	@Column(name = "alloc_dt")
	private LocalDate allocDate;

	@Column(name = "alloc_qty")
	private Integer allocQty;

	@PrePersist
	public void prePersist() {
		this.deviceAllocId = generateId();

	}

	private String generateId() {
		StringBuilder sb = new StringBuilder("");
		String randomSuffix = UUID.randomUUID().toString().replace("-", "").substring(0, 10);
		sb.append(randomSuffix);
		return sb.toString();
	}
}
