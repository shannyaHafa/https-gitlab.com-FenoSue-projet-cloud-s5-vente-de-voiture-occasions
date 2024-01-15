/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classeViews.springboot;

import annotations.AnnotationField;
import classes.springboot.Annonce;
import connexion.Connexion;
import static dao.Dao.dao;
import java.sql.Connection;

/**
 *
 * @author USER
 */
public class DetailAnnonce extends Annonce {
    @AnnotationField(attribut = "marque")
    String marque;
    @AnnotationField(attribut = "modele")
    String modele;
    @AnnotationField(attribut = "boiteVitesse")
    String boiteVitesse;
    @AnnotationField(attribut = "categorie")
    String categorie;
    @AnnotationField(attribut = "typeCarburant")
    String typeCarburant;
    @AnnotationField(attribut = "kilometrage")
    float kilometrage;
    @AnnotationField(attribut = "anneeSortie")
    int anneeSortie;
    @AnnotationField(attribut = "couleur")
    String couleur;
    @AnnotationField(attribut = "nbrPlace")
    int nbrplace;
    @AnnotationField(attribut = "prix")
    double prix;

    public DetailAnnonce() {
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getBoiteVitesse() {
        return boiteVitesse;
    }

    public void setBoiteVitesse(String boiteVitesse) {
        this.boiteVitesse = boiteVitesse;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getTypeCarburant() {
        return typeCarburant;
    }

    public void setTypeCarburant(String typeCarburant) {
        this.typeCarburant = typeCarburant;
    }

    public float getKilometrage() {
        return kilometrage;
    }

    public void setKilometrage(float kilometrage) {
        this.kilometrage = kilometrage;
    }

    public int getAnneeSortie() {
        return anneeSortie;
    }

    public void setAnneeSortie(int anneeSortie) {
        this.anneeSortie = anneeSortie;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public int getNbrplace() {
        return nbrplace;
    }

    public void setNbrplace(int nbrplace) {
        this.nbrplace = nbrplace;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
    
    public DetailAnnonce[] listeAnnonceNonValider() throws Exception {
        DetailAnnonce[] listes = null;
        try(Connection connection = new Connexion().getConnection()) {
            listes = (DetailAnnonce[]) dao().select("AnnonceNonValider", this, connection);
        }
        catch(Exception exception) {
            throw exception;
        }
        return listes;
    }
    
    public DetailAnnonce[] listeAnnonceValider() throws Exception {
        DetailAnnonce[] listes = null;
        try(Connection connection = new Connexion().getConnection()) {
            listes = (DetailAnnonce[]) dao().select("ListeAnnonce", this, connection);
        }
        catch(Exception exception) {
            throw exception;
        }
        return listes;
    }
    
    public DetailAnnonce[] historiqueAnnonce() throws Exception {
        DetailAnnonce[] listes = null;
        try(Connection connection = new Connexion().getConnection()) {
            listes = (DetailAnnonce[]) dao().select("HistoriqueAnnonce", this, connection);
        }
        catch(Exception exception) {
            throw exception;
        }
        return listes;
    }
    
    public DetailAnnonce[] listeAnnonceFavoris() throws Exception {
        DetailAnnonce[] listes = null;
        try(Connection connection = new Connexion().getConnection()) {
            listes = (DetailAnnonce[]) dao().select("AnnonceFavoris", this, connection);
        }
        catch(Exception exception) {
            throw exception;
        }
        return listes;
    }
    
    public DetailAnnonce[] listeAnnonceValider(String marque, String modele, String boiteVitesse, String categorie, String typeCarburant,
            String couleur, String prixMin, String prixMax) throws Exception {
        DetailAnnonce[] listes = null;
        String requette = " select * from ListeAnnonce where 1>0";
        double min, max =0;
        try(Connection connection = new Connexion().getConnection()) {
            if(marque!=null && marque.equals("")==false) {
                requette += " and marque like '%"+marque+"%'";
            }
            if(modele!=null && modele.equals("")==false) {
                requette += " and modele like '%"+modele+"%'";
            }
            if(boiteVitesse!=null && boiteVitesse.equals("")==false) {
                System.out.println("vitesse : '"+boiteVitesse+"'");
                requette += " and boiteVitesse like '%"+boiteVitesse+"%'";
            }
            if(categorie!=null && categorie.equals("")==false) {
                System.out.println("vitesse : '"+boiteVitesse+"'");
                requette += " and categorie like '%"+categorie+"%'";
            }
            if(typeCarburant!=null && typeCarburant.equals("")==false) {
                System.out.println("vitesse : '"+boiteVitesse+"'");
                requette += " and typeCarburant like '%"+typeCarburant+"%'";
            }
            if(couleur!=null && couleur.equals("")==false) {
                requette += " and couleur like '%"+couleur+"%'";
            }
            if(prixMin!=null && prixMin.equals("")==false) {
                min = Double.parseDouble(prixMin);
                requette += " and prix>="+min;
            }
            if(prixMax!=null && prixMax.equals("")==false) {
                max = Double.parseDouble(prixMax);
                requette += " and prix<="+max;
            }
            listes = (DetailAnnonce[]) dao().selectRequette(this, requette, connection);
        }
        catch(Exception exception) {
            exception.printStackTrace();
            throw exception;
        }
        return listes;
    }
}
