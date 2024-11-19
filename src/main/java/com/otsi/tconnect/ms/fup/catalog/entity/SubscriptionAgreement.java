package com.otsi.tconnect.ms.fup.catalog.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "subscription_agreement")
@Data
public class SubscriptionAgreement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "subsc_gen")
	@SequenceGenerator(name = "subsc_gen", sequenceName = "subsc_seq", initialValue = 60000000, allocationSize = 1)
	@Column(name = "subsc_id")
	private Long id;

	@Column(name = "subsc_status", length = 50)
	@Enumerated(EnumType.STRING)
	private Status subscStatus;

	@Column(name = "cust_id")
	private String custId;

	@Column(name = "actt_id")
	private Long acttlId;

	@Column(name = "user_id", length = 10)
	private String userId;

	@Column(name = "subsc_start_dt")
	private LocalDateTime subscStartDt;

	@Column(name = "subsc_end_dt")
	private LocalDateTime subscEndDt;

	@Column(name = "subc_chg_dt")
	private LocalDateTime subcChgDt;

	@Column(name = "actual_start_dt")
	private LocalDateTime actualStartDt;

	@Column(name = "actual_end_dt")
	private LocalDateTime actualEndDt;

	@Column(name = "extended_data", length = 2500)
	private String extendedData;

	@Column(name = "prod_offrg_id")
	private Long productOfferingId;
	
	@Column(name = "mac_address")
	private String macAddress;
	
	@Column(name = "framed_ip")
	private String framedIp;
	

}