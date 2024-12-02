package com.otsi.tconnect.ms.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.otsi.tconnect.ms.inventory.entity.Shipment;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, String> {

	
	public Shipment findByDeviceOrderId(String id);
}
