package com.example.controller;

import java.util.List;

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

@Controller
@RequestMapping("/messages")
public class MessageController {

    @GetMapping
    public ResponseEntity<List<Message>> findAll(){
        return null;
    }

    @GetMapping("/{message_id}")
    public ResponseEntity<Message> findById(@PathVariable("message_id") int message_id){
        return null;
    }

    @GetMapping("/accounts/{account_id}/messages")
    public ResponseEntity<List<Message>> findAccountMessages(@PathVariable("account_id") int account_id){
        return null;
    }

    @PostMapping
    public ResponseEntity<Message> save(@RequestBody Message message){
        return null;
    }

    @PatchMapping("/{message_id}")
    @PutMapping("/{message_id}")
    public ResponseEntity<Message> updateMessage(@PathVariable("message_id") int message_id, @RequestBody Message message){
        return null;
    }
    
    @DeleteMapping("/{message_id}")
    public ResponseEntity<?> delete(@PathVariable("message_id") int message_id){
        return null;
    }
    
}
