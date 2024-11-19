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
@Table(name = "revenue_share_profile")
@Data
public class RevenueShareProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "revenue_share_profile_id")
    private Long revenueShareProfileId;

    @Column(name = "mso_revenue_share_pct")
    private Double msoRevenueSharePct;

    @Column(name = "mso_revenue_share_amt")
    private Double msoRevenueShareAmt;
    
    @Column(name = "lco_revenue_share_pct")
    private Double lcoRevenueSharePct;
    
    @Column(name = "lco_revenue_share_amt")
    private Double lcoRevenueShareAmt;

    @Column(name = "is_revenue_share_eligable")
    private Integer isRevenueShareEligable;

    @Column(name = "user_id")
    private String userId;
    
    @Column(name = "eff_start_dt")
    private LocalDateTime effStartDt;
    
    @Column(name = "eff_end_dt")
    private LocalDateTime effEndDt;
}
