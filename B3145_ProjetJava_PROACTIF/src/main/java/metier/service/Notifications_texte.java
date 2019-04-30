/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.service;
import metier.modele.Client;
import metier.modele.Intervention;
import metier.modele.STATUT_INTERVENTION;
/**
 *
 * @author touristguy
 */
public class Notifications_texte {
    public static String inscriptionImpossible(Client unClient)
    {
        String message = "Bonjour "+unClient.getPrenom()+", votre inscription au service PROACT'IF a malencontreusement échoué... \n";
        message+="Merci de recommencer ultérieurement.";
        return message;
    }
    public static String inscriptionReussie(Client unClient)
    {
        String message = "Bonjour "+unClient.getPrenom()+", nous vous confirmons votre incription au service PROACT'IF.\n";
        message+=" Votre numero de client est : "+unClient.getIdPersonne();
        return message;
    }
    
    public static String debutInterventionEmploye(Intervention inter)
    {
        String message = inter.getType()+ " demandée le "+ inter.getDateDebut().getDate()+"/"+inter.getDateDebut().getMonth()+"/" +(inter.getDateDebut().getYear()+1900)+" a "+inter.getDateDebut().getHours()+":"+inter.getDateDebut().getMinutes()+" ";
        message+= "pour "+inter.getClient_associe().getPrenom()+" "+inter.getClient_associe().getNom()+" ";
        message+= "(#"+inter.getClient_associe().getIdPersonne()+"), "+ inter.getClient_associe().getAdresse()+".\n";
        message+= "\""+inter.getDescription()+"\".\n";
        message+= "Trajet : "+inter.getDistance()+" km.";
        return message;
    }
    public static String debutInterventionClient(Intervention inter)
    {
        String message = "Votre demande d'intervention faite le "+inter.getDateDebut().getDate()+"/"+inter.getDateDebut().getMonth()+"/" +(inter.getDateDebut().getYear()+1900)+" a "+inter.getDateDebut().getHours()+":"+inter.getDateDebut().getMinutes()+" ";
        message+=" a été prise en compte et acceptée, merci pour votre confiance.";
        return message;
    }   
    public static String interventionImpossible()
    {
        return "Aucun employe n'est disponible actuellement pour votre intervention, veuillez reessayer ulterieuerement";
    }
    public static String clotureInterventionClient(Intervention inter)
    {
        String message = "L'intervention que vous avez demandé est maintenant terminée avec le statut : ";
        if(inter.getStatut()==STATUT_INTERVENTION.SUCCES)
        {
            message+="reussie.\n";
        }
        else{
            message+="echouee.\n";
        }
        message+= "Commentaire de l'employe chargé de l'intervention : "+inter.getCommentaireFin();
        return message;
    }
    
    public static String clotureInterventionEmploye(Intervention inter){
        String message = "Votre intervention a bien été cloturée.";
        return message;
    }

   
   
    
    
}
