package com.otsi.tconnect.ms.inventory.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CustomerIdDeviceDTO {

	private String status;
	private LocalDate entryDate;
	private LocalDate endDate;
//	private String deviceTypeName;
	private DeviceTypeDTO deviceType;
	private String deviceId;
	private String macAddress;
	private String framedIp;

}
