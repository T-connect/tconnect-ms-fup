package com.otsi.tconnect.ms.fup.catalog.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.otsi.tconnect.ms.fup.catalog.entity.FubTemplate;

@Repository
public interface FubTemplateRepository extends JpaRepository<FubTemplate, Long> {
	
	
	@Query("SELECT f FROM FubTemplate f WHERE f.isDefault = :isDefault")
    Optional<FubTemplate> findOne(@Param("isDefault") Boolean isDefault);
	
	
   
}
