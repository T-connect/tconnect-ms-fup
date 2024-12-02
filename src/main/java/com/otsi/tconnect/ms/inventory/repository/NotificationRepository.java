package com.otsi.tconnect.ms.inventory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.otsi.tconnect.ms.inventory.entity.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long>  {

	List<Notification> findByCustomerId(String customerId);

	List<Notification> findByPartnerId(String partnerId);
	
	List<Notification> findByCustomerIdOrPartnerId(String customerId, String partnerId);

	//Notification findByPartnerIdNotificationId(String partnerId, String notificationId);

	Notification findByPartnerIdAndNotificationId(String partnerId, Long notificationId);
    List<Notification> findByPartnerIdAndCustomerId(String partnerId, String customerId);

}
