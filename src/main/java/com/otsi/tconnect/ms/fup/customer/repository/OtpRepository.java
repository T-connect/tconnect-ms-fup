package com.otsi.tconnect.ms.fup.customer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.otsi.tconnect.ms.fup.customer.entity.Otp;

@Repository
public interface OtpRepository extends JpaRepository<Otp, Long> {

	Optional<Otp> findByPhoneNumber(String phoneNumber);

	Otp findByPhoneNumberAndOtp(String phoneNumber, String otp);
}
