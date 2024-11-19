package com.otsi.tconnect.ms.fup.customer.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "otp")
public class Otp {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "otp_id")
	private Long id;

	@Column(name = "mobile")
	private String phoneNumber;

	@Column(name = "otp")
	private String otp;

	@Column(name = "send_date")
	private LocalDateTime otpSendDate;

	@Column(name = "verified_date")
	private LocalDateTime otpVerifiedDate;

	@Column(name = "otp_status")
	private String otpStatus;

}