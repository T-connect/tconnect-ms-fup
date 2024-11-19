package com.otsi.tconnect.ms.fup.catalog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.otsi.tconnect.ms.fup.catalog.entity.Product;
import com.otsi.tconnect.ms.fup.catalog.entity.ProductPrice;


@Repository
public interface ProductPriceRepository extends JpaRepository<ProductPrice, Long> {

	List<ProductPrice> findByProduct(Product product);
}
