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
@Table(name = "device_order")
public class DeviceOrder {

	@Id
	@Column(name = "device_order_id")
	private String id;

	@Column(name = "order_type")
	private String orderType;

	@Column(name = "`desc`")
	private String description;

	@Column(name = "status")
	private String status;

	@Column(name = "order_create_dt")
	private LocalDateTime orderCreateDate;

	@Column(name = "bill_acnt_no")
	private String billAccountNo;

	@Column(name = "extended_data")
	private String extendedData;

	@Column(name = "created_dt")
	private LocalDate createdDate;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Column(name = "request_date")
	private LocalDateTime requestDate;

	@Column(name = "mso_id")
	private String msoId;

	@Column(name = "lco_id")
	private String lcoId;

	@Column(name = "shipment_id")
	private String shipmentId;

	@Column(name = "shipment_type")
	private String shipmentType;

	@Column(name = "quantity")
	private Long quantity;

	@Column(name = "modified_dt")
	private LocalDateTime modifiedDate;

	@Column(name = "device_type_id")
	private String deviceTypeID;

	@Column(name = "action")
	private String action;

	@Column(name = "devices_id")
	private String deviceId;

	@Column(name = "role_type")
	private String roleType;

	@Column(name = "device_type_name")
	private String deviceTypeName;

	@Column(name = "inventory_id")
	private String inventoryId;

	@ManyToOne
	@JoinColumn(name = "device_id")
	private Device device;

	@PrePersist
	public void prePersist() {
		this.id = generateId();
		orderCreateDate = LocalDateTime.now();
		createdDate = LocalDate.now();
		modifiedDate = LocalDateTime.now();
	}

	private String generateId() {
		StringBuilder sb = new StringBuilder("DOR");
		String randomSuffix = UUID.randomUUID().toString().replace("-", "").substring(0, 7);
		sb.append(randomSuffix);
		return sb.toString();
	}

}
