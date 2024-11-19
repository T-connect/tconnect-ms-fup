package com.otsi.tconnect.ms.fup.catalog.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "location_lkp")
@Data
public class LocationLkp {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "loc_id")
	private Integer locId;

	@Column(name = "pincode", length = 50)
	private Long pinCode;

	@Column(name = "loc_name", length = 50)
	private String locName;

	@Column(name = "sub_city", length = 50)
	private String subCity;

	@Column(name = "city", length = 50)
	private String city;
	
	@Column(name = "state", length = 50)
	private String state;

	@Column(name = "country", length = 50)
	private String country;

	@Column(name = "user_id", length=10)
	private String userId;

	@Column(name = "created_dt")
	private LocalDateTime createdDt;
}
