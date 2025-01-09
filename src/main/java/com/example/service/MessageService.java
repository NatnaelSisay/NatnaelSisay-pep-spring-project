package com.example.service;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.repository.MessageRepository;
import com.example.util.GeneralUtil;
import com.example.util.ValidationUtil;
import com.fasterxml.jackson.core.JsonProcessingException;


/**
 * Provide methods for Message class to interact with the database and perform buisness logic
 */
@Service
public class MessageService {
    MessageRepository messageRepository;
    AccountService accountService;

    public MessageService(MessageRepository messageRepository, AccountService accountService){
        this.messageRepository = messageRepository;
        this.accountService = accountService;
    }

    public Optional<Message> save(Message message){
        if(!GeneralUtil.isMessageValid(message)){
            return Optional.empty();
        }

        // check if the user id exist in database
        Optional<Account> userAccount = this.accountService.findById(message.getPostedBy());
        if(!userAccount.isPresent()) return Optional.empty();

        Message result = this.messageRepository.save(message);
        return result == null ? Optional.empty() : Optional.of(result);
    }

    public Optional<Message> delete(int message_id){
        Optional<Message> message = this.messageRepository.findById(message_id);
        
        if(message.isEmpty()){
            return Optional.empty();
        }

        this.messageRepository.deleteById(message_id);
        return message;
    }

    public Optional<List<Message>> findAccountMessages(int account_id){
        return this.messageRepository.findMessageBypostedBy(account_id);
    }

    public Optional<List<Message>> findAll(){
        return Optional.of(this.messageRepository.findAll());
    }

    public Optional<Message> findById(int message_id){
        return this.messageRepository.findById(message_id);
    }

    public Optional<Message> updateMessage(int message_id, Message message){

        if(!ValidationUtil.isValidMessage(message)){
            return Optional.empty();
        }

        int rowsUpdated = this.messageRepository.update(message_id, message.getMessageText());
        if(rowsUpdated == 0) return Optional.empty();

        return findById(message_id);
    }
}
