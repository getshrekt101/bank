package com.algomau.bank.domain.repository;

import com.algomau.bank.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, UUID> {
    UserAccount findByUserName(String username);
}
