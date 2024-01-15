/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.springboot;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import connexion.ConnexionMongo;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import org.bson.Document;
import com.mongodb.client.MongoCursor;

/**
 *
 * @author USER
 */
public class Message {
    int id;
    String idUserDestinateur;
    String idUserDestinataire;
    String message;
    Timestamp dateHeure;

    public Message() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdUserDestinateur() {
        return idUserDestinateur;
    }

    public void setIdUserDestinateur(String idUserDestinateur) {
        this.idUserDestinateur = idUserDestinateur;
    }

    public String getIdUserDestinataire() {
        return idUserDestinataire;
    }

    public void setIdUserDestinataire(String idUserDestinataire) {
        this.idUserDestinataire = idUserDestinataire;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getDateHeure() {
        return dateHeure;
    }

    public void setDateHeure(Timestamp dateHeure) {
        this.dateHeure = dateHeure;
    }
    
    public void envoyerMessage(String idUserDestinateur, String idUserDestinataire, String message) {
        Timestamp date = Timestamp.valueOf(LocalDateTime.now());
        Document document1 = new Document()
                    .append("idUserDestinateur", idUserDestinateur)
                    .append("idUserDestinataire", idUserDestinataire)
                    .append("message", message)
                    .append("dateHeure", date);
        try(MongoClient mongoClient = new ConnexionMongo().getConnection()) {
            MongoDatabase database = mongoClient.getDatabase("ventevehicule");
            MongoCollection<Document> collection = database.getCollection("message");
            collection.insertOne(document1);
            System.out.println("okok");
        }
    }
    
    public Message[] getListeMessage() {
        Message[] message = null;
        try (MongoClient mongoClient = new ConnexionMongo().getConnection()) {
            MongoDatabase database = mongoClient.getDatabase("ventevehicule");
            MongoCollection<Document> collection = database.getCollection("message");
            try (MongoCursor<Document> cursor = collection.find().iterator()) {
                while (cursor.hasNext()) {
                    Document document = cursor.next();
                    System.out.println(document.toJson());
                }
            }
        }
        return message;
    }
}
