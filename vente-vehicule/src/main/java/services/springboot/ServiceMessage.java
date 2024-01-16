/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.springboot;

import classes.springboot.HttpRetour;
import classes.springboot.Message;
import classes.springboot.Token;

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
    
    public HttpRetour getDiscussion(String token, String idUserDestinateur, String idUserDestinataire) throws Exception {
        HttpRetour resultat = new HttpRetour();
        Message[] messages = null;
        boolean verification = new Token().verifieToken(token);
        if(verification==true) {
            try {
                messages = m.getListeMessage(idUserDestinateur, idUserDestinataire);
                resultat.setHttpRetour(resultat, 200, "Ok", messages);
            }
            catch(Exception exception) {
                resultat.setHttpRetour(resultat, 400, exception.getMessage(), null);
            }
        }
        else {
            resultat.setHttpRetour(resultat, 400, "Vous devez vous connectez!!", null);
        }
        return resultat;
    }
}
