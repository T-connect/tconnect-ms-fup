package com.otsi.tconnect.ms.fup.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.otsi.tconnect.ms.fup.customer.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
