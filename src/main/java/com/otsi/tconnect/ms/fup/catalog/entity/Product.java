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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "product")
@Data
public class Product extends AbstractAuditingEntity<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "prod_gen")
	@SequenceGenerator(name = "prod_gen", sequenceName = "prod_seq", initialValue = 100000, allocationSize = 1)
	@Column(name = "prod_id")
	private Long id;

	@Column(name = "prod_chrg_type_id")
	private Long prodChrgTypeId;

	@Enumerated(EnumType.STRING)
	@Column(name = "prod_chrg_type")
	private ChargeType prodChrgType;

	@ManyToOne
	@JoinColumn(name = "prod_type_id")
	private ProdTypeLkp prodType;

	@Column(name = "prod_code", length = 50)
	private String prodCode;

	@Column(name = "prod_name", length = 50)
	private String prodName;

	@Column(name = "prod_desc", length = 250)
	private String prodDesc;

	@Column(name = "user_id")
	private String userId;

	@Column(name = "priority")
	private Integer priority;

	@Column(name = "is_any_extra_cols")
	private Boolean isAnyExtraCols;

	@Column(name = "validity_type")
	private Integer validityType;

	@Column(name = "validity_period")
	private Integer validityPeriod;

	@Column(name = "version", length = 50)
	private String version;

	@Column(name = "speed")
	private Long speed;

	@Column(name = "fup_limit")
	private Long fupLimit;

	@Column(name = "eff_start_dt")
	private LocalDateTime effStartDt;

	@Column(name = "eff_end_dt")
	private LocalDateTime effEndDt;

	@Column(name = "logo_url", length = 500)
	private String logoUrl;

	@Column(name = "is_cust_visible")
	private Integer isCustVisible;

	@Column(name = "extended_data", length = 2000)
	private String extendedData;

	@ManyToMany
	@JoinTable(name = "product_location", joinColumns = @JoinColumn(name = "loc_id"), inverseJoinColumns = @JoinColumn(name = "prod_id"))
	private List<LocationLkp> locationLkp;

//    @OneToMany(mappedBy = "product")
//    private Set<ProductOffering> productOfferingProds;

//    @OneToMany(mappedBy = "product")
//    private Set<ProductExtraDetails> productExtraDetails;

//    @OneToMany(mappedBy = "product")
//    private Set<ProductPrice> productPrices;
}

//    @PrePersist
//    public void prePersist() {
//    	String genId = generateId();
//        this.id = genId;
//        this.srvcCode = genId;
//    }
//
//	private String generateId() {
//		StringBuilder sb = new StringBuilder("SVC");
//		Random random = new Random();
//		for (int i = 0; i < 5; i++) {
//			sb.append(random.nextInt(10));
//		}
//		return sb.toString();
//	}
