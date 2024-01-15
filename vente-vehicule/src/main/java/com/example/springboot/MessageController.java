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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
        ResponseEntity<HttpRetour> listes = new ResponseEntity<>(retour, HttpStatus.OK);
        return listes;
    }
}
