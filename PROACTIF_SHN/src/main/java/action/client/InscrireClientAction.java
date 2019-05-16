package action.client;

import action.Action;
import com.google.maps.model.LatLng;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.modele.Client;
import metier.service.Service;

/**
 *
 * @author Liam
 */
public class InscrireClientAction extends Action{

    @Override
    public boolean executer(HttpServletRequest request, HttpSession session) {
        
        Date dateInscription = new Date();
        String mdp = (String) request.getParameter("mdp");
        String nom = (String) request.getParameter("nom");
        String prenom = (String) request.getParameter("prenom");
        String civilite = (String) request.getParameter("civilite");
        String date = (String) request.getParameter("date_naissance"); // Convertir en Date
        String numTelephone = (String) request.getParameter("telephone");
        String mail = (String) request.getParameter("email");
        String adresse = (String) request.getParameter("adresse");
        String codePostal = (String) request.getParameter("code_postal"); // Convertir en Integer
        String ville = (String) request.getParameter("ville");
        LatLng coordGPS = new LatLng();       
                
        Client nouvClient = new Client(); 
        
        Service.creerClient(nouvClient);

        return true;
    }
    
}
