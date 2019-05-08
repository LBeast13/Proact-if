package action.employe;

import action.Action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.modele.Employe;
import metier.modele.Intervention;
import metier.service.Service;

/**
 *
 * @author Liam
 */
public class RecupererDetailInterventionEmployeAction extends Action {

    @Override
    public boolean executer(HttpServletRequest request, HttpSession session) {
      
        System.out.println((String)request.getAttribute("numero_interv"));
        // Récupérationdu numéro d'intervention
        Integer numeroInterv = Integer.parseInt((String)request.getParameter("numero_interv"));
    
        Intervention interv = Service.getIntervention(numeroInterv);

        request.setAttribute("numero_interv", interv.getNumIntervention().toString());
        request.setAttribute("numero_client", interv.getClient_associe().getIdPersonne().toString());
        request.setAttribute("numero_employe", interv.getEmploye_associe().getIdPersonne().toString());
        request.setAttribute("type_interv", interv.getType());
        request.setAttribute("statut_interv", interv.getStatut().toString());
        request.setAttribute("objet_interv", "A modifier");
        request.setAttribute("entreprise_interv", "A modifier");
        request.setAttribute("date_demande", interv.getDateDebut().toString());
        request.setAttribute("date_cloture", interv.getDaterFin().toString());
        request.setAttribute("description_interv", interv.getDescription());
        request.setAttribute("commentaire_interv", interv.getCommentaireFin());
        
        return true;
    }
    
}
