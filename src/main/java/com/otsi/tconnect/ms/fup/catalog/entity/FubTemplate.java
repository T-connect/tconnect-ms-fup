package com.otsi.tconnect.ms.fup.catalog.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "fub_template")
@Data
public class FubTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")  
    private Long id;

    @Column(name = "PLAN_NAME")  
    private String planName;

    @Column(name = "FREQUENCY") 
    private String frequency;

    @Column(name = "FUP_SPEED") 
    private String fupSpeed;

    @Column(name = "DATA_LIMIT")  
    private String dataLimit;

    @Column(name = "IS_DEFAULT")  
    private Boolean isDefault;

   
}
