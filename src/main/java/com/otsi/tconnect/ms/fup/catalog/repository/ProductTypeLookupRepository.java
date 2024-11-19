package com.otsi.tconnect.ms.fup.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.otsi.tconnect.ms.fup.catalog.entity.ProdTypeLkp;

@Repository
public interface ProductTypeLookupRepository extends JpaRepository<ProdTypeLkp, Integer> {

}
