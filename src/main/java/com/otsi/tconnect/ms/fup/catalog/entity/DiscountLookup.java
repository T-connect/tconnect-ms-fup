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
@Data
@Table(name = "discount")
public class DiscountLookup {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "discount_id")
	private Integer id;

	@Column(name = "Discount_Description")
	private String discountDescription;

	@Column(name = "Discount_Percentage")
	private Short discountPercentage;

	@Column(name = "Discount_Amount")
	private Double discountAmount;

	@Column(name = "Effective_Start")
	private LocalDateTime effectiveStart;

	@Column(name = "Effective_End_Date")
	private LocalDateTime effectiveEndDate;

	@Column(name = "Active_Indicator")
	private String activeIndicator;
}