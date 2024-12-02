package com.otsi.tconnect.ms.inventory.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class DeviceTypeDTO {
	private String deviceTypeId;
	private String deviceTypeName;
	private String description;
	private LocalDate createdDate;
	private String createdBy;
	private LocalDate modifiedDate;
	private String modifiedBy;

}
