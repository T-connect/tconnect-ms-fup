package com.otsi.tconnect.ms.fup.user.entity;

import lombok.Getter;

@Getter
public enum RoleType {
	//ADMIN,MSO,LCO,FIELDOPERATOR
	SUPERADMIN(1, "SUPER Administrator"),
    ADMIN(1, "Administrator"),
    MSO(2, "Multiple System Operator"),
    LCO(3, "Local Cable Operator"),
    ACCOUNTANT(4,"Accountant"),
    FIELDOPERATOR(5, "Field Operator"),
	INVENTORY(6,"Inventory"),
	CUSTOMER(7,"Customer");

    private final int id;
    private final String displayName;
    

    RoleType(int id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }
    
}
