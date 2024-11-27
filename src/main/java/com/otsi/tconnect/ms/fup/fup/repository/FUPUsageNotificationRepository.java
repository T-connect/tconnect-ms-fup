package com.otsi.tconnect.ms.fup.fup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.otsi.tconnect.ms.fup.fup.entity.FUPUsageNotification;
import com.otsi.tconnect.ms.fup.fup.entity.FUPUsageNotificationID;

@Repository
public interface FUPUsageNotificationRepository extends JpaRepository<FUPUsageNotification, FUPUsageNotificationID> {

}
