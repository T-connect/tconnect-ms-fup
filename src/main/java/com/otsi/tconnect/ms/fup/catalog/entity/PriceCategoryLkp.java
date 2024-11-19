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
@Table(name = "price_category_lkp")
@Data
public class PriceCategoryLkp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "price_category_id")
    private Integer priceCategoryId;

    @Column(name = "price_category_name", length = 50)
    private String priceCategoryName;

    @Column(name = "description", length = 100)
    private String description;

    @Column(name = "created_dt")
    private LocalDateTime createdDt;
}
