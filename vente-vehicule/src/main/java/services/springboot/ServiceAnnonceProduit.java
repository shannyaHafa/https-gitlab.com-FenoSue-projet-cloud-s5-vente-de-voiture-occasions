/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.springboot;

import classeViews.springboot.DetailAnnonce;
import classes.springboot.Annonce;
import classes.springboot.HttpRetour;
import classes.springboot.Produit;
import classes.springboot.Token;
import connexion.Connexion;
import java.sql.Connection;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 *
 * @author USER
 */
public class ServiceAnnonceProduit {
    Produit p = new Produit();
    Annonce a = new Annonce();
    DetailAnnonce av = new DetailAnnonce();
    
    public HttpRetour ajoutAnnonceProduit(String token, String idMarque, String idModele, String matricule, String idVitesse, String idCategorie, 
            String idCarburant, String kilometrage, String anneeSortie, String couleur, String nbrPlace, String prix, String description) 
            throws Exception {
        
        HttpRetour resultat = new HttpRetour();
        boolean verification = new Token().verifieToken(token);
        if(verification==true) {
            Produit produit = new Produit();
            String idUtilisateur = null;
            try(Connection connection = new Connexion().getConnection()) {
                connection.setAutoCommit(false);
                idUtilisateur = new Token().readByToken(token).getUtilisateur();
                try {
                    p.create(idMarque, idModele, matricule, idVitesse, idCategorie, idCarburant, kilometrage, anneeSortie, couleur, nbrPlace, prix, 
                            connection);
                    produit = p.getLastProduit(connection);
                    a.create(produit.getId(), idUtilisateur, description, connection);
                    resultat.setHttpRetour(resultat, 200, "Ok", null);
                    connection.commit();
                }
                catch(Exception exception) {
                    connection.rollback();
                    resultat.setHttpRetour(resultat, 400, exception.getMessage(), null);
                }
            }
            catch(Exception exception) {
                resultat.setHttpRetour(resultat, 400, exception.getMessage(), null);
            }
        }
        else {
            resultat.setHttpRetour(resultat, 400, "Vous devez vous connectez!!", null);
        }
        return resultat;
    }
    
    public HttpRetour validerAnnonce(String token, String idAnnonce) throws Exception {
        HttpRetour resultat = new HttpRetour();
        try {
            boolean verification = new Token().verifieToken(token);
            if(verification==true) {
                Annonce annonce = new Annonce();
                Annonce newAnnonce = new Annonce();
                annonce.setId(idAnnonce);
                newAnnonce.setEtat(1);
                try {
                    a.update(annonce, newAnnonce);
                    resultat.setHttpRetour(resultat, 200, "Validation OK", null);
                }
                catch(Exception exception) {
                    resultat.setHttpRetour(resultat, 400, exception.getMessage(), null);
                }
            }
            else {
                throw new Exception("Vous devez vous connectez!!");
            }
        }
        catch(Exception exception) {
            resultat.setHttpRetour(resultat, 400, exception.getMessage(), null);
        }
        return resultat;
    }
    
    public HttpRetour favoriserAnnonce(String token, String idAnnonce) {
        HttpRetour resultat = new HttpRetour();
        try {
            boolean verification = new Token().verifieToken(token);
            if(verification==true) {
                Annonce annonce = new Annonce();
                Annonce newAnnonce = new Annonce();
                annonce.setId(idAnnonce);
                newAnnonce.setFavoris(1);
                try {
                    a.update(annonce, newAnnonce);
                    resultat.setHttpRetour(resultat, 200, "Annonce favoriser", null);
                }
                catch(Exception exception) {
                    resultat.setHttpRetour(resultat, 400, exception.getMessage(), null);
                }
            }
            else {
                throw new Exception("Vous devez vous connectez!!");
            }
        }
        catch(Exception exception) {
            resultat.setHttpRetour(resultat, 400, exception.getMessage(), null);
        }
        return resultat;
    }
    
    public HttpRetour ModifierStatutAnnonce(String token, String idAnnonce) {
        HttpRetour resultat = new HttpRetour();
        try {
            boolean verification = new Token().verifieToken(token);
            if(verification==true) {
                Annonce annonce = new Annonce();
                Annonce newAnnonce = new Annonce();
                Timestamp dateStatut = Timestamp.valueOf(LocalDateTime.now());
                annonce.setId(idAnnonce);
                newAnnonce.setStatut(1);
                newAnnonce.setDateStatut(dateStatut);
                try {
                    a.update(annonce, newAnnonce);
                    resultat.setHttpRetour(resultat, 200, "Statut modifier", null);
                }
                catch(Exception exception) {
                    resultat.setHttpRetour(resultat, 400, exception.getMessage(), null);
                }
            }
            else {
                throw new Exception("Vous devez vous connectez!!");
            }
        }
        catch(Exception exception) {
            resultat.setHttpRetour(resultat, 400, exception.getMessage(), null);
        }
        return resultat;
    }
    
    public HttpRetour listeAnnonceNonValider() {
        HttpRetour resultat = new HttpRetour();
        DetailAnnonce[] listes = null;
        try {
            listes = av.listeAnnonceNonValider();
            resultat.setHttpRetour(resultat, 200, "Ok", listes);
        }
        catch(Exception exception) {
            resultat.setHttpRetour(resultat, 400, exception.getMessage(), null);
        }
        return resultat;
    }
    
    public HttpRetour listeAnnonceValider() {
        HttpRetour resultat = new HttpRetour();
        DetailAnnonce[] listes = null;
        try {
            listes = av.listeAnnonceValider();
            resultat.setHttpRetour(resultat, 200, "Ok", listes);
        }
        catch(Exception exception) {
            resultat.setHttpRetour(resultat, 400, exception.getMessage(), null);
        }
        return resultat;
    }
    
    public HttpRetour historiqueAnnonce() {
        HttpRetour resultat = new HttpRetour();
        DetailAnnonce[] listes = null;
        try {
            listes = av.historiqueAnnonce();
            resultat.setHttpRetour(resultat, 200, "Ok", listes);
        }
        catch(Exception exception) {
            resultat.setHttpRetour(resultat, 400, exception.getMessage(), null);
        }
        return resultat;
    }
    
    public HttpRetour listeAnnonceFavoris() {
        HttpRetour resultat = new HttpRetour();
        DetailAnnonce[] listes = null;
        try {
            listes = av.listeAnnonceFavoris();
            resultat.setHttpRetour(resultat, 200, "Ok", listes);
        }
        catch(Exception exception) {
            resultat.setHttpRetour(resultat, 400, exception.getMessage(), null);
        }
        return resultat;
    }
    
    public HttpRetour listeAnnonceValider(String marque, String modele, String boiteVitesse, String categorie, String typeCarburant,
            String couleur, String prixMin, String prixMax) {
        HttpRetour resultat = new HttpRetour();
        DetailAnnonce[] listes = null;
        try {
            listes = av.listeAnnonceValider(marque, modele, boiteVitesse, categorie, typeCarburant, couleur, prixMin, prixMax);
            if(listes.length!=0) {
                resultat.setHttpRetour(resultat, 200, "Ok", listes);
            }
            else {
                resultat.setHttpRetour(resultat, 300, "Aucune annonce correspondant à votre recherche", listes);
            }
        }
        catch(Exception exception) {
            resultat.setHttpRetour(resultat, 400, exception.getMessage(), null);
        }
        return resultat;
    }
}
