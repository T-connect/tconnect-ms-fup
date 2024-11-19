package com.otsi.tconnect.ms.fup.billing.entity;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "payment")
public class Payment/* extends AbstractAuditingEntity */ {

	@Id
	@Column(name = "payment_id", nullable = false)
	private String paymentId;

	@ManyToOne
	@JoinColumn(name = "invoice_id")
	private InvoiceSummary invoiceId;

	@Column(name = "acnt_id")
	private Long acntId;

	@ManyToOne
	@JoinColumn(name = "pymt_mthd_id")
	private PaymentMethod pymtMthdId;

	@Column(name = "pymt_dt")
	private LocalDateTime pymtDt;

	@Column(name = "pymt_updt_dt")
	private LocalDateTime pymtUpdtDt;

	@Column(name = "ttl_amnt")
	private Double ttlAmnt;

	@Column(name = "status")
	private String status;

	@Column(name = "src_type")
	private String srcType;

	@Column(name = "amnt_paid")
	private Double amntPaid;

	@Column(name = "tx_no")
	private String txNo;

	@Column(name = "remaining_amnt")
	private Double remainingAmnt;

	@Column(name = "batch_id")
	private String batchId;
	
	@CreatedDate
	@Column(name = "created_date", updatable = false)
	private Instant createdDate = Instant.now();
	
	@LastModifiedDate
	@Column(name = "last_modified_date")
	private Instant lastModifiedDate = Instant.now();

	@PrePersist
	public void prePersist() {
		// this.invoiceId = generateId();
	}

	private Long generateId() {
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 14; i++) {
			sb.append(random.nextInt(10));
		}
		return Long.parseLong(sb.toString());
	}

}
