package com.otsi.tconnect.ms.inventory.dto;

import java.time.LocalDate;
import java.util.List;

import com.otsi.tconnect.ms.inventory.entity.Carrier;
import com.otsi.tconnect.ms.inventory.entity.Device;

import lombok.Data;

@Data
public class ShipmentDTO {

	private String shipmentId;

	private Carrier carrier;

	//private DeviceOrder deviceOrder;

	private String shipmentType;

	private LocalDate shipmentDate;

	private LocalDate deliveryDate;

	private String status;
	
	private String quantity;

	private LocalDate estimatedDateOfDelivery;
	
	private List<Device> device;

}
