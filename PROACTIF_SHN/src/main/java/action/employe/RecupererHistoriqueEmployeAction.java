package action.employe;

import action.Action;
import com.google.maps.model.LatLng;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.modele.Client;
import metier.modele.Employe;
import metier.modele.Intervention;
import metier.modele.Intervention_Animal;
import metier.modele.Intervention_Incident;
import metier.modele.STATUT_INTERVENTION;
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
        
        List<Intervention> interventions = Service.recupererHistoriqueEmploye(emp);
        
        request.setAttribute("interventions", interventions);
        
        return true;
    }
    
}
