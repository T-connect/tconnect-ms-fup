package com.otsi.tconnect.ms.inventory.entity;

import java.time.LocalDate;

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
@Table(name = "device")
public class Device {

	@Id
	@Column(name = "device_id")
	private String deviceId;
	
	@Column(name = "`desc`", length = 250)
	private String description;

	@Column(name = "entry_dt")
	private LocalDate entryDate;

	@Column(name = "created_by", length = 30)
	private String createdBy;

	@Column(name = "modified_dt")
	private LocalDate modifiedDate;
	
	@Column(name = "end_date")
	private LocalDate endDate;

	@Column(name = "modified_by", length = 30)
	private String modifiedBy;

	@Column(name = "quantity")
	private Integer quantity;

	@Column(name = "status")
	private String status;

	/*
	 * @Column(name = "action") private String action;
	 */

	@ManyToOne
	@JoinColumn(name = "device_type_id")
	private DeviceType deviceType;

	@Column(name = "lcoid")
	private String lcoId;

	@Column(name = "mso_id")
	private String msoId;

	@Column(name = "customer_number")
	private String customerNumber;

	@Column(name = "inventory_id")
	private String inventoryId;
	
	@Column(name = "order_id")
	private String orderId;
	
	@Column(name = "mac_address")
	private String macAddress;
	
	@Column(name = "framed_ip")
	private String framedIp;
	

	@PrePersist
	public void prePersist() {
		entryDate = LocalDate.now();
		modifiedDate = LocalDate.now();
	}
}
