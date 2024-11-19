package com.otsi.tconnect.ms.fup.fup.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "fup_record")
@Data
public class FUPRecord {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "client")
	private String client;

	@Column(name = "device")
	private String device;

	@Column(name = "service")
	private Integer service;

	@Column(name = "nas_ip_address")
	private String nasIpAddress;

	@Column(name = "framed_ip_address")
	private String framedIpAddress;

	@Column(name = "framed_ipv6_prefix")
	private String framedIpv6Prefix;

	@Column(name = "acct_status_type")
	private String acctStatusType;

	@Column(name = "acct_input_octets")
	private Long acctInputOctets;

	@Column(name = "acct_output_octets")
	private Long acctOutputOctets;

	@Column(name = "acct_input_gigawords")
	private Integer acctInputGigawords;

	@Column(name = "acct_output_gigawords")
	private Integer acctOutputGigawords;

	@Column(name = "acct_session_time")
	private Integer acctSessionTime;

	@Column(name = "event_timestamp")
	private Long eventTimestamp;

	@Column(name = "nas_port_id")
	private String nasPortId;

	@Column(name = "acct_session_id")
	private String acctSessionId;

	@Column(name = "acct_delay_time")
	private Integer acctDelayTime;

	@Column(name = "acct_terminate_cause")
	private String acctTerminateCause;

}
