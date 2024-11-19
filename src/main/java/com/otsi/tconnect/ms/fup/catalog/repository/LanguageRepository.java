package com.otsi.tconnect.ms.fup.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.otsi.tconnect.ms.fup.catalog.entity.LocationLkp;

@Repository
public interface LanguageRepository extends JpaRepository<LocationLkp, Integer> {

}