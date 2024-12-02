package com.otsi.tconnect.ms.inventory.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.otsi.tconnect.ms.inventory.dto.DeviceDTO;
import com.otsi.tconnect.ms.inventory.entity.Device;
import com.otsi.tconnect.ms.inventory.entity.DeviceType;


@Repository
public interface DeviceTypeRepository extends JpaRepository<DeviceType, String> {
	
	Optional<DeviceType> findByDeviceTypeId(String deviceTypeId);

	DeviceType findByDeviceTypeName(String deviceTypeName);

	Optional<Device> findByDeviceTypeId(DeviceDTO deviceDTO);
	
	}


