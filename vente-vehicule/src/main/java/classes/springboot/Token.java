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
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author USER
 */
@AnnotationClass(table = "Token")
public class Token {
    public static final long expiration = 86400;
    public static final String keyToken = "Token22";
    
    @AnnotationField(attribut = "utilisateur")
    String utilisateur;
    @AnnotationField(attribut = "token")
    String token;
    @AnnotationField(attribut = "dateExpiration")
    Date dateExpiration;

    public Token() {
    }

    public Token(String utilisateur, String token, Date dateExpiration) {
        this.utilisateur = utilisateur;
        this.token = token;
        this.dateExpiration = dateExpiration;
    }

    public String getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(String utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(Date dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public void create(Token tokenGenerer) throws Exception {
        try(Connection connection = new Connexion().getConnection()) {
            connection.setAutoCommit(false);
            try {
                dao().insert(tokenGenerer, connection);
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
    
    public Token readByToken(String tokenUtilisateur) throws Exception {
        Token tokenAdmin = new Token();
        Token tokens = new Token();
        Token[] listeToken = null;
        try(Connection connection = new Connexion().getConnection()) {
            tokens.setToken(tokenUtilisateur);
            listeToken = (Token[]) dao().select(tokens, connection);
            for(Token t : listeToken) {
                tokenAdmin = t;
            }
        }
        catch(Exception exception) {
            throw exception;
        }
        return tokenAdmin;
    }
    
    public void delete(Token token) throws Exception {
        try(Connection connection = new Connexion().getConnection()) {
            connection.setAutoCommit(false);
            try {
                dao().delete(token, connection);
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
    
    public String genererToken(String utilisateurId) throws Exception {
        Token tokenGenerer = new Token();
        long now = System.currentTimeMillis();
        int expire = (int) this.expiration/60/60/24;
        Date date = Date.valueOf(LocalDate.now().plusDays(expire));
        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, Token.keyToken).setIssuedAt(new Date(now)).setExpiration(date).claim("idUtilisateur", utilisateurId).compact();
        tokenGenerer.setUtilisateur(utilisateurId);
        tokenGenerer.setToken(token);
        tokenGenerer.setDateExpiration(date);
        create(tokenGenerer);
        return token;
    }
    
    public boolean verifieToken(String token) throws Exception {
        boolean resultat = false;
        Token tokenAdmin = null;
        try {
            tokenAdmin = readByToken(token);
            if(tokenAdmin.getToken()!=null) {
                resultat = true;
            }
        }
        catch(Exception exception) {
            throw exception;
        }
        return resultat;
    }
}