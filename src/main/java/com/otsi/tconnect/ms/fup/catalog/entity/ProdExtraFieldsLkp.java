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
@Table(name = "prod_extra_fields_lkp")
@Data
public class ProdExtraFieldsLkp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prod_ext_field_id")
    private Long prodExtFieldId;

    @Column(name = "prod_ext_field_name", length = 50)
    private String prodExtFieldName;

    @Column(name = "prod_ext_field_value", length = 50)
    private String prodExtFieldValue;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "created_dt")
    private LocalDateTime createdDt;
}

