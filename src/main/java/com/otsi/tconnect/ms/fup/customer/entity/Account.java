package com.otsi.tconnect.ms.fup.customer.entity;

import java.time.LocalDateTime;
import java.util.Random;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "account")
@Data
public class Account {

	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "acnt_no")
	private Long acntNo;

	@Column(name = "partner_id")
	private String partnerId;

	@Column(name = "acnt_name")
	private String acntName;

	@Column(name = "cust_no")
	private String custNo;

	@Column(name = "acnt_start_date")
	private LocalDateTime acntStartDate;

	@Column(name = "acnt_end_date")
	private LocalDateTime acntEndDate;

	@Column(name = "acnt_status")
	@Enumerated(EnumType.STRING)
	private Status acntStatus;

	@Column(name = "billing_cycle")
	private String billingCycle;

	@Column(name = "bill_period")
	private String billPeriod;

	@Column(name = "nxt_bill_dt")
	private LocalDateTime nxtBillDt;

	@Column(name = "subscription_id")
	private Long subscriptionId;

	@Column(name = "order_id")
	private Long orderId;

	@Column(name = "create_dt")
	@CreationTimestamp
	private LocalDateTime createDt;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "modify_dt")
	@UpdateTimestamp
	private LocalDateTime modifyDt;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Column(name = "billing_status")
	private Boolean billingStatus;

	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "addr_id")
	private Address address;

	@PrePersist
	public void prePersist() {
		this.acntNo = generateId();
	}

	private Long generateId() {
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 12; i++) {
			sb.append(random.nextInt(10));
		}
		return Long.parseLong(sb.toString());
	}

}
