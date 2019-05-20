package action.employe;

import action.Action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import metier.modele.Intervention;
import metier.service.Service;

/**
 *
 * @author Liam
 */
public class RecupererEmplacementsInterventionsAction extends Action{

    @Override
    public boolean executer(HttpServletRequest request, HttpSession session) {

        Date date = new Date();
        
        List<Intervention> intervDuJour = Service.recupererCarteJour(date);
        
        request.setAttribute("interventions_du_jour", intervDuJour);
        
        return true;
    }
    
}
