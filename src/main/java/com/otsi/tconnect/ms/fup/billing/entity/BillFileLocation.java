package com.otsi.tconnect.ms.fup.billing.entity;


import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "bill_file_location")
public class BillFileLocation {

    @Id
    @Column(name = "file_loc_id" )
    private String fileLocId;

    @Column(name = "invoice_id" )
    private String invoiceId;

    @Column(name = "batch_id" )
    private String batchId;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_loc" )
    private String fileLoc;

    @Column(name = "status")
    private String status;

    @Column(name = "created_dt")
    private LocalDate createdDt;

}
