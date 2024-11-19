package com.otsi.tconnect.ms.fup.customer.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.otsi.tconnect.ms.fup.customer.entity.TicketsEntity;
import com.otsi.tconnect.ms.fup.util.TypeEnum;

@Repository
public interface TicketsRepository extends JpaRepository<TicketsEntity, Serializable>{

	List<TicketsEntity> findByCustomerId(Long customerId);

	List<TicketsEntity> findByPartnerId(String partnerId);

	List<TicketsEntity> findByType(TypeEnum lead);

	List<TicketsEntity> findByCustomerIdAndType(Long customerId, TypeEnum ticket);

}
