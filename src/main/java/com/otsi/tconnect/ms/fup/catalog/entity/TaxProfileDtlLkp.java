package com.otsi.tconnect.ms.fup.catalog.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tax_profile_dtl_lkp")
@Data
public class TaxProfileDtlLkp {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tax_profile_dtl_id")
    private Integer id;
    
    @ManyToOne
	@JoinColumn(name = "tax_profile_id")
	private TaxProfileLkp taxProfile;
    
    @ManyToOne
	@JoinColumn(name = "tax_id")
    private TaxDtlLkp taxDtl;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "eff_start_date")
    private LocalDateTime effStartDate;
    
    @Column(name = "eff_end_date")
    private LocalDateTime effEndDate;
    
//    @ManyToMany
//	@JoinTable(name = "tax_profile", joinColumns = @JoinColumn(name = "tax_id"), inverseJoinColumns = @JoinColumn(name = "tax_profile_id"))
//	private List<TaxDtlLkp> taxDtlLkp;


}
