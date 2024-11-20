package com.otsi.tconnect.ms.fup.fup.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.otsi.tconnect.ms.fup.fup.entity.FUPRecord;

@Repository
public interface FUPRecordRepository extends JpaRepository<FUPRecord, Long> {
	
	
	List<FUPRecord> findByDevice(String device);

}
