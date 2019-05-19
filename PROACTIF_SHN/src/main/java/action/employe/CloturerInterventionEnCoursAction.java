package action.employe;

import action.Action;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.modele.Intervention;
import metier.modele.STATUT_INTERVENTION;
import metier.service.Service;

/**
 *
 * @author Liam
 */
public class CloturerInterventionEnCoursAction extends Action{

    @Override
    public boolean executer(HttpServletRequest request, HttpSession session) {

        try {
            Integer numInterv = Integer.parseInt((String) request.getParameter("numero_interv"));
            String dateString = ((String) request.getParameter("date_cloture"));
            String commentaire = (String) request.getParameter("commentaire");
            String statut = (String) request.getParameter("statut");
            
            // Formattage date
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = format.parse(dateString);
            GregorianCalendar dateFin = new GregorianCalendar();
            dateFin.setTime(date);
            
            
            Intervention intervClot = Service.getIntervention(numInterv);
            
            
            if(statut.equals("Succès")){
                Service.cloturerIntervention(intervClot, dateFin, commentaire, STATUT_INTERVENTION.SUCCES);
            } else if(statut.equals("Echec")){
                Service.cloturerIntervention(intervClot, dateFin, commentaire, STATUT_INTERVENTION.ECHEC);
            }
            
        } catch (ParseException ex) {
            Logger.getLogger(CloturerInterventionEnCoursAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true;
    }
    
}
