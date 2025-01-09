package com.example.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.entity.Account;
import com.example.service.AccountService;

/**
This class is responsible for account registration and Authentication
 */
@Controller
public class SocialMediaController {
    private final AccountService accountService;

    public SocialMediaController(AccountService accountService){
        this.accountService = accountService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerAccount(@RequestBody Account account){
        HttpStatus status = this.accountService.save(account);
        return ResponseEntity.status(status).build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Account account){
        Optional<Account> loggedInAccount = this.accountService.login(account);
        
        if(loggedInAccount.isEmpty()){
            return ResponseEntity.status(401).build();
        }

        return ResponseEntity.status(200).build();
    }
}
