package com.otsi.tconnect.ms.fup.fup.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.otsi.tconnect.ms.fup.fup.entity.FUPRecord;

@Repository
public interface FUPRecordRepository extends JpaRepository<FUPRecord, Long> {

	List<FUPRecord> findByDevice(String device);

	@Query("SELECT DISTINCT fup.device FROM FUPRecord fup")
	List<String> getAllDeviceIds();

	@Query("SELECT fup FROM FUPRecord fup WHERE fup.device = :device and fup.eventTimestamp BETWEEN :startTimestamp AND :endTimestamp")
	List<FUPRecord> findRecordsByDeviceIdAndTimeInRange(@Param("device") String device, @Param("startTimestamp") Long startTimestamp,
			@Param("endTimestamp") Long endTimestamp);

}
