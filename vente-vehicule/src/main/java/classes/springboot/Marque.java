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
@AnnotationClass(table = "Marque")
public class Marque {
    @AnnotationField(attribut = "id")
    int id;
    @AnnotationField(attribut = "nom")
    String nom;

    public Marque() {
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
        Marque marque = new Marque();
        try(Connection connection = new Connexion().getConnection()) {
            connection.setAutoCommit(false);
            if(nom!=null || nom.equals("")==false) {
                marque.setNom(nom);
                try {
                    dao().insert(marque, connection);
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
    
    public void update(String idMarque, String newNom) throws Exception {
        Marque marque = new Marque();
        Marque newMarque = new Marque();
        int idCat = 0;
        try(Connection connection = new Connexion().getConnection()) {
            connection.setAutoCommit(false);
            if(idMarque!=null || idMarque.equals("")==false || newNom!=null || newNom.equals("")==false) {
                idCat = Integer.parseInt(idMarque);
                marque.setId(idCat);
                newMarque.setNom(newNom);
                try {
                    dao().update(marque, newMarque, connection);
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
    
    public void delete(String idMarque) throws Exception {
        Marque marque = new Marque();
        int idMar = 0;
        try(Connection connection = new Connexion().getConnection()) {
            connection.setAutoCommit(false);
            if(idMarque!=null || idMarque.equals("")==false) {
                marque.setId(idMar);
                try {
                    dao().delete(marque, connection);
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
    
    public Marque[] liste() throws Exception {
        Marque[] listeMarque = null;
        try(Connection connection = new Connexion().getConnection()) {
            listeMarque = (Marque[]) dao().select(this, connection);
        }
        catch(Exception exception) {
            throw exception;
        }
        return listeMarque;
    }
}
