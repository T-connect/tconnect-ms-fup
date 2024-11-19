package com.otsi.tconnect.ms.fup.catalog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.otsi.tconnect.ms.fup.catalog.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("SELECT p FROM Product p WHERE p.prodType.id = :prodTypeId")
	List<Product> findAllProductsByProdTypeId(@Param("prodTypeId") Integer prodTypeId);

//    @Query("SELECT new com.otsi.tconnect.ms.catalog.dto.ServiceDTO(s.srvcName, s.serviceTypeName, s.chargeType, s.description, s.prior, s.billInAdv, s.prorationOnStart, s.prorationOnEnd, s.priceCatg, s.genreId, s.price, s.sgst, s.cgst, s.taxId, s.languageId, s.custType, s.custCatg, s.district, s.partnerId, s.userId, s.chgDt, s.version, s.srvcStartDt, s.srvcEndDt, s.validity, s.createdDt, s.createdBy, s.modifiedDt, s.modifiedBy) FROM Services s")
//    List<ServiceDTO> findCustomServices();
//	
}