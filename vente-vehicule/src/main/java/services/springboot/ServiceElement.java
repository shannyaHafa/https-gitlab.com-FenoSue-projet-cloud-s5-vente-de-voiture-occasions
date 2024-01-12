/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.springboot;

import classes.springboot.Carburant;
import classes.springboot.Categorie;
import classes.springboot.HttpRetour;
import classes.springboot.Marque;
import classes.springboot.Modele;
import classes.springboot.Vitesse;

/**
 *
 * @author USER
 */
public class ServiceElement {
    Categorie c = new Categorie();
    Vitesse v = new Vitesse();
    Carburant c2 = new Carburant();
    Marque m = new Marque();
    Modele m2 = new Modele();
    
    public HttpRetour insertCategorie(String nom) {
        HttpRetour resultat = new HttpRetour();
        try {
            c.create(nom);
            resultat.setHttpRetour(resultat, 200, "Ok", null);
        }
        catch(Exception exception) {
            resultat.setHttpRetour(resultat, 400, "Erreur", null);
        }
        return resultat;
    }
    
    public HttpRetour listeCategorie() {
        HttpRetour resultat = new HttpRetour();
        Categorie[] listes = null;
        try {
            listes = c.liste();
            resultat.setHttpRetour(resultat, 200, "Ok", listes);
        }
        catch(Exception exception) {
            resultat.setHttpRetour(resultat, 400, "Erreur", null);
        }
        return resultat;
    }
    
    public HttpRetour deleteCategorie(String idCategorie) {
        HttpRetour resultat = new HttpRetour();
        try {
            c.delete(idCategorie);
            resultat.setHttpRetour(resultat, 200, "Ok", null);
        }
        catch(Exception exception) {
            resultat.setHttpRetour(resultat, 400, "Erreur", null);
        }
        return resultat;
    }
    
    
    public HttpRetour insertVitesse(String boiteVitesse) {
        HttpRetour resultat = new HttpRetour();
        try {
            v.create(boiteVitesse);
            resultat.setHttpRetour(resultat, 200, "Ok", null);
        }
        catch(Exception exception) {
            resultat.setHttpRetour(resultat, 400, "Erreur", null);
        }
        return resultat;
    }
    
    public HttpRetour listeVitesse() {
        HttpRetour resultat = new HttpRetour();
        Vitesse[] listes = null;
        try {
            listes = v.liste();
            resultat.setHttpRetour(resultat, 200, "Ok", listes);
        }
        catch(Exception exception) {
            resultat.setHttpRetour(resultat, 400, "Erreur", null);
        }
        return resultat;
    }
    
    public HttpRetour deleteVitesse(String idVitesse) {
        HttpRetour resultat = new HttpRetour();
        try {
            v.delete(idVitesse);
            resultat.setHttpRetour(resultat, 200, "Ok", null);
        }
        catch(Exception exception) {
            resultat.setHttpRetour(resultat, 400, "Erreur", null);
        }
        return resultat;
    }
    
    
    public HttpRetour insertCarburant(String nom) {
        HttpRetour resultat = new HttpRetour();
        try {
            c2.create(nom);
            resultat.setHttpRetour(resultat, 200, "Ok", null);
        }
        catch(Exception exception) {
            resultat.setHttpRetour(resultat, 400, "Erreur", null);
        }
        return resultat;
    }
    
    public HttpRetour listeCarburant() {
        HttpRetour resultat = new HttpRetour();
        Carburant[] listes = null;
        try {
            listes = c2.liste();
            resultat.setHttpRetour(resultat, 200, "Ok", listes);
        }
        catch(Exception exception) {
            resultat.setHttpRetour(resultat, 400, "Erreur", null);
        }
        return resultat;
    }
    
    public HttpRetour deleteCarburant(String idCarburant) {
        HttpRetour resultat = new HttpRetour();
        try {
            c2.delete(idCarburant);
            resultat.setHttpRetour(resultat, 200, "Ok", null);
        }
        catch(Exception exception) {
            resultat.setHttpRetour(resultat, 400, "Erreur", null);
        }
        return resultat;
    }
    
    
    public HttpRetour insertMarque(String nom) {
        HttpRetour resultat = new HttpRetour();
        try {
            m.create(nom);
            resultat.setHttpRetour(resultat, 200, "Ok", null);
        }
        catch(Exception exception) {
            resultat.setHttpRetour(resultat, 400, "Erreur", null);
        }
        return resultat;
    }
    
    public HttpRetour listeMarque() {
        HttpRetour resultat = new HttpRetour();
        Marque[] listes = null;
        try {
            listes = m.liste();
            resultat.setHttpRetour(resultat, 200, "Ok", listes);
        }
        catch(Exception exception) {
            resultat.setHttpRetour(resultat, 400, "Erreur", null);
        }
        return resultat;
    }
    
    public HttpRetour deleteMarque(String idMarque) {
        HttpRetour resultat = new HttpRetour();
        try {
            m.delete(idMarque);
            resultat.setHttpRetour(resultat, 200, "Ok", null);
        }
        catch(Exception exception) {
            resultat.setHttpRetour(resultat, 400, "Erreur", null);
        }
        return resultat;
    }
    
    
    public HttpRetour insertModele(String idMarque, String nom) {
        HttpRetour resultat = new HttpRetour();
        try {
            m2.create(idMarque, nom);
            resultat.setHttpRetour(resultat, 200, "Ok", null);
        }
        catch(Exception exception) {
            resultat.setHttpRetour(resultat, 400, "Erreur", null);
        }
        return resultat;
    }
    
    public HttpRetour listeModele() {
        HttpRetour resultat = new HttpRetour();
        Modele[] listes = null;
        try {
            listes = m2.liste();
            resultat.setHttpRetour(resultat, 200, "Ok", listes);
        }
        catch(Exception exception) {
            resultat.setHttpRetour(resultat, 400, "Erreur", null);
        }
        return resultat;
    }
    
    public HttpRetour deleteModele(String idModele) {
        HttpRetour resultat = new HttpRetour();
        try {
            m2.delete(idModele);
            resultat.setHttpRetour(resultat, 200, "Ok", null);
        }
        catch(Exception exception) {
            resultat.setHttpRetour(resultat, 400, "Erreur", null);
        }
        return resultat;
    }
}
