package action.employe;

import action.Action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.modele.Intervention;
import metier.modele.Intervention_Animal;
import metier.modele.Intervention_Livraison;
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
        request.setAttribute("date_demande", formattageDate(interv.getDateDebut()));
        request.setAttribute("statut_interv", getStatut(interv));
        request.setAttribute("description_interv", interv.getDescription());
        request.setAttribute("commentaire_interv", interv.getCommentaireFin());
        
        switch(interv.getType()){
            case "Intervention Animal":
                Intervention_Animal intervAnimal = (Intervention_Animal) interv;
                request.setAttribute("animal_interv", intervAnimal.getAnimal());
                //System.out.println(intervAnimal.getAnimal());
                break;
                
            case "Intervention Livraison":
                Intervention_Livraison intervLivraison = (Intervention_Livraison) interv;
                request.setAttribute("objet_interv", intervLivraison.getObjet());
                request.setAttribute("entreprise_interv", intervLivraison.getEntreprise());
                //System.out.println(intervLivraison.getObjet());
                //System.out.println(intervLivraison.getEntreprise());
                break;
        }
        
        if(interv.estTerminee()){
            request.setAttribute("date_cloture", formattageDate(interv.getDaterFin())); 
        } else{
            request.setAttribute("date_cloture", "En cours...");
        }

        return true;
    }
    
    /**
     * Formate le statut de l'intervention
     * @param interv l'intervention à formater
     * @return le statut formaté
     */
    private String getStatut(Intervention interv) {
        if (interv.getDaterFin() != null) {
            String statut = interv.getStatut().toString();
            switch (statut) {
                case "SUCCES":
                    return "Succès";
                case "ECHEC":
                    return "Echec";
                default:
                    return "Erreur statut";
            }
        } else {
            return "En cours";
        }
    }
    
}
