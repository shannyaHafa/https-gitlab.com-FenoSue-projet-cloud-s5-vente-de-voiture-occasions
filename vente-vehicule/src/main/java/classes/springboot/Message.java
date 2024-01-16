/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.springboot;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import connexion.ConnexionMongo;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import org.bson.Document;
import com.mongodb.client.MongoCursor;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
public class Message {
    @JsonProperty("_id")
    private Id id;
    
    @JsonProperty("idUserDestinateur")
    private String idUserDestinateur;
    
    @JsonProperty("idUserDestinataire")
    private String idUserDestinataire;
    
    @JsonProperty("message")
    private String message;
    
    @JsonProperty("dateHeure")
    private DateHeure dateHeure;

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
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

    public DateHeure getDateHeure() {
        return dateHeure;
    }

    public void setDateHeure(DateHeure dateHeure) {
        this.dateHeure = dateHeure;
    }

    public static class Id {
        @JsonProperty("$oid")
        private String oid;

        public String getOid() {
            return oid;
        }

        public void setOid(String oid) {
            this.oid = oid;
        }
    }

    public static class DateHeure {
        @JsonProperty("$date")
        private String date;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
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
    
    public Message[] getListeMessage(String idUserDestinateur, String idUserDestinataire) throws JsonProcessingException {
        Message[] messages = null;
        List<Message> listes = new ArrayList();
        int i=0;
        try (MongoClient mongoClient = new ConnexionMongo().getConnection()) {
            MongoDatabase database = mongoClient.getDatabase("ventevehicule");
            MongoCollection<Document> collection = database.getCollection("message");
            Document query = new Document("idUserDestinateur", idUserDestinateur).append("idUserDestinataire", idUserDestinataire);
            try (MongoCursor<Document> cursor = collection.find(query).iterator()) {
                while (cursor.hasNext()) {
                    Document document = cursor.next();
                    String jsonDocument = document.toJson();
                    ObjectMapper objectMapper = new ObjectMapper();
                    Message messageObject = objectMapper.readValue(jsonDocument, Message.class);
                    listes.add(messageObject);
                }
            }
            messages = new Message[listes.size()];
            for(i=0; i<listes.size(); i++) {
                messages[i] = listes.get(i);
            }
        }
        return messages;
    }
}
