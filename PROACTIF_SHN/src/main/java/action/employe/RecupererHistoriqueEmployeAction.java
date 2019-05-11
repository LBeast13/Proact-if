package action.employe;

import action.Action;
import java.util.ArrayList;
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
public class RecupererHistoriqueEmployeAction extends Action{

    @Override
    public boolean executer(HttpServletRequest request, HttpSession session) {
        String login = (String) session.getAttribute("utilisateur");
        String mdp = (String) session.getAttribute("mdp");
        
        Employe emp = Service.connecterEmploye(login, mdp);
        
        // Filtres
        String typeFiltre = (String) request.getParameter("type");
        String enCoursFiltre = (String) request.getParameter("enCours");
        
        List<Intervention> interventions = Service.recupererHistoriqueEmploye(emp);
        List<Intervention> intervFiltre = getInterventionsFiltres(interventions, typeFiltre, enCoursFiltre);

        // Test récupération des filtres
        System.out.println("Test filtres : " +  typeFiltre);
        System.out.println("Test filtres : " +  enCoursFiltre);

        if(intervFiltre.isEmpty()){
            request.setAttribute("interventions", "Vide");
        }
        else{
            request.setAttribute("interventions", intervFiltre);
        }

        return true;
    }
    
    /**
     * Applique les filtres à la liste d'interventions.
     * @param interventions Liste des interventions non filtrées
     * @param type Le type pour le filtre
     * @param enCours Le filtre d'intervention terminée
     * @return La liste d'interventions filtrée
     */
    private List<Intervention> getInterventionsFiltres(List<Intervention> interventions, String type, String enCours){
        List<Intervention> intervFiltres = new ArrayList<>();
        
        if(type.equals("Type") && enCours.equals("false")){ // Cas pas de filtres
            intervFiltres = interventions;
        }
        
        else if(!type.equals("Type")){  // Cas filtre sur le type
            if(enCours.equals("true")){ //Cas type + en cours
                
                for(Intervention interv: interventions){
                    if(interv.estTerminee() == false 
                       && interv.getType().equals("Intervention "+type)){
                        intervFiltres.add(interv);
                    }
                }
            }
            else if(enCours.equals("false")){ // Cas type
                for(Intervention interv: interventions){
                    if(interv.getType().equals("Intervention "+type)){
                        intervFiltres.add(interv);
                    }
                }
            }
        }
        
        else if(enCours.equals("true")){ // Cas en cours
            if(!type.equals("Type")){   // Cas en cours et type
                
                for(Intervention interv: interventions){
                    if(interv.estTerminee() == false 
                       && interv.getType().equals("Intervention "+type)){
                        intervFiltres.add(interv);
                    }
                }
            }
            else if(type.equals("Type")){ //Cas en cours
                
                for(Intervention interv: interventions){
                    if(interv.estTerminee() == false){
                        intervFiltres.add(interv);
                    }
                }
            }
        }
        
        
        return intervFiltres;
    }
}
