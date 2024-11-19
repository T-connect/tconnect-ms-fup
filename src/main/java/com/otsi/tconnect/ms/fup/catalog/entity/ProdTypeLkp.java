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
@Table(name = "prod_type_lkp")
@Data
public class ProdTypeLkp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prod_type_id")
    private Integer id;

    @Column(name = "prod_type_name", length = 50)
    private String prodTypeName;

    @Column(name = "description", length = 100)
    private String description;

    @Column(name = "created_dt")
    private LocalDateTime createdDt;
}
