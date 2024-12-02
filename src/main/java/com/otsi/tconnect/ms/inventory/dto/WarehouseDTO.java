package com.otsi.tconnect.ms.inventory.dto;

import java.time.LocalDate;

import lombok.Data;

 @Data
public class WarehouseDTO {


	private String WhId;
	private String whName;
	private String description;
	private String whPhone;
	private String whEmailId;
	private String whArea;
	private String whCity;
	private String whState;
	private String whCountry;
	private String whPincode;
	private LocalDate createdDate;
	private String createdBy;
	private LocalDate modifiedDate;
	private String modifiedBy;
	
	}

