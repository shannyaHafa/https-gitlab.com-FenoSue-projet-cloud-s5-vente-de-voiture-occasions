/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.springboot;

import classes.springboot.HttpRetour;
import classes.springboot.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import services.springboot.ServiceMessage;

/**
 *
 * @author USER
 */
@RestController
public class MessageController {
    ServiceMessage s = new ServiceMessage();
    
    @CrossOrigin(origins = "*")
    @PostMapping("/EnvoieMessage")
    public ResponseEntity<HttpRetour> envoyerMessage(@RequestBody Message message) 
            throws Exception { 
        HttpRetour retour = s.envoyerMessage(message.getIdUserDestinateur(), message.getIdUserDestinataire(), message.getMessage());
        ResponseEntity<HttpRetour> envoie = new ResponseEntity<>(retour, HttpStatus.OK);
        return envoie;
    }
    
    @CrossOrigin(origins = "*")
    @GetMapping("/Discussion")
    public ResponseEntity<HttpRetour> getDiscussion(@RequestHeader(name = "Authorization") String token, @RequestParam String idUserDestinateur, 
            @RequestParam String idUserDestinataire) throws Exception {
        String realToken = token.substring(7, token.length());
        HttpRetour retour = s.getDiscussion(realToken, idUserDestinateur, idUserDestinataire);
        ResponseEntity<HttpRetour> messages = new ResponseEntity<>(retour, HttpStatus.OK);
        return messages;
    }
}
