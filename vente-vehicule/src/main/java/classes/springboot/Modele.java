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

/**
 *
 * @author USER
 */
@AnnotationClass(table = "Modele")
public class Modele {
    @AnnotationField(attribut = "id")
    int id;
    @AnnotationField(attribut = "idMarque")
    int idMarque;
    @AnnotationField(attribut = "nom")
    String nom;

    public Modele() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdMarque() {
        return idMarque;
    }

    public void setIdMarque(int idMarque) {
        this.idMarque = idMarque;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public void create(int idMarque, String nom) throws Exception {
        Modele modele = new Modele();
        try(Connection connection = new Connexion().getConnection()) {
            connection.setAutoCommit(false);
            if(nom!=null || nom.equals("")==false) {
                modele.setIdMarque(idMarque);
                modele.setNom(nom);
                try {
                    dao().insert(modele, connection);
                    connection.commit();
                }
                catch(Exception exception) {
                    connection.rollback();
                    throw exception;
                }
            }
            else {
                throw new Exception("NullPointerException");
            }
        }
        catch(Exception exception) {
            throw exception;
        }
    }
    
    public void update(String idModele, String newNom) throws Exception {
        Modele modele = new Modele();
        Modele newModele = new Modele();
        int idCat = 0;
        try(Connection connection = new Connexion().getConnection()) {
            connection.setAutoCommit(false);
            if(idModele!=null || idModele.equals("")==false || newNom!=null || newNom.equals("")==false) {
                idCat = Integer.parseInt(idModele);
                modele.setId(idCat);
                newModele.setNom(newNom);
                try {
                    dao().update(modele, newModele, connection);
                    connection.commit();
                }
                catch(Exception exception) {
                    connection.rollback();
                    throw exception;
                }
            }
            else {
                throw new Exception("NullPointerException");
            }
        }
        catch(Exception exception) {
            throw exception;
        }
    }
    
    public void delete(String idModele) throws Exception {
        Modele modele = new Modele();
        int idMod = 0;
        try(Connection connection = new Connexion().getConnection()) {
            connection.setAutoCommit(false);
            if(idModele!=null || idModele.equals("")==false) {
                modele.setId(idMod);
                try {
                    dao().delete(modele, connection);
                    connection.commit();
                }
                catch(Exception exception) {
                    connection.rollback();
                    throw exception;
                }
            }
            else {
                throw new Exception("NullPointerException");
            }
        }
        catch(Exception exception) {
            throw exception;
        }
    }
    
    public Modele[] liste() throws Exception {
        Modele[] listeModele = null;
        try(Connection connection = new Connexion().getConnection()) {
            listeModele = (Modele[]) dao().select(this, connection);
        }
        catch(Exception exception) {
            throw exception;
        }
        return listeModele;
    }
}
