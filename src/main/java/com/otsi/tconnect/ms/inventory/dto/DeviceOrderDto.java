package com.otsi.tconnect.ms.inventory.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.otsi.tconnect.ms.inventory.entity.Device;

import lombok.Data;

@Data
public class DeviceOrderDto {

	private String id;
	private String orderType;
	private String description;
	private String status;
	private LocalDateTime orderCreateDate;
	private String billAccountNo;
	private String extendedData;
	private LocalDate createdDate;
	private String createdBy;
	private LocalDateTime modifiedDate;
	private String modifiedBy;
	private String deviceId;
	private String carrierId;
	private LocalDateTime requestDate;
	// all Orders
	private Device device;
	private Long quantity;
	private String action;
	// create shipment
	private String msoId;
	private String lcoId;
	private String deviceTypeID;
	private String shipmentId;
	private String deviceTypeName;
	private String inventoryId;
	// Shipment tracking details
	private ShipmentDTO shipmentDTO;

	// role
	private String roleType;
	private String shipmentType;

}
