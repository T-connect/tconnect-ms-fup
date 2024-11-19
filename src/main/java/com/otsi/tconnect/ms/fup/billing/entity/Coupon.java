package com.otsi.tconnect.ms.fup.billing.entity;

import java.sql.Date;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Coupon")
@Data
public class Coupon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long Id;
	
	@Column(name = "code")
    private String code;
	
	@Column(name = "isValid")
    private boolean isValid;
	
	@Column(name = "expiryDate")
   private LocalDate expiryDate;
	
	
	

	
}
	 