package com.otsi.tconnect.ms.fup.partner.entity;

import java.time.LocalDate;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name="partner_type")
@Entity
@Data
public class PartnerType {

	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "partner_type_id")
	private Long id;
	
	@Column(name = "partner_type_name")
	private String partnerTypeName;
	
	@Column(name = "`desc`")
	private String desc;
	
	@Column(name = "create_dt")
	private LocalDate createDt;

	@Column(name = "created_by", length = 30)
	private String createdBy;

	@Column(name = "modify_dt")
	private LocalDate modifyDt;

	@Column(name = "modified_by", length = 30)
	private String modifiedBy;


}
