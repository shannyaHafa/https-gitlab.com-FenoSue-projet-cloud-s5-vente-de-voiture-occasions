/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.springboot;

import annotations.AnnotationClass;
import annotations.AnnotationField;
import static dao.Dao.dao;
import java.sql.Connection;

/**
 *
 * @author USER
 */
@AnnotationClass(table = "Produit")
public class Produit {
    @AnnotationField(attribut = "id")
    String id;
    @AnnotationField(attribut = "idMarque")
    int idMarque;
    @AnnotationField(attribut = "idModele")
    int idModele;
    @AnnotationField(attribut = "matricule")
    String matricule;
    @AnnotationField(attribut = "idVitesse")
    int idVitesse;
    @AnnotationField(attribut = "idCategorie")
    int idCategorie;
    @AnnotationField(attribut = "idCarburant")
    int idCarburant;
    @AnnotationField(attribut = "kilometrage")
    float kilometrage;
    @AnnotationField(attribut = "anneeSortie")
    int anneeSortie;
    @AnnotationField(attribut = "couleur")
    String couleur;
    @AnnotationField(attribut = "nbrPlace")
    int nbrPlace;
    @AnnotationField(attribut = "prix")
    double prix;

    public Produit() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIdMarque() {
        return idMarque;
    }

    public void setIdMarque(int idMarque) {
        this.idMarque = idMarque;
    }

    public int getIdModele() {
        return idModele;
    }

    public void setIdModele(int idModele) {
        this.idModele = idModele;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public int getIdVitesse() {
        return idVitesse;
    }

    public void setIdVitesse(int idVitesse) {
        this.idVitesse = idVitesse;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public int getIdCarburant() {
        return idCarburant;
    }

    public void setIdCarburant(int idCarburant) {
        this.idCarburant = idCarburant;
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

    public int getNbrPlace() {
        return nbrPlace;
    }

    public void setNbrPlace(int nbrPlace) {
        this.nbrPlace = nbrPlace;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
    
    public void create(String idMarque, String idModele, String matricule, String idVitesse, String idCategorie, String idCarburant,
            String kilometrage, String anneeSortie, String couleur, String nbrPlace, String prix, Connection connection) throws Exception {
        Produit produit = new Produit();
        int marque, modele, vitesse, categorie, carburant, annee, place = 0;
        float tabKilometrage = 0;
        double prixProduit = 0;
        try {
            if(idMarque!=null || idMarque.equals("")==false || idModele!=null || idModele.equals("")==false || idVitesse!=null || 
                    idVitesse.equals("")==false || idCategorie!=null || idCategorie.equals("")==false || idCarburant!=null ||
                    idCarburant.equals("")==false || idCategorie!=null || idCategorie.equals("")==false || anneeSortie!=null ||
                    anneeSortie.equals("")==false || nbrPlace!=null || nbrPlace.equals("")==false) {
                
                marque = Integer.parseInt(idMarque);
                modele = Integer.parseInt(idModele);
                vitesse = Integer.parseInt(idVitesse);
                categorie = Integer.parseInt(idCategorie);
                carburant = Integer.parseInt(idCarburant);
                annee = Integer.parseInt(anneeSortie);
                place = Integer.parseInt(nbrPlace);
                
                if(matricule!=null || matricule.equals("")==false || kilometrage!=null || kilometrage.equals("")==false || couleur!=null ||
                        couleur.equals("")==false || prix!=null || prix.equals("")==false) {
                    
                    tabKilometrage = Float.parseFloat(kilometrage);
                    prixProduit = Double.parseDouble(prix);
                    
                    produit.setIdMarque(marque);
                    produit.setIdModele(modele);
                    produit.setMatricule(matricule);
                    produit.setIdVitesse(vitesse);
                    produit.setIdCategorie(categorie);
                    produit.setIdCarburant(carburant);
                    produit.setKilometrage(tabKilometrage);
                    produit.setAnneeSortie(annee);
                    produit.setCouleur(couleur);
                    produit.setNbrPlace(place);
                    produit.setPrix(prixProduit);
                    
                    dao().insert(produit, connection);
                }
                else {
                    throw new Exception("NullPointerException");
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
    
    public Produit getLastProduit(Connection connection) throws Exception {
        Produit[] listeProduit = null;
        Produit produit = new Produit();
        String condition = null;
        try {
            condition = "order by id desc limit 1";
            listeProduit = (Produit[]) dao().select(this, condition, connection);
            for(Produit p : listeProduit) {
                produit = p;
            }
        }
        catch(Exception exception) {
            throw exception;
        }
        return produit;
    }
}
