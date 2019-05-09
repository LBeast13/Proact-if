package action.employe;

import action.Action;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.modele.Employe;
import metier.modele.Intervention;
import metier.service.Service;

/**
 *
 * @author Liam
 */
public class RecupererInterventionEnCoursEmployeAction extends Action{

    @Override
    public boolean executer(HttpServletRequest request, HttpSession session) {

        String login = (String) session.getAttribute("utilisateur");
        String mdp = (String) session.getAttribute("mdp");
        
        Employe emp = Service.connecterEmploye(login, mdp);
        
        List<Intervention> interventions = emp.getMesInterventions();
        
        Intervention intervEnCours = null;
       
        // Récupération de l'intervention en cours parmis les interventions de l'employé
        for(Intervention intervention: interventions){
            if(!intervention.estTerminee()){
                intervEnCours = intervention;
            }
        }
        
        // Récupération des informations de l'intervention en cours
        String type = intervEnCours.getType();
        String detail = "A gérer";
        String codeClient = intervEnCours.getClient_associe().getIdPersonne().toString();
        String dateDemande = intervEnCours.getDateDebut().toString();
        String trajet = Double.toString(intervEnCours.getDistance());
        String adresse = "A faire";
        String description = intervEnCours.getDescription();
        
        //Ajout des données dans la requête
        request.setAttribute("type_interv", type);
        request.setAttribute("detail_type_interv", detail);
        request.setAttribute("numero_client", codeClient);
        request.setAttribute("date_demande_interv", dateDemande);
        request.setAttribute("trajet_interv", trajet);
        request.setAttribute("adresse_interv", adresse);
        request.setAttribute("description_interv", description);

        return true;
    }
    
}
