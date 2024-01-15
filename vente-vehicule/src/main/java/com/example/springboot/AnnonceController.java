/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.springboot;

import classes.springboot.HttpRetour;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import services.springboot.ServiceAnnonceProduit;

/**
 *
 * @author USER
 */
@RestController
public class AnnonceController {
    ServiceAnnonceProduit s = new ServiceAnnonceProduit();
    
    @CrossOrigin(origins = "*")
    @GetMapping("/AjoutAnnonce")
    public ResponseEntity<HttpRetour> ajoutAnnonceProduit(@RequestHeader(name = "Authorization") String token, @RequestParam String idMarque, 
            @RequestParam String idModele, @RequestParam String matricule, @RequestParam String idVitesse, @RequestParam String idCategorie, 
            @RequestParam String idCarburant, @RequestParam String kilometrage, @RequestParam String anneeSortie, @RequestParam String couleur, 
            @RequestParam String nbrPlace, @RequestParam String prix, @RequestParam String description) 
            throws Exception {
        
        String realToken = token.substring(7, token.length());
        HttpRetour retour = s.ajoutAnnonceProduit(realToken, idMarque, idModele, matricule, idVitesse, idCategorie, idCarburant, kilometrage, 
                anneeSortie, couleur, nbrPlace, prix, description);
        
        ResponseEntity<HttpRetour> ajout = new ResponseEntity<>(retour, HttpStatus.OK);
        return ajout;
    }
    
    @CrossOrigin(origins = "*")
    @PutMapping("/ValiderAnnonce")
    public ResponseEntity<HttpRetour> validerAnnonce(@RequestHeader(name = "Authorization") String token, @RequestParam String idAnnonce) 
            throws Exception {
        String realToken = token.substring(7, token.length());
        HttpRetour retour = s.validerAnnonce(realToken, idAnnonce);
        ResponseEntity<HttpRetour> validation = new ResponseEntity<>(retour, HttpStatus.OK);
        return validation;
    }
    
    @CrossOrigin(origins = "*")
    @PutMapping("/FavoriserAnnonce")
    public ResponseEntity<HttpRetour> favoriserAnnonce(@RequestHeader(name = "Authorization") String token, @RequestParam String idAnnonce) 
            throws Exception {
        String realToken = token.substring(7, token.length());
        HttpRetour retour = s.favoriserAnnonce(realToken, idAnnonce);
        ResponseEntity<HttpRetour> favorisation = new ResponseEntity<>(retour, HttpStatus.OK);
        return favorisation;
    }
    
    @CrossOrigin(origins = "*")
    @PutMapping("/ModifierStatutAnnonce")
    public ResponseEntity<HttpRetour> modifierStatutAnnonce(@RequestHeader(name = "Authorization") String token, @RequestParam String idAnnonce) 
            throws Exception {
        String realToken = token.substring(7, token.length());
        HttpRetour retour = s.ModifierStatutAnnonce(realToken, idAnnonce);
        ResponseEntity<HttpRetour> favorisation = new ResponseEntity<>(retour, HttpStatus.OK);
        return favorisation;
    }
    
    @CrossOrigin(origins = "*")
    @GetMapping("/ListeAnnonceNonValider")
    public ResponseEntity<HttpRetour> listeAnnonceNonValider() throws Exception {
        HttpRetour retour = s.listeAnnonceNonValider();
        ResponseEntity<HttpRetour> listes = new ResponseEntity<>(retour, HttpStatus.OK);
        return listes;
    }
    
    @CrossOrigin(origins = "*")
    @GetMapping("/ListeAnnonceValider")
    public ResponseEntity<HttpRetour> listeAnnonceValider() throws Exception {
        HttpRetour retour = s.listeAnnonceValider();
        ResponseEntity<HttpRetour> listes = new ResponseEntity<>(retour, HttpStatus.OK);
        return listes;
    }
    
    @CrossOrigin(origins = "*")
    @GetMapping("/HistoriqueAnnonce")
    public ResponseEntity<HttpRetour> historiqueAnnonce() throws Exception {
        HttpRetour retour = s.historiqueAnnonce();
        ResponseEntity<HttpRetour> listes = new ResponseEntity<>(retour, HttpStatus.OK);
        return listes;
    }
    
    @CrossOrigin(origins = "*")
    @GetMapping("/ListeAnnonceFavoris")
    public ResponseEntity<HttpRetour> listeAnnonceFavoris() throws Exception {
        HttpRetour retour = s.listeAnnonceFavoris();
        ResponseEntity<HttpRetour> listes = new ResponseEntity<>(retour, HttpStatus.OK);
        return listes;
    }
    
    @CrossOrigin(origins = "*")
    @GetMapping("/Recherche")
    public ResponseEntity<HttpRetour> recherche(@RequestParam String marque, @RequestParam String modele, @RequestParam String boiteVitesse, 
            @RequestParam String categorie, @RequestParam String typeCarburant, @RequestParam String couleur, @RequestParam String prixMin, 
            @RequestParam String prixMax) throws Exception {
        HttpRetour retour = s.listeAnnonceValider(marque, modele, boiteVitesse, categorie, typeCarburant, couleur, prixMin, prixMax);
        ResponseEntity<HttpRetour> listes = new ResponseEntity<>(retour, HttpStatus.OK);
        return listes;
    }
}
