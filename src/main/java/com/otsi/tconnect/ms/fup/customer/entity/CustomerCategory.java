package com.otsi.tconnect.ms.fup.customer.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "cust_catg")
@Data
public class CustomerCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cust_catg_id")
	private Long id;

	@Column(name = "cust_catg_name")
	private String custCatgName;

	@Column(name = "`desc`")
	private String desc;

	@Column(name = "create_dt")
	private LocalDateTime createDt;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "modify_dt")
	private LocalDateTime modifyDt;

	@Column(name = "modified_by")
	private String modifiedBy;

}
