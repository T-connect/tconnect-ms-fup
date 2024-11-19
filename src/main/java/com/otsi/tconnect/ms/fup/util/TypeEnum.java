package com.otsi.tconnect.ms.fup.util;

public enum TypeEnum {

	TICKET ("TICKET"), LEAD ("LEAD");
	
	private String type;
	
	TypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
