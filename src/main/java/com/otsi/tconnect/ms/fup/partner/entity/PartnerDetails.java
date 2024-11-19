package com.otsi.tconnect.ms.fup.partner.entity;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "partner_details")
@Data
public class PartnerDetails {
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "partner_detail_id")
	private String id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "partner_id", nullable = false)
	@JsonBackReference
	private Partner partner;

	@Column(name = "communication_add_flatno_plotno")
	private String communicationAddFlatnoPlotno;

	@Column(name = "communication_add_street_colony")
	private String communicationAddStreetColony;

	@Column(name = "communication_add_area")
	private String communicationAddArea;

	@Column(name = "communication_add_pincode")
	private String communicationAddPincode;

	@Column(name = "communication_city")
	private String communicationCity;

	@Column(name = "communication_state")
	private String communicationState;

	@Column(name = "billing_address_flatno_plotno")
	private String billingAddressFlatnoPlotno;

	@Column(name = "billing_address_street_colony")
	private String billingAddressStreetColony;

	@Column(name = "billing_address_area")
	private String billingAddressArea;

	@Column(name = "billing_address_pincode")
	private String billingAddressPin;

}