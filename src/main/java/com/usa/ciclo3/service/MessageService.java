/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usa.ciclo3.service;

import com.usa.ciclo3.model.Message;
import com.usa.ciclo3.repository.MessageRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ale Giraldo
 */
@Service
public class MessageService {
    
    @Autowired
    private MessageRepository messageRepository;
    
    public List<Message> getAll(){
        return messageRepository.getAll();
    }
    
    public Optional<Message> getMessage(Integer id){
        return messageRepository.getMessage(id);
    }
    
    public Message save(Message message){
        if(message.getIdMessage() == null){
            return messageRepository.save(message);
        } else {
            Optional<Message> messageAux = messageRepository.getMessage(message.getIdMessage());
            if(messageAux.isEmpty()){
                return messageRepository.save(message);
            } else {
                return message;
            }
        }
    }
    
    public Message update(Message message){
        if(message.getIdMessage() == null){
            Optional<Message> messageAux = messageRepository.getMessage(message.getIdMessage());
            if(!messageAux.isEmpty()){
                if(message.getMessageText() != null){
                    messageAux.get().setMessageText(message.getMessageText());
                }
                messageRepository.save(messageAux.get());
                return messageAux.get();
            } else {
                return message;
            }
        } else {
            return message;
        }
    }
    
    public Boolean delMessage(Integer id){
        Boolean aBoolean = getMessage(id).map(message -> {messageRepository.delMessage(message);
        return true;
        }).orElse(false);
        return aBoolean;
    }
}
