package com.otsi.tconnect.ms.inventory.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "shipment")
public class Shipment {

	@Id
	@Column(name = "shipment_id")
	private String shipmentId;

	@ManyToOne
	@JoinColumn(name = "carrier_id")
	private Carrier carrier;

	@ManyToOne
	@JoinColumn(name = "device_order_id")
	private DeviceOrder deviceOrder;

	@Column(name = "shipment_type", length = 20)
	private String shipmentType;
	
	@Column(name = "quantity")
	private String quantity;

	@Column(name = "shipment_dt")
	private LocalDate shipmentDate;

	@Column(name = "delivery_dt")
	private LocalDate deliveryDate;

	@Column(name = "status", length = 20)
	private String status;

	@Column(name = "estimated_date_of_delivery")
	private LocalDate estimatedDateOfDelivery;
//
//	@Column(name="deviceName")
//	@ElementCollection(targetClass=String.class)
//	private List<String> deviceId;
//	
	@ManyToMany(cascade = CascadeType.REMOVE)
	@JoinTable(name = "shipment_device", joinColumns = @JoinColumn(name = "shipment_id"), inverseJoinColumns = @JoinColumn(name = "device_id"))
	private List<Device> device;
//
//
//	@PrePersist
//	public void prePersist() {
//		this.shipmentId = generateId();

//	private String generateId() {
//		StringBuilder sb = new StringBuilder("SHIP");
//		String randomSuffix = UUID.randomUUID().toString().replace("-", "").substring(0, 6);
//		sb.append(randomSuffix);
//		return sb.toString();
}
