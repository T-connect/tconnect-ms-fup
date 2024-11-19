package com.otsi.tconnect.ms.fup.billing.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "collections")
public class Collections {

	@Id
	@Column(name = "coll_id")
	private String collId;

	@Column(name = "invoice_id")
	private String invoiceId;

	@Column(name = "acnt_id")
	private String acntId;

	@Column(name = "pymt_due_dt")
	private LocalDate pymtDueDt;

	@Column(name = "tran_dt")
	private LocalDate tranDt;

	@Column(name = "srvc_id")
	private String srvcId;

	@Column(name = "coll_entry_dt")
	private LocalDate collEntryDt;

	@Column(name = "coll_exit_dt")
	private LocalDate collExitDt;

	@Column(name = "coll_mlnstn_id")
	private String collMlnstnId;

	@Column(name = "coll_exe_dt")
	private LocalDate collExeDt;

}
