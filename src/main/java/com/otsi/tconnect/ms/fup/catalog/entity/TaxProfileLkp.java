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
@Table(name = "tax_profile_lkp")
@Data
public class TaxProfileLkp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tax_profile_id")
    private Integer id;
    
    @Column(name = "tax_profile_desc")
    private String taxProfileDesc;

    @Column(name = "user_id", length=2000)
    private String userId;

    @Column(name = "created_dt")
    private LocalDateTime createdDt;
    
//    @ManyToMany
//	@JoinTable(name = "tax_profile", joinColumns = @JoinColumn(name = "tax_id"), inverseJoinColumns = @JoinColumn(name = "tax_profile_id"))
//	private List<TaxDtlLkp> taxDtlLkp;

   
}

