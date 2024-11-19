package com.otsi.tconnect.ms.fup.customer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.otsi.tconnect.ms.fup.customer.entity.Account;



@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

	Optional<Account> findBySubscriptionId(Long subscriptionId);
	
	List<Account> findByBillPeriod(String billPeriod);

	Optional<Account> findByCustNo(String customerNo);
}
