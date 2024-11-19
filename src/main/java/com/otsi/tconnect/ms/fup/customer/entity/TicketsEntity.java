package com.otsi.tconnect.ms.fup.customer.entity;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.otsi.tconnect.ms.fup.util.StatusEnum;
import com.otsi.tconnect.ms.fup.util.TypeEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "tickets")
public class TicketsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ticket_no")
	private Long ticketNo;

	@Column(name = "name")
	private String name;

	@Column(name = "phn_nmbr")
	private String phoneNumber;

	@Column(name = "email_id")
	private String email;

	@Column(name = "description")
	private String description;

	@Column(name = "status")
    @Enumerated(EnumType.STRING)
	private StatusEnum status;

	@Column(name = "type")
    @Enumerated(EnumType.STRING)
	private TypeEnum type;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	@Column(name = "partner_id", length = 15)
	private String partnerId;
	
	@Column(name = "issue_related", length = 15)
	private String issueRelated;

	@CreatedDate
	@Column(name = "created_date", updatable = false)
	private Instant createdDate = Instant.now();

	@LastModifiedDate
	@Column(name = "last_modified_date")
	private Instant lastModifiedDate = Instant.now();

}
