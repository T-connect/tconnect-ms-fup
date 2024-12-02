package com.otsi.tconnect.ms.inventory.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

/**
 * A DTO for the {@link com.otsi.tconnect.ms.crm.domain.Customer} entity.
 */
@Data
public class CustomerDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String custNo;

	private Long custTypeId;

	private Long custClassId;

	private String salutation;

	private String firstName;

	private String middleName;

	private String lastName;

	private String orgName;

	private String gstNo;

	private String gender;

	private LocalDate dob;

	private String emailId;

	private String notificationPreferences;

	private Boolean vipFlag;

	private String userId;

	private String partnerId;

	private String phone1;

	private String phone2;

	private String relationType;

	private String relationName;

	private String docType1;

	private String docType2;

	private String kycStatus;

	private String kycMode;

	private String kycRefNo;

//	private Status status;

	private String extendedData;

	private LocalDateTime createDt;

	private String createdBy;

	private LocalDateTime modifyDt;

	private String modifiedBy;

//	private List<AccountDto> accounts;
//	
//	private Boolean paymentStatus;
//	
//	private PaymentDTO paymentDto;

}
