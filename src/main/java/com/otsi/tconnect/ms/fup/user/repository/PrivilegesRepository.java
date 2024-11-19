package com.otsi.tconnect.ms.fup.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.otsi.tconnect.ms.fup.user.entity.Privileges;


@Repository
public interface PrivilegesRepository extends JpaRepository<Privileges, Long>{

}
