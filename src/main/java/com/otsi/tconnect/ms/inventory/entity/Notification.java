package com.otsi.tconnect.ms.inventory.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GeneratorType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "notifiaction")
public class Notification {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "notification_id")
	private Long notificationId;

	@Column(name = "message")
	private String message;
	
	@Column(name = "`desc`")
	private String desc;
	
	@Column(name = "partner_id")
	private String partnerId;

	@Column(name = "customer_id")
	private String customerId;
	
	@Column(name = "created_date")
	@CreationTimestamp
	private LocalDate createdDate;
	
	@Column(name = "`read`")
	private Boolean read;

}
