/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.springboot;

import annotations.AnnotationClass;
import annotations.AnnotationField;
import connexion.Connexion;
import static dao.Dao.dao;
import java.sql.Connection;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 *
 * @author USER
 */
@AnnotationClass(table = "Annonce")
public class Annonce {
    @AnnotationField(attribut = "id")
    String id;
    @AnnotationField(attribut = "idProduit")
    String idProduit;
    @AnnotationField(attribut = "idUtilisateur")
    String idUtilisateur;
    @AnnotationField(attribut = "dateHeure")
    Timestamp dateHeure;
    @AnnotationField(attribut = "description")
    String description;
    @AnnotationField(attribut = "statut")
    int statut;
    @AnnotationField(attribut = "dateStatut")
    Timestamp dateStatut;
    @AnnotationField(attribut = "etat")
    int etat;
    @AnnotationField(attribut = "favoris")
    int favoris;

    public Annonce() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(String idProduit) {
        this.idProduit = idProduit;
    }

    public String getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(String idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public Timestamp getDateHeure() {
        return dateHeure;
    }

    public void setDateHeure(Timestamp dateHeure) {
        this.dateHeure = dateHeure;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }

    public Timestamp getDateStatut() {
        return dateStatut;
    }

    public void setDateStatut(Timestamp dateStatut) {
        this.dateStatut = dateStatut;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public int getFavoris() {
        return favoris;
    }

    public void setFavoris(int favoris) {
        this.favoris = favoris;
    }
    
    public void create(String idProduit, String idUtilisateur, String description, Connection connection) throws Exception {
        Annonce annonce = new Annonce();
        Timestamp dateAnnonce = Timestamp.valueOf(LocalDateTime.now());
        int statuts = -1, etats = -1, favori = -1;
        try {
            if(idProduit!=null || idProduit.equals("")==false || idUtilisateur!=null || idUtilisateur.equals("")==false || description!=null ||
                    description.equals("")==false) {
                
                annonce.setIdProduit(idProduit);
                annonce.setIdUtilisateur(idUtilisateur);
                annonce.setDateHeure(dateAnnonce);
                annonce.setDescription(description);
                annonce.setStatut(statuts);
                annonce.setEtat(etats);
                annonce.setFavoris(favori);
                
                dao().insert(annonce, connection);
            }
            else {
                throw new Exception("NullPointerException");
            }
        }
        catch(Exception exception) {
            throw exception;
        }
    }
    
    public void update(Annonce annonce, Annonce newAnnonce) throws Exception {
        try(Connection connection = new Connexion().getConnection()) {
            connection.setAutoCommit(false);
            try {
                dao().update(annonce, newAnnonce, connection);
                connection.commit();
            }
            catch(Exception exception) {
                connection.rollback();
                throw exception;
            }
        }
        catch(Exception exception) {
            throw exception;
        }
    }
}
