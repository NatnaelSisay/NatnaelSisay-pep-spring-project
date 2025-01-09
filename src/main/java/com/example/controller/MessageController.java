package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entity.Message;
import com.example.service.MessageService;

@Controller
@RequestMapping("/messages")
public class MessageController {

    MessageService messageService;
    public MessageController(MessageService messageService){
        this.messageService = messageService;
    }

    @GetMapping
    public ResponseEntity<List<Message>> findAll(){
        Optional<List<Message>> result = this.messageService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(result.get());
    }

    @GetMapping("/{messageId}")
    public ResponseEntity<Message> findById(@PathVariable("messageId") int messageId){
        Optional<Message> result = this.messageService.findById(messageId);
        return ResponseEntity.status(HttpStatus.OK).body(result.get());
    }

    @GetMapping("/accounts/{accountId}/messages")
    public ResponseEntity<List<Message>> findAccountMessages(@PathVariable("accountId") int accountId){
        Optional<List<Message>> result = this.messageService.findAccountMessages(accountId);
        return ResponseEntity.status(HttpStatus.OK).body(result.get());
    }

    @PostMapping
    public ResponseEntity<Message> save(@RequestBody Message message){
        Optional<Message> result = this.messageService.save(message);
        
        if(result.isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        return ResponseEntity.status(HttpStatus.OK).body(result.get());
    }

    @PatchMapping("/{messageId}")
    @PutMapping("/{messageId}")
    public ResponseEntity<Message> updateMessage(@PathVariable("messageId") int messageId, @RequestBody Message message){
        Optional<Message> result = this.messageService.updateMessage(messageId, message);
        return ResponseEntity.status(HttpStatus.OK).body(result.get());
    }
    
    @DeleteMapping("/{messageId}")
    public ResponseEntity<?> delete(@PathVariable("messageId") int messageId){
        Optional<Message> result = this.messageService.delete(messageId);
        return ResponseEntity.status(HttpStatus.OK).body(result.get());
    }
    
}
