package com.otsi.tconnect.ms.fup.catalog.entity;

import java.time.LocalDateTime;
import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "product_offering")
@Data
public class ProductOffering {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "prodoffr_gen")
	@SequenceGenerator(name = "prodoffr_gen", sequenceName = "prodoffr_seq", initialValue = 300000, allocationSize = 1)
	@Column(name = "prod_offr_id")
	private Long id;

	@Column(name = "prod_offr_code", length = 50)
	private String prodOffrCode;

	@Column(name = "prod_offr_name", length = 50)
	private String prodOffrName;

	@Column(name = "prod_offr_desc", length = 400)
	private String prodOffrDesc;

	@Column(name = "user_id")
	private String userId;

	@Column(name = "plan_type")
	@Enumerated(EnumType.STRING)
	private PlanType planType;

//	@Column(name = "status")
//	@Enumerated(EnumType.STRING)
//	private StatusEnum status;

	@Column(name = "version")
	private String version;

	@Column(name = "eff_start_dt")
	private LocalDateTime effStartDt;

	@Column(name = "eff_end_dt")
	private LocalDateTime effEndDt;

	@Column(name = "extended_data", length = 2000)
	private String extendedData;

	@ManyToMany
	@JoinTable(name = "productoffering_bundleoffering", joinColumns = @JoinColumn(name = "prod_offr_id"), inverseJoinColumns = @JoinColumn(name = "bundle_offrng_id"))
	private List<BundleOffering> bundleOffering;

	@ManyToMany
	@JoinTable(name = "productoffering_product", joinColumns = @JoinColumn(name = "prod_offr_id"), inverseJoinColumns = @JoinColumn(name = "prod_id"))
	private List<Product> product;
	
	
	
	@Column(name = "fup_template_id")
	private Long fupTemplateId;


}

//    @PrePersist
//    public void prePersist() {
//        this.id = generateId();
//    }
//
//	private String generateId() {
//		StringBuilder sb = new StringBuilder("PAK");
//		Random random = new Random();
//		for (int i = 0; i < 5; i++) {
//			sb.append(random.nextInt(10));
//		}
//		return sb.toString();
//	}
