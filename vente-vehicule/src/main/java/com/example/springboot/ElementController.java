/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.springboot;

import classes.springboot.HttpRetour;
import classes.springboot.Modele;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import services.springboot.ServiceElement;

/**
 *
 * @author USER
 */
@RestController
public class ElementController {
    ServiceElement s = new ServiceElement();
    
    @CrossOrigin(origins = "*")
    @PostMapping("/InsertCategorie")
    public ResponseEntity<HttpRetour> insertCategorie(@RequestBody String nomCategorie) throws Exception {
        HttpRetour retour = s.insertCategorie(nomCategorie);
        ResponseEntity<HttpRetour> insertion = new ResponseEntity<>(retour, HttpStatus.OK);
        return insertion;
    }
    
    @CrossOrigin(origins = "*")
    @GetMapping("/ListeCategorie")
    public ResponseEntity<HttpRetour> listeCategorie() throws Exception {
        HttpRetour retour = s.listeCategorie();
        ResponseEntity<HttpRetour> listes = new ResponseEntity<>(retour, HttpStatus.OK);
        return listes;
    }
    
    @CrossOrigin(origins = "*")
    @DeleteMapping("/DeleteCategorie")
    public ResponseEntity<HttpRetour> deleteCategorie(@RequestParam String idCategorie) throws Exception {
        HttpRetour retour = s.deleteCategorie(idCategorie);
        ResponseEntity<HttpRetour> suppression = new ResponseEntity<>(retour, HttpStatus.OK);
        return suppression;
    }
    
    
    @CrossOrigin(origins = "*")
    @PostMapping("/InsertVitesse")
    public ResponseEntity<HttpRetour> insertVitesse(@RequestBody String boiteVitesse) throws Exception {
        HttpRetour retour = s.insertVitesse(boiteVitesse);
        ResponseEntity<HttpRetour> insertion = new ResponseEntity<>(retour, HttpStatus.OK);
        return insertion;
    }
    
    @CrossOrigin(origins = "*")
    @GetMapping("/ListeVitesse")
    public ResponseEntity<HttpRetour> listeVitesse() throws Exception {
        HttpRetour retour = s.listeVitesse();
        ResponseEntity<HttpRetour> listes = new ResponseEntity<>(retour, HttpStatus.OK);
        return listes;
    }
    
    @CrossOrigin(origins = "*")
    @DeleteMapping("/DeleteVitesse")
    public ResponseEntity<HttpRetour> deleteVitesse(@RequestParam String idVitesse) throws Exception {
        HttpRetour retour = s.deleteVitesse(idVitesse);
        ResponseEntity<HttpRetour> suppression = new ResponseEntity<>(retour, HttpStatus.OK);
        return suppression;
    }
    
    
    @CrossOrigin(origins = "*")
    @PostMapping("/InsertCarburant")
    public ResponseEntity<HttpRetour> insertCrburant(@RequestBody String nomCarbutant) throws Exception {
        HttpRetour retour = s.insertCarburant(nomCarbutant);
        ResponseEntity<HttpRetour> insertion = new ResponseEntity<>(retour, HttpStatus.OK);
        return insertion;
    }
    
    @CrossOrigin(origins = "*")
    @GetMapping("/ListeCarburant")
    public ResponseEntity<HttpRetour> listeCarburant() throws Exception {
        HttpRetour retour = s.listeCarburant();
        ResponseEntity<HttpRetour> listes = new ResponseEntity<>(retour, HttpStatus.OK);
        return listes;
    }
    
    @CrossOrigin(origins = "*")
    @DeleteMapping("/DeleteCarburant")
    public ResponseEntity<HttpRetour> deleteCarburant(@RequestParam String idCarburant) throws Exception {
        HttpRetour retour = s.deleteCarburant(idCarburant);
        ResponseEntity<HttpRetour> suppression = new ResponseEntity<>(retour, HttpStatus.OK);
        return suppression;
    }
    
    
    @CrossOrigin(origins = "*")
    @PostMapping("/InsertMarque")
    public ResponseEntity<HttpRetour> insertMarque(@RequestBody String nomMarque) throws Exception {
        HttpRetour retour = s.insertMarque(nomMarque);
        ResponseEntity<HttpRetour> insertion = new ResponseEntity<>(retour, HttpStatus.OK);
        return insertion;
    }
    
    @CrossOrigin(origins = "*")
    @GetMapping("/ListeMarque")
    public ResponseEntity<HttpRetour> listeMarque() throws Exception {
        HttpRetour retour = s.listeMarque();
        ResponseEntity<HttpRetour> listes = new ResponseEntity<>(retour, HttpStatus.OK);
        return listes;
    }
    
    @CrossOrigin(origins = "*")
    @DeleteMapping("/DeleteMarque")
    public ResponseEntity<HttpRetour> deleteMarque(@RequestParam String idMarque) throws Exception {
        HttpRetour retour = s.deleteMarque(idMarque);
        ResponseEntity<HttpRetour> suppression = new ResponseEntity<>(retour, HttpStatus.OK);
        return suppression;
    }
    
    
    @CrossOrigin(origins = "*")
    @PostMapping("/InsertModele")
    public ResponseEntity<HttpRetour> insertModele(@RequestBody Modele modele) throws Exception {
        HttpRetour retour = s.insertModele(modele.getIdMarque(), modele.getNom());
        ResponseEntity<HttpRetour> insertion = new ResponseEntity<>(retour, HttpStatus.OK);
        return insertion;
    }
    
    @CrossOrigin(origins = "*")
    @GetMapping("/ListeModele")
    public ResponseEntity<HttpRetour> listeModele() throws Exception {
        HttpRetour retour = s.listeModele();
        ResponseEntity<HttpRetour> listes = new ResponseEntity<>(retour, HttpStatus.OK);
        return listes;
    }
    
    @CrossOrigin(origins = "*")
    @DeleteMapping("/DeleteModele")
    public ResponseEntity<HttpRetour> deleteModele(@RequestParam String idModele) throws Exception {
        HttpRetour retour = s.deleteModele(idModele);
        ResponseEntity<HttpRetour> suppression = new ResponseEntity<>(retour, HttpStatus.OK);
        return suppression;
    }
}
