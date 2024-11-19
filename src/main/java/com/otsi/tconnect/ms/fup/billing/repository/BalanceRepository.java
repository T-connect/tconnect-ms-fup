package com.otsi.tconnect.ms.fup.billing.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.otsi.tconnect.ms.fup.billing.entity.Balance;


@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long> {
	
	Optional<Balance> findByAcntId(Long acntId);

}
