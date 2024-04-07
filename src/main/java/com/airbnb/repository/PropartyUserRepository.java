package com.airbnb.repository;

import com.airbnb.entity.PropartyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PropartyUserRepository extends JpaRepository<PropartyUser, Long> {

    Optional<PropartyUser> findByUserName(String userName);
}