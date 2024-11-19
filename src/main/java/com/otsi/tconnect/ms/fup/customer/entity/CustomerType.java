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
@Table(name = "cust_type")
@Data
public class CustomerType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id")
	private Long id;

	@Column(name = "cust_type_name")
	private String custTypeName;

	@Column(name = "`desc`")
	private String desc;

	@Column(name = "create_dt")
	private LocalDateTime createDt;

	@Column(name = "created_by", length = 30)
	private String createdBy;

	@Column(name = "modify_dt")
	private LocalDateTime modifyDt;

	@Column(name = "modified_by", length = 30)
	private String modifiedBy;

}
