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
@AnnotationClass(table = "Carburant")
public class Carburant {
    @AnnotationField(attribut = "id")
    int id;
    @AnnotationField(attribut = "nom")
    String nom;

    public Carburant() {
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
        Carburant carburant = new Carburant();
        try(Connection connection = new Connexion().getConnection()) {
            connection.setAutoCommit(false);
            if(nom!=null || nom.equals("")==false) {
                carburant.setNom(nom);
                try {
                    dao().insert(carburant, connection);
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
    
    public void update(String idCarburant, String newNom) throws Exception {
        Carburant carburant = new Carburant();
        Carburant newCarburant = new Carburant();
        int idCat = 0;
        try(Connection connection = new Connexion().getConnection()) {
            connection.setAutoCommit(false);
            if(idCarburant!=null || idCarburant.equals("")==false || newNom!=null || newNom.equals("")==false) {
                idCat = Integer.parseInt(idCarburant);
                carburant.setId(idCat);
                newCarburant.setNom(newNom);
                try {
                    dao().update(carburant, newCarburant, connection);
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
    
    public void delete(String idCarburant) throws Exception {
        Carburant carburant = new Carburant();
        int idCar = 0;
        try(Connection connection = new Connexion().getConnection()) {
            connection.setAutoCommit(false);
            if(idCarburant!=null || idCarburant.equals("")==false) {
                carburant.setId(idCar);
                try {
                    dao().delete(carburant, connection);
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
    
    public Carburant[] liste() throws Exception {
        Carburant[] listeCarburant = null;
        try(Connection connection = new Connexion().getConnection()) {
            listeCarburant = (Carburant[]) dao().select(this, connection);
        }
        catch(Exception exception) {
            throw exception;
        }
        return listeCarburant;
    }
}
