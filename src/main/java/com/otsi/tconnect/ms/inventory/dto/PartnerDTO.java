package com.otsi.tconnect.ms.inventory.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
@Data
public class PartnerDTO implements Serializable {

	/**
	 * A DTO for the {@link com.otsi.tconnect.ms.partner.domain.Partner} entity.
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	private String partnerNumber;
	
	private String parentPartnerId;

	private String salutation;

	private String firstName;

	private String middleName;

	private String lastName;

	private String partnerName;
	
	private Long partnerType;
	
	//private String roleId;
	private List<String> roleIds; 

	private String description;

	private String gstNo;

	private String flatNo;

	private String street;

	private String area;

	private String state;

	private String country;

	private Integer pincode;

	private String phone1;

	private String phone2;

	private String landlineNo;

	private String emailId;

	private String kycStatus;

	private String kycMode;

	private String kycRefNo;

	private String status;

	private Date startDt;

	private Date endDt;
	
	private Boolean isActive;

	//private List<PartnerDetailsDTO> partnerDetailsDTO;

}
