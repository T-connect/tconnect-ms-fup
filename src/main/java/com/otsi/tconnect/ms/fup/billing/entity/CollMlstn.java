package com.otsi.tconnect.ms.fup.billing.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "coll_mlstn")
public class CollMlstn {

	@Id
	@Column(name = "coll_mlnstn_id")
	private String collMlnstnId;

	@Column(name = "mlstn_status_name")
	private String mlstnStatusName;

	@Column(name = "description")
	private String description;

	@Column(name = "days")
	private int days;

	@Column(name = "created_dt")
	private LocalDate createdDt;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "modified_dt")
	private LocalDate modifiedDt;

	@Column(name = "modified_by")
	private String modifiedBy;

}
