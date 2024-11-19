package com.otsi.tconnect.ms.fup.catalog.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "bundle_offering")
@Data
public class BundleOffering {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "bundle_gen")
	@SequenceGenerator(name = "bundle_gen", sequenceName = "bundle_seq", initialValue = 200000, allocationSize = 1)
	@Column(name = "bundle_offrng_id")
	private Long id;

	@Column(name = "bundle_offrng_code", length = 50)
	private String bundleOffrngCode;

	@Column(name = "bundle_offrng_name", length = 50)
	private String bundleOffrngName;

	@Column(name = "bundle_offrng_desc", length = 250)
	private String bundleOffrngDesc;

	@Column(name = "user_id", length = 10)
	private String userId;

	@Column(name = "version")
	private String version;

	@Column(name = "eff_start_dt")
	private LocalDateTime effStartDt;

	@Column(name = "eff_end_dt")
	private LocalDateTime effEndDt;

//	@Column(name = "prod_offrng_id")
//	private Long prodOffrngId;

	@Column(name = "extended_data", length = 2000)
	private String extendedData;

	@ManyToMany
	@JoinTable(name = "bundle_product", joinColumns = @JoinColumn(name = "bundle_offrng_id"), inverseJoinColumns = @JoinColumn(name = "prod_id"))
	private List<Product> products;

//    @PrePersist
//    public void prePersist() {
//        this.id = generateId();
//    }
//
//	private static String generateId() {
//		StringBuilder sb = new StringBuilder("BND");
//		Random random = new Random();
//		for (int i = 0; i < 5; i++) {
//			sb.append(random.nextInt(10));
//		}
//		return sb.toString();
//	}

}
