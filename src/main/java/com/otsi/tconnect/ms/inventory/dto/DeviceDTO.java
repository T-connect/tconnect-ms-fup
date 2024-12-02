package com.otsi.tconnect.ms.inventory.dto;

import java.time.LocalDate;

import com.otsi.tconnect.ms.inventory.entity.Warehouse;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class DeviceDTO {
	private String deviceId;
	private String description;
	private LocalDate entryDate;
	private LocalDate endDate;
	private String createdBy;
	private LocalDate modifiedDate;
	private String modifiedBy;
	private String status;
	private String action;
	private DeviceTypeDTO deviceType;
	private String lcoId;
	private String msoId;
	private String customerNumber;
	private String inventoryId;
	private String orderId;
	private String deviceTypeName;
	private String macAddress;
	private String framedIp;

}
