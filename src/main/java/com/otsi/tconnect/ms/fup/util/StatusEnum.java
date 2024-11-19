package com.otsi.tconnect.ms.fup.util;

public enum StatusEnum {

	ACTIVE("ACTIVE"), INACTIVE("INACTIVE");

	private String type;

	StatusEnum(String type) {
        this.type = type;
    }

	public String getType() {
		return type;
	}
}
