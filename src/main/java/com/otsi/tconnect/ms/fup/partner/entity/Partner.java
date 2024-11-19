package com.otsi.tconnect.ms.fup.partner.entity;

import java.util.Date;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Table(name = "partner")
@Data
public class Partner {
	@Id
	@Column(name = "partner_id", nullable = false)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	
	@Column(name="partner_number")
	private String partnerNumber;

	
	@Column(name = "parent_partner_id")
	private String parentPartnerId;

	
	@Column(name = "partner_type_id")
	private Long partnerType;
	
	@Column(name = "role_id")
	private String roleId;
	
	@Column(name = "salutation")
	private String salutation;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "middle_name")
	private String middleName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "partner_name")
	private String partnerName;

	@Column(name = "description")
	private String description;

	@Column(name = "gst_no")
	private String gstNo;

	@Column(name = "flat_no")
	private String flatNo;

	@Column(name = "street")
	private String street;

	@Column(name = "area")
	private String area;

	@Column(name = "state")
	private String state;

	@Column(name = "country")
	private String country;

	@Column(name = "pincode")
	private Integer pincode;

	@Column(name = "phone_1")
	private String phone1;

	@Column(name = "phone_2")
	private String phone2;

	@Column(name = "landline_no")
	private String landlineNo;

	@Column(name = "email_id")
	private String emailId;

	@Column(name = "kyc_status")
	private String kycStatus;

	@Column(name = "kyc_mode")
	private String kycMode;

	@Column(name = "kyc_ref_no")
	private String kycRefNo;

	@Column(name = "status")
	private String status;

	@Column(name = "start_dt")
	@Temporal(TemporalType.DATE)
	private Date startDt;

	@Column(name = "end_dt")
	@Temporal(TemporalType.DATE)
	private Date endDt;

	@Column(name = "is_active")
	private Boolean isActive;
	
	@Column(name = "role_type")
	private String roleType;


}