package com.otsi.tconnect.ms.fup.partner.entity;

import java.util.Date;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "partner_type_lookup")
public class PartnerTypeLookup {

    @Id
    @Column(name = "partner_type_id")
    @GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String partnerTypeId;

    @Column(name = "partner_type_name")
    private String partnerTypeName;

    @Column(name = "description")
    private String description;

    @Column(name = "created_dt")
    @Temporal(TemporalType.DATE)
    private Date createdDt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "modified_dt")
    @Temporal(TemporalType.DATE)
    private Date modifiedDt;

    @Column(name = "modified_by")
    private String modifiedBy;
    
}