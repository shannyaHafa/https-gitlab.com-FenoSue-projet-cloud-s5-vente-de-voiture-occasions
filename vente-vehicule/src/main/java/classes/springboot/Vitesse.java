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
@AnnotationClass(table = "Vitesse")
public class Vitesse {
    @AnnotationField(attribut = "id")
    int id;
    @AnnotationField(attribut = "nom")
    String boiteVitesse;

    public Vitesse() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBoiteVitesse() {
        return boiteVitesse;
    }

    public void setBoiteVitesse(String boiteVitesse) {
        this.boiteVitesse = boiteVitesse;
    }

    
    public void create(String boiteVitesse) throws Exception {
        Vitesse vitesse = new Vitesse();
        try(Connection connection = new Connexion().getConnection()) {
            connection.setAutoCommit(false);
            if(boiteVitesse!=null || boiteVitesse.equals("")==false) {
                vitesse.setBoiteVitesse(boiteVitesse);
                try {
                    dao().insert(vitesse, connection);
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
    
    public void update(String idVitesse, String newNomBoiteVitesse) throws Exception {
        Vitesse vitesse = new Vitesse();
        Vitesse newVitesse = new Vitesse();
        int idCat = 0;
        try(Connection connection = new Connexion().getConnection()) {
            connection.setAutoCommit(false);
            if(idVitesse!=null || idVitesse.equals("")==false || newNomBoiteVitesse!=null || newNomBoiteVitesse.equals("")==false) {
                idCat = Integer.parseInt(idVitesse);
                vitesse.setId(idCat);
                newVitesse.setBoiteVitesse(newNomBoiteVitesse);
                try {
                    dao().update(vitesse, newVitesse, connection);
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
    
    public void delete(String idVitesse) throws Exception {
        Vitesse vitesse = new Vitesse();
        int idVit = 0;
        try(Connection connection = new Connexion().getConnection()) {
            connection.setAutoCommit(false);
            if(idVitesse!=null || idVitesse.equals("")==false) {
                vitesse.setId(idVit);
                try {
                    dao().delete(vitesse, connection);
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
    
    public Vitesse[] liste() throws Exception {
        Vitesse[] listeVitesse = null;
        try(Connection connection = new Connexion().getConnection()) {
            listeVitesse = (Vitesse[]) dao().select(this, connection);
        }
        catch(Exception exception) {
            throw exception;
        }
        return listeVitesse;
    }
}
