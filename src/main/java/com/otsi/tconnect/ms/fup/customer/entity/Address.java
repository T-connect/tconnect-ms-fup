package com.otsi.tconnect.ms.fup.customer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "address")
@Data
public class Address {

	@Id
	@SequenceGenerator(name = "addr_gen",sequenceName = "addr_seq",initialValue = 700000000,allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "addr_gen")
	@Column(name = "addr_id")
	private Long addrId;

	@Column(name = "cust_no", length = 10)
	private String custNo;

	@Column(name = "status", length = 10)
	private String status;

	@Column(name = "addr_type", length = 10)
	private String addrType;

	@Column(name = "srvc_addr_proof_type", length = 20)
	private String srvcAddrProofType;

	@Column(name = "srvc_addr_ln_1", length = 50)
	private String srvcAddrLn1;

	@Column(name = "srvc_addr_ln_2", length = 50)
	private String srvcAddrLn2;

	@Column(name = "srvc_area", length = 30)
	private String srvcArea;

	@Column(name = "srvc_city", length = 30)
	private String srvcCity;

	@Column(name = "srvc_state", length = 20)
	private String srvcState;

	@Column(name = "srvc_country", length = 30)
	private String srvcCountry;

	@Column(name = "srvc_pincode", length = 10)
	private String srvcPincode;

	@Column(name = "bill_addr_proof_type", length = 20)
	private String billAddrProofType;

	@Column(name = "bill_addr_line_1", length = 30)
	private String billAddrLine1;

	@Column(name = "bill_addr_line_2", length = 30)
	private String billAddrLine2;

	@Column(name = "bill_area", length = 30)
	private String billArea;

	@Column(name = "bill_city", length = 30)
	private String billCity;

	@Column(name = "bill_state", length = 20)
	private String billState;

	@Column(name = "bill_country", length = 30)
	private String billCountry;

	@Column(name = "bill_pincode", length = 10)
	private String billPincode;
}