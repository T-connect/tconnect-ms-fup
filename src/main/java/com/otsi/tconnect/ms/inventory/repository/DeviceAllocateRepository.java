package com.otsi.tconnect.ms.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.otsi.tconnect.ms.inventory.entity.DeviceAllocate;

@Repository
public interface DeviceAllocateRepository extends JpaRepository<DeviceAllocate, String> {

}
