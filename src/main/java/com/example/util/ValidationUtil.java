package com.example.util;

import com.example.entity.Account;
import com.example.entity.Message;

public class ValidationUtil {
    public static boolean isValidAccount(Account account){
        return !(account.getUsername().isEmpty() || account.getPassword().length() < 4);
    }

    public static boolean isValidMessage(Message message){
        return !(message.getMessageText().isEmpty() || message.getMessageText().length() > 255);
    }
    
}
