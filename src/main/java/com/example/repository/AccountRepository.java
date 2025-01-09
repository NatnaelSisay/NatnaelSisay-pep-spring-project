package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer>{
    // TODO: 
    @Query("SELECT a FROM Account a WHERE username=:username")
    Optional<Account> getAccountByUserName(String username);

    // TODO:
    // @Query("SELECT a FROM Account a WHERE username=:username AND password=:password")
    Optional<Account> findAccountByUsernameAndPassword(String username, String password);
}
