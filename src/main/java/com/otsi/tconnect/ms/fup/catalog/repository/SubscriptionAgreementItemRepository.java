package com.otsi.tconnect.ms.fup.catalog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.otsi.tconnect.ms.fup.catalog.entity.SubscriptionAgreement;
import com.otsi.tconnect.ms.fup.catalog.entity.SubscriptionAgreementItem;

@Repository
public interface SubscriptionAgreementItemRepository extends JpaRepository<SubscriptionAgreementItem, Long> {

//	@Query(value = "SELECT * FROM partner WHERE user_name LIKE %?1% " +
//            "OR mobile LIKE %?1% OR phone LIKE %?1% " +
//            "OR address_1 LIKE %?1% OR address_2 LIKE %?1% ",
//            
//            countQuery = "SELECT count(*) FROM partner WHERE user_name LIKE %?1% " +
//                         "OR address_2 LIKE %?1% OR address_1 LIKE %?1% " +
//                         " OR mobile LIKE %?1% "
//                         ,
//            nativeQuery = true)
	// Page<SubscriptionDetails> findByKeyword(@Param("searchKey") String searchKey,
	// Pageable pageable);

	List<SubscriptionAgreementItem> findBySubscriptionAgreement(SubscriptionAgreement subscriptionAgreement);

//	@Query("SELECT sd FROM SubscriptionDetails sd WHERE sd.subscription.id = :subscriptionId")
	@Query("SELECT sd FROM SubscriptionAgreementItem sd WHERE sd.subscriptionAgreement.id = :subscriptionId")
	List<SubscriptionAgreementItem> findBySubscriptionAgreementId(@Param("subscriptionId") Long subscriptionId);

	List<SubscriptionAgreementItem> findBySubscriptionAgreementIdAndProductOfferingId(Long subscriptionId, Long productOfferingId);
}
