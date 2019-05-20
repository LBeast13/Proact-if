package action.employe;

import action.Action;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.modele.Employe;
import metier.modele.Intervention;
import metier.modele.Intervention_Animal;
import metier.modele.Intervention_Livraison;
import metier.service.Service;

/**
 *
 * @author Liam
 */
public class RecupererInterventionEnCoursEmployeAction extends Action {

    @Override
    public boolean executer(HttpServletRequest request, HttpSession session) {

        String login = (String) session.getAttribute("utilisateur");
        String mdp = (String) session.getAttribute("mdp");

        Employe emp = Service.connecterEmploye(login, mdp);

        List<Intervention> interventions = emp.getMesInterventions();

        if (!emp.getMesInterventions().isEmpty()) {

            Intervention intervEnCours = null;
            request.setAttribute("interv_presentes", "oui");

            // Récupération de l'intervention en cours parmis les interventions de l'employé
            for (Intervention intervention : interventions) {
                if (!intervention.estTerminee()) {
                    intervEnCours = intervention;
                }
            }

            if (intervEnCours != null) {

                // Récupération des informations de l'intervention en cours
                String numInterv = intervEnCours.getNumIntervention().toString();
                String numEmploye = intervEnCours.getEmploye_associe().getIdPersonne().toString();
                String type = intervEnCours.getType();
                String codeClient = intervEnCours.getClient_associe().getIdPersonne().toString();
                String dateDemande = formattageDate(intervEnCours.getDateDebut());
                String trajet = Double.toString(intervEnCours.getDistance());
                String adresse = intervEnCours.getClient_associe().getAdresse()
                        + " " + intervEnCours.getClient_associe().getCodePostal().toString()
                        + " " + intervEnCours.getClient_associe().getVille();
                String description = intervEnCours.getDescription();

                // Remplissage de la requête en fonction du type d'intervention
                switch (type) {
                    case "Intervention Animal":
                        Intervention_Animal intervA = (Intervention_Animal) intervEnCours;
                        request.setAttribute("animal_interv", intervA.getAnimal());
                        break;

                    case "Intervention Livraison":
                        Intervention_Livraison intervL = (Intervention_Livraison) intervEnCours;
                        request.setAttribute("objet_interv", intervL.getObjet());
                        request.setAttribute("entreprise_interv", intervL.getEntreprise());
                        break;

                    case "Intervention Incident":

                        break;
                }

                //Ajout des données indépendante du type d'intervention dans la requête
                request.setAttribute("num_interv", numInterv);
                request.setAttribute("num_employe", numEmploye);
                request.setAttribute("type_interv", type);
                request.setAttribute("numero_client", codeClient);
                request.setAttribute("date_demande_interv", dateDemande);
                request.setAttribute("trajet_interv", trajet);
                request.setAttribute("adresse_interv", adresse);
                request.setAttribute("description_interv", description);
                request.setAttribute("en_cours", "oui");

            } else {
                request.setAttribute("en_cours", "non");
            }
        } else {
            request.setAttribute("interv_presentes", "non");
        }
        return true;
    }

}
