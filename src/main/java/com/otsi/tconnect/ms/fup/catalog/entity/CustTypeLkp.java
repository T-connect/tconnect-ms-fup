package com.otsi.tconnect.ms.fup.catalog.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "cust_type_lkp")
@Data
public class CustTypeLkp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_type_id")
    private Integer custTypeId;

    @Column(name = "cust_type_name", length = 50)
    private String custTypeName;

    @Column(name = "description", length = 100)
    private String description;

    @Column(name = "created_dt")
    private LocalDateTime createdDt;
}
