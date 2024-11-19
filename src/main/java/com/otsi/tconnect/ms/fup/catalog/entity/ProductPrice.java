package com.otsi.tconnect.ms.fup.catalog.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;


@Entity
@Table(name = "product_price")
@Data
public class ProductPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prod_price_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "prod_id_ref")
    private Product product;
    
    @ManyToOne
    @JoinColumn(name = "bundle_id_ref")
    private BundleOffering bundleOffering;
    
	@Column(name = "prod_type_id")
	private Integer prodTypeId;

    @Column(name = "price_category")
    private Integer priceCategory;

//    @Column(name = "revenue_share_profile_id")
//    private Integer revenueShareProfileId;
    
    
    @Column(name = "bill_in_adv_val_type")
    private Integer billInAdvValType;
    
    @Column(name = "bill_in_adv_val_period")
    private Integer billInAdvValPeriod;

    @Column(name = "cust_catg_id")
    private Integer custCatgId;
    
    @Column(name = "cust_type_id")
    private Integer custTypeId;
    
    @Column(name = "proration_on_start")
    private Integer prorationOnStart;
    
    @Column(name = "proration_on_end")
    private Integer prorationOnEnd;
    
    @Column(name = "prod_validity_days")
    private Integer prodValidityDays;

    @Column(name = "prod_validity_period", length = 50)
    private String prodValidityPeriod;

    @Column(name = "price")
    private Double price;
    
    @Column(name = "product_price_col")
    private Integer productPriceCol;

    @Column(name = "user_id")
    private String userId;
    
    @Column(name = "version")
    private String Version;

    @Column(name = "eff_start_dt")
    private LocalDateTime effStartDt;

    @Column(name = "eff_end_dt")
    private LocalDateTime effEndDt;
    
    @ManyToOne
	@JoinColumn(name = "tax_profile_id")
	private TaxProfileLkp taxProfileLkp;
    
    @ManyToOne
   	@JoinColumn(name = "revenue_share_profile_id")
   	private RevenueShareProfile revenueShareProfile;
}
