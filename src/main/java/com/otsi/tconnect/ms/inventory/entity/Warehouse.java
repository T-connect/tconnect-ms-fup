package com.otsi.tconnect.ms.inventory.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "warehouse")
public class Warehouse {

	@Id
	@Column(name = "wh_id", length = 10, nullable = false)
	private String whId;

	@Column(name = "wh_name", length = 30)
	private String whName;

	@Column(name = "description", length = 250)
	private String description;

	@Column(name = "wh_phone", length = 15)
	private String whPhone;

	@Column(name = "wh_email_id", length = 30)
	private String whEmailId;

	@Column(name = "wh_area", length = 20)
	private String whArea;

	@Column(name = "wh_city", length = 20)
	private String whCity;

	@Column(name = "wh_state", length = 20)
	private String whState;

	@Column(name = "wh_country", length = 20)
	private String whCountry;

	@Column(name = "wh_pincode", length = 10)
	private String whPincode;

	@Column(name = "created_dt")
	private LocalDate createdDate;

	@Column(name = "created_by", length = 30)
	private String createdBy;

	@Column(name = "modified_dt")
	private LocalDate modifiedDate;

	@Column(name = "modified_by", length = 30)
	private String modifiedBy;
	
	@PrePersist
	public void prePersist() {
				
		createdDate = LocalDate.now();
		modifiedDate = LocalDate.now();
	}
	

//	@OneToMany(mappedBy = "dim_device")
//	private Set<Device> device;

}
