package com.otsi.tconnect.ms.fup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WebSocketResponse {

	private String custId;
	private String message;

}
