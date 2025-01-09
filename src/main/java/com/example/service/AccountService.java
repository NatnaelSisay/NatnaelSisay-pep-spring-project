package com.example.service;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import com.example.util.ValidationUtil;

@Service
public class AccountService {
    AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    /**
     * Given username fetch Account information
     * 
     * @param username the account username
     * @return account with provided username or null if username doesn't exist
     */
    public Account getAccountByUserName(String username){
        Optional<Account> account = this.accountRepository.getAccountByUserName(username);
        return account.isPresent() ? account.get() : null;
    }

    /**
     * Save new account to database
     * 
     * @param account account object with username and password
     * @return HttpStatus.OK if account is saved else HttpStatus.BAD_REQUEST
     *  this could happen
     *  - the account is null or empty
     *  - invalid length of password
     *  - empty username
     *  - or account already exist by the same username
     */
    public HttpStatus save(Account account){
        if(!ValidationUtil.isValidAccount(account)){
            return HttpStatus.BAD_REQUEST;
        }
        
        Account existingAccount = this.getAccountByUserName(account.getUsername());
        if(existingAccount != null){
            // user already exists
            return HttpStatus.CONFLICT;
        }

        this.accountRepository.save(account);
        return HttpStatus.OK;
    }

    
    /**
     * Find account with the provided username and password information
     * 
     * @param account, account information with username and password
     * @return Optional of account or empty depending on wether the account exist or not
     */
    public Optional<Account> findAccountByUserNameAndPassword(Account account){
        return this.accountRepository.findAccountByUsernameAndPassword(account.getUsername(), account.getPassword());
    }

    public Optional<Account> findById(int account_id){
        return this.accountRepository.findById(account_id);
    }

    public Optional<Account> login(Account account){
        if(account == null){
            return Optional.empty();
        }

        Optional<Account> extractedAccount = this.accountRepository.findAccountByUsernameAndPassword(account.getUsername(), account.getPassword());

        if(extractedAccount.isEmpty()){
            return Optional.empty();
        }

        return extractedAccount;
    }

}
