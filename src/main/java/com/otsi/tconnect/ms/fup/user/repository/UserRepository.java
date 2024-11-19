package com.otsi.tconnect.ms.fup.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.otsi.tconnect.ms.fup.user.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByMobileNumberOrEmail(String mobileNumber, String email);

    Boolean existsByMobileNumber(String mobileNumber);

    Boolean existsByEmail(String email);
    
    Optional<User> findByMobileNumber(String mobileNumber);
    
    Optional<User> findByUserId(String partnerNumber);

}
