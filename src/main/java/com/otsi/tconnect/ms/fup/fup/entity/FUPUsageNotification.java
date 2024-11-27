package com.otsi.tconnect.ms.fup.fup.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "fup_usage_notification")
@Data
public class FUPUsageNotification {

	@EmbeddedId
	private FUPUsageNotificationID id;
	
	
	private String status;

}
