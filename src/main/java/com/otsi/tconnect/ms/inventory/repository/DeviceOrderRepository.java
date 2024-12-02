package com.otsi.tconnect.ms.inventory.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.otsi.tconnect.ms.inventory.entity.Device;
import com.otsi.tconnect.ms.inventory.entity.DeviceOrder;

@Repository
public interface DeviceOrderRepository extends JpaRepository<DeviceOrder, String> {

	@Query(value = "select ord.* from device_order ord \r\n" + "Inner join dim_device d \r\n"
			+ "on ord.device_id = d.device_id where (ord.device_order_id=?1 and d.device_type_Name =?2 \r\n"
			+ "	and  ord.mso_id= ?3 and ord.lco_id=?4 and ord.quantity=?5)", nativeQuery = true)
	public DeviceOrder getDeviceOrder(String id, String deviceTypeName, String msoId, String lcoId, Long long1);

	public List<DeviceOrder> getDeviceOrderByLcoIdOrderByOrderCreateDateDesc(String id);

	public List<DeviceOrder> getDeviceOrderByMsoIdOrderByOrderCreateDateDesc(String id);

	public List<DeviceOrder> findByLcoId(String lcoId);

	public List<DeviceOrder> findByMsoId(String msoId);

	@Query(nativeQuery = true, value = "select * from device_order order by order_create_dt desc")
	public List<DeviceOrder> findAllDeviceOrdersOrderBycreateDateDesc();

//	@Query("SELECT do.deviceTypeName, COUNT(do) FROM DeviceOrder do WHERE do.createdDate BETWEEN :startDate AND :endDate GROUP BY do.deviceTypeName")
//	List<Object[]> countByDeviceTypeAndDateRange(@Param("startDate") LocalDate startDate,
//			@Param("endDate") LocalDate endDate);

	/*
	 * @Query(value = "SELECT device_type_name, COUNT(*) " + "FROM device_order " +
	 * "WHERE created_dt BETWEEN :startDate AND :endDate " +
	 * "GROUP BY device_type_name", nativeQuery = true) List<Object[]>
	 * countByDeviceTypeAndDateRange(@Param("startDate") LocalDate startDate,
	 * 
	 * @Param("endDate") LocalDate endDate);
	 */
	
	@Query(value = "SELECT device_type_name, COUNT(*) " +
            "FROM device_order " +
            "WHERE created_dt BETWEEN :startDate AND :endDate " +
            "AND (:lcoId IS NULL OR lco_id = :lcoId) " +
            "AND (:inventoryId IS NULL OR inventory_id = :inventoryId) " +
            "AND (:msoId IS NULL OR mso_id = :msoId) " +
            "GROUP BY device_type_name", 
    nativeQuery = true)
List<Object[]> countByDeviceTypeAndDateRange(@Param("startDate") LocalDate startDate, 
                                          @Param("endDate") LocalDate endDate,
                                          @Param("lcoId") String lcoId,
                                          @Param("inventoryId") String inventoryId,
                                          @Param("msoId") String msoId);
}
