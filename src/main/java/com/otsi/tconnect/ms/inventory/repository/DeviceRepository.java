package com.otsi.tconnect.ms.inventory.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.otsi.tconnect.ms.inventory.entity.Device;
import com.otsi.tconnect.ms.inventory.entity.DeviceType;


public interface DeviceRepository extends JpaRepository<Device, String> {

	List<Device> findByLcoId(String lcoId);

	List<Device> findByMsoId(String msoId);

	List<Device> findByinventoryId(String msoId);

//	List<Device> findAllOrderByEntryDateDesc();

	@Query(nativeQuery = true, value = "select * from device order by entry_dt desc")
	List<Device> findAllDeviceOrderByEntryDateDesc();

	//List<Device> findByMsoIdAndStatTus(String partnerId, String string);

	List<Device> findByMsoIdAndStatus(String msoId, String string);

	List<Device> findByInventoryIdAndStatus(String inventoryId, String string);

	List<Device> findByStatus(String string);

	List<Device> findByOrderId(String orderId);
	
    List<Device> findByDeviceType(DeviceType deviceType);
    
    @Query("SELECT d.status, COUNT(d) FROM Device d WHERE d.entryDate BETWEEN :startDate AND :endDate AND d.status IN ('Assigned', 'In-Stock', 'Delivered', 'Shipped') GROUP BY d.status")
    List<Object[]> countByStatusAndDateRange(LocalDate startDate, LocalDate endDate);

    
	List<Device> findByMsoIdAndStatusAndEntryDateBetween(String partnerId, String string, LocalDate startDate,
			LocalDate endDate);
	
	@Query("SELECT d FROM Device d WHERE d.createdBy = :createdBy AND d.entryDate BETWEEN :startDate AND :endDate")
	List<Device> findByCreatedByAndEntryDateBetween(
	        @Param("createdBy") String createdBy, 
	        @Param("startDate") LocalDate startDate,  
	        @Param("endDate") LocalDate endDate);

	Optional<Device> findByDeviceId(String deviceId);


	Optional<Device> findByDeviceIdAndCustomerNumberAndLcoId(String deviceId, String customerNumber, String lcoId);

	@Query("SELECT d FROM Device d WHERE d.lcoId = :lcoId AND d.deviceId IN :deviceIds")
	List<Device> findByLcoIdAndDeviceIdIn(@Param("lcoId") String lcoId, @Param("deviceIds") List<String> deviceIds);

	List<Device> findByCustomerNumber(String customerNumber);

	Optional<Device> findByMacAddress(String device);

	//Device findByDeviceId(Device device);

	
}
