package com.otsi.tconnect.ms.fup.customer.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * A Customer.
 */
@Entity
@Table(name = "customer")
@Data
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "customer_gen")
	@SequenceGenerator(name = "customer_gen", sequenceName = "customer_seq", initialValue = 900000000, allocationSize = 1)
	@Column(name = "customer_id")
	private Long id;

	@Column(name = "cust_no", nullable = false, length = 10)
	private String custNo;

	@ManyToOne
	@JoinColumn(name = "cust_type_id")
	private CustomerType custType;

	@Column(name = "cust_class_id")
	private Integer custClassId;

	@Column(name = "salutation", length = 10)
	private String salutation;

	@Column(name = "first_name", length = 50)
	private String firstName;

	@Column(name = "middle_name", length = 50)
	private String middleName;

	@Column(name = "last_name", length = 50)
	private String lastName;

	@Column(name = "org_name", length = 50)
	private String orgName;

	@Column(name = "gst_no", length = 30)
	private String gstNo;

	@Column(name = "gender", length = 10)
	private String gender;

	@Column(name = "dob")
	private LocalDate dob;

	@Column(name = "email_id", unique = true,nullable = false)
	private String emailId;

	@Column(name = "notification_preferences", length = 20)
	private String notificationPreferences;

	@Column(name = "vip_flag")
	private Boolean vipFlag;

	@Column(name = "user_id", length = 10)
	private String userId;

	@Column(name = "partner_id", length = 15)
	private String partnerId;

	@Column(name = "phone_1", unique = true,nullable = false)
	private String phone1;

	@Column(name = "phone_2", length = 15)
	private String phone2;

	@Column(name = "relation_type", length = 10)
	private String relationType;

	@Column(name = "relation_name", length = 30)
	private String relationName;

	@Column(name = "doc_type_1", length = 10)
	private String docType1;

	@Column(name = "doc_type_2", length = 10)
	private String docType2;

	@Column(name = "kyc_status", length = 20)
	private String kycStatus;

	@Column(name = "kyc_mode", length = 10)
	private String kycMode;

	@Column(name = "kyc_ref_no", length = 50)
	private String kycRefNo;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private Status status;

	@Column(name = "extended_data", length = 2000)
	private String extendedData;

	@Column(name = "create_dt")
	@CreationTimestamp
	private LocalDateTime createDt;

	@Column(name = "created_by", length = 30)
	private String createdBy;

	@Column(name = "modify_dt")
	@UpdateTimestamp
	private LocalDateTime modifyDt;

	@Column(name = "modified_by", length = 30)
	private String modifiedBy;

	@ManyToMany(cascade = CascadeType.REMOVE)
	@JoinTable(name = "customer_account", joinColumns = @JoinColumn(name = "customer_id"), inverseJoinColumns = @JoinColumn(name = "acnt_no"))
	private List<Account> accounts;
	
	@PrePersist
	void onPrePersist() {
		this.createDt = LocalDateTime.now();
	}

}