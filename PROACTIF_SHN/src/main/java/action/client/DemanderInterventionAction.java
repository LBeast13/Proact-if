package action.client;

import action.Action;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.modele.Client;
import metier.modele.Intervention;
import metier.modele.Intervention_Animal;
import metier.modele.Intervention_Incident;
import metier.modele.Intervention_Livraison;
import metier.service.Service;

/**
 *
 * @author Liam
 */
public class DemanderInterventionAction extends Action{

    @Override
    public boolean executer(HttpServletRequest request, HttpSession session) {

        String login = (String) session.getAttribute("utilisateur");
        String mdp = (String) session.getAttribute("mdp");
        String typeInterv = (String) request.getParameter("type");
        String description = (String) request.getParameter("description");
        Date date = new Date();
        
        Client demandeur = Service.connecterClient(login, mdp);
        
        Intervention interv = null;
        
        switch(typeInterv){
            case "Animal":
                String animal = (String) request.getParameter("animal");
                interv = new Intervention_Animal(animal,description,date);
                break;
                
            case "Livraison":
                String objet = (String) request.getParameter("objet");
                String entreprise = (String) request.getParameter("entreprise");
                interv = new Intervention_Livraison(objet,entreprise,description,date);
                break;
                
            case "Incident":
                interv = new Intervention_Incident(description,date);
                break;
        }
        
        Intervention demande = Service.demanderIntervention(demandeur, interv);
        
        request.setAttribute("num_interv", demande.getNumIntervention().toString());
        
        return true;
    }
    
}
