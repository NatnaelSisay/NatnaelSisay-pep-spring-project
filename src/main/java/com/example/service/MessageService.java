package com.example.service;


import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.repository.MessageRepository;
import com.example.util.GeneralUtil;
import com.example.util.ValidationUtil;

/**
 * Provide methods for Message class to interact with the database and perform buisness logic
 */
@Service
@Transactional
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

    public int delete(int message_id){
        Optional<Message> message = this.messageRepository.findById(message_id);
        
        if(message.isEmpty()) return 0;
        
        return this.messageRepository.deleteById(message_id);
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

    public int updateMessage(int message_id, Message message){
        if(!ValidationUtil.isValidMessage(message)){
            return 0;
        }

        return this.messageRepository.updateMessage(message_id, message.getMessageText());
    }
}
