package com.otsi.tconnect.ms.inventory.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class NotificationDTO {

	private Long notificationId;
	private String message;
	private String desc;
	private String partnerId;
	private String customerId;
	private LocalDate createdDate;
	private boolean read;
}
