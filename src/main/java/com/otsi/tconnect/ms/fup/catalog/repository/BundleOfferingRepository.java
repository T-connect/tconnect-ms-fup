package com.otsi.tconnect.ms.fup.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.otsi.tconnect.ms.fup.catalog.entity.BundleOffering;

@Repository
public interface BundleOfferingRepository extends JpaRepository<BundleOffering, Long> {

}
