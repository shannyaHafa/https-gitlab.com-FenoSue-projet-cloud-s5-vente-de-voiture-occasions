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
@AnnotationClass(table = "Utilisateur")
public class Utilisateur {
    @AnnotationField(attribut = "id")
    String id;
    @AnnotationField(attribut = "login")
    String login;
    @AnnotationField(attribut = "pwd")
    String pwd;
    @AnnotationField(attribut = "roles")
    String roles;

    public Utilisateur() {
    }

    public Utilisateur(String login, String pwd, String roles) {
        this.login = login;
        this.pwd = pwd;
        this.roles = roles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
    
    public Utilisateur getById(String id) throws Exception {
        Utilisateur user = new Utilisateur();
        Utilisateur userId = new Utilisateur();
        Utilisateur[] listeUser = null;
        try {
            userId.setId(id);
            listeUser = this.read(userId);
            for(Utilisateur u : listeUser) {
                user = u;
            }
        }
        catch(Exception exception) {
            throw exception;
        }
        return user;
    }
    
    public void create(String login, String pwd, String roles) throws Exception {
        try(Connection connection = new Connexion().getConnection()) {
            connection.setAutoCommit(false);
            Utilisateur user = new Utilisateur(login, pwd, roles);
            try {
                dao().insert(user, connection);
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
    
    public Utilisateur[] read(Utilisateur user) throws Exception {
        Utilisateur[] listeUser = null;
        try(Connection connection = new Connexion().getConnection()) {
            listeUser = (Utilisateur[]) dao().select(user, connection);
        }
        catch(Exception exception) {
            throw exception;
        }
        return listeUser;
    }
    
    public void update(Utilisateur user, Utilisateur newUser) throws Exception {
        try(Connection connection = new Connexion().getConnection()) {
            connection.setAutoCommit(false);
            try {
                dao().update(user, newUser, connection);
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
    
    public void delete(Utilisateur user) throws Exception {
        try(Connection connection = new Connexion().getConnection()) {
            connection.setAutoCommit(false);
            try {
                dao().delete(user, connection);
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
