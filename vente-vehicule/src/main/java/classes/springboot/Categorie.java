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
@AnnotationClass(table = "Categorie")
public class Categorie {
    @AnnotationField(attribut = "id")
    int id;
    @AnnotationField(attribut = "nom")
    String nom;

    public Categorie() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public void create(String nom) throws Exception {
        Categorie categorie = new Categorie();
        try(Connection connection = new Connexion().getConnection()) {
            connection.setAutoCommit(false);
            if(nom!=null || nom.equals("")==false) {
                categorie.setNom(nom);
                try {
                    dao().insert(categorie, connection);
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
    
    public void update(String idCategorie, String newNom) throws Exception {
        Categorie categorie = new Categorie();
        Categorie newCategorie = new Categorie();
        int idCat = 0;
        try(Connection connection = new Connexion().getConnection()) {
            connection.setAutoCommit(false);
            if(idCategorie!=null || idCategorie.equals("")==false || newNom!=null || newNom.equals("")==false) {
                idCat = Integer.parseInt(idCategorie);
                categorie.setId(idCat);
                newCategorie.setNom(newNom);
                try {
                    dao().update(categorie, newCategorie, connection);
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
    
    public void delete(String idCategorie) throws Exception {
        Categorie categorie = new Categorie();
        int idCat = 0;
        try(Connection connection = new Connexion().getConnection()) {
            connection.setAutoCommit(false);
            if(idCategorie!=null || idCategorie.equals("")==false) {
                categorie.setId(idCat);
                try {
                    dao().delete(categorie, connection);
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
    
    public Categorie[] liste() throws Exception {
        Categorie[] listeCategorie = null;
        try(Connection connection = new Connexion().getConnection()) {
            listeCategorie = (Categorie[]) dao().select(this, connection);
        }
        catch(Exception exception) {
            throw exception;
        }
        return listeCategorie;
    }
}
