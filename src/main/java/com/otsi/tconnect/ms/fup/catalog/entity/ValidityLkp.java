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
@Table(name = "validity_lkp")
@Data
public class ValidityLkp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "validity_type_id")
    private Long validityTypeId;

    @Column(name = "validity_type_id_val", length = 50)
    private String validityTypeIdVal;

    @Column(name = "description", length = 250)
    private String description;

    @Column(name = "user_id", length = 10)
    private String userId;

    @Column(name = "created_dt")
    private LocalDateTime createdDt;
}
