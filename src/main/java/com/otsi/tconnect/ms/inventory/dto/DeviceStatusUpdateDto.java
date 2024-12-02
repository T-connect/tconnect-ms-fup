package com.otsi.tconnect.ms.inventory.dto;

import java.util.List;

import lombok.Data;

@Data
public class DeviceStatusUpdateDto {

	private String customerNumber;
	private String lcoId;
	private List<String> devices; // This could represent a list of device IDs
	private String status;
}
