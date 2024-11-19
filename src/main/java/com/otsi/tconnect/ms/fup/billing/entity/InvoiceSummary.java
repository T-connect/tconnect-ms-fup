package com.otsi.tconnect.ms.fup.billing.entity;

import java.time.LocalDateTime;
import java.util.Random;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "invoice_summary")
public class InvoiceSummary {

	@Id
	@Column(name = "invoice_id")
	private Long invoiceId;
	
	@Column(name = "cust_id")
	private String custId;
	
	@Column(name = "acnt_id")
	private Long acntId;
	
	@Column(name = "subsc_id")
	private Long subscId;
	
	@Column(name = "balance_id")
	private Long balanceId;
	
	@Column(name = "prv_inv_id")
	private Long prvInvId;
	
	@Column(name = "inv_dt")
	private LocalDateTime invDt;
	
	@Column(name = "due_dt")
	private LocalDateTime dueDt;
	
	@Column(name = "ttl_unit_price")
	private Double ttlUnitPrice;
	
	@Column(name = "ttl_tax_amnt")
	private Double ttlTaxAmnt;
	
	@Column(name = "ttl_dscnt_amnt")
	private Double ttlDscntAmnt;
	
	@Column(name = "ttl_adj_amnt")
	private Double ttlAdjAmnt;
	
	@Column(name = "prv_outstng_amnt")
	private Double prvOutstngAmnt;
	
	@Column(name = "ttl_late_fee_amnt")
	private Double ttlLateFeeAmnt;
	
	@Column(name = "ttl_net_amnt")
	private Double ttlNetAmnt;
	
	@Column(name = "created_dt")
	@CreationTimestamp
	private LocalDateTime createdDt;
	
	@Column(name = "batch_id")
	private String batchId;
	
	@Column(name = "payment_done")
	private Boolean paymentDone;

	@PrePersist
	public void prePersist() {
		this.invoiceId = generateId();
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