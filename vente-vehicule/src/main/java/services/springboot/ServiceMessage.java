/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.springboot;

import classes.springboot.HttpRetour;
import classes.springboot.Message;

/**
 *
 * @author USER
 */
public class ServiceMessage {
    Message m = new Message();
    
    public HttpRetour envoyerMessage(String idUserDestinateur, String idUserDestinataire, String message) {
        HttpRetour resultat = new HttpRetour();
        try {
            m.envoyerMessage(idUserDestinateur, idUserDestinataire, message);
            resultat.setHttpRetour(resultat, 200, "Message envoyé", null);
        }
        catch(Exception exception) {
            resultat.setHttpRetour(resultat, 400, exception.getMessage(), null);
        }
        return resultat;
    }
}
