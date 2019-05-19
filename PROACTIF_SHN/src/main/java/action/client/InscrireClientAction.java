package action.client;

import action.Action;
import com.google.maps.model.LatLng;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.modele.Client;
import metier.service.Service;
import util.GeoTest;

/**
 *
 * @author Liam
 */
public class InscrireClientAction extends Action{

    @Override
    public boolean executer(HttpServletRequest request, HttpSession session) {
        
        try {
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
            LatLng coordGPS = GeoTest.getLatLng(adresse + " " + codePostal + " " + ville);
            
            // Conversion de la date
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = format.format(new Date());
            Date dateConv = format.parse(date);
            
            Client nouvClient = new Client(new Date(), mdp, nom, prenom, civilite,
                    dateConv, numTelephone, mail, adresse,
                    Integer.parseInt(codePostal), ville, coordGPS);
            
            nouvClient = Service.creerClient(nouvClient);
            
            // Ajout des informations dans la requête
            request.setAttribute("nom", nouvClient.getNom());
            request.setAttribute("prenom", nouvClient.getPrenom());
            request.setAttribute("civilite", nouvClient.getCivilite());
            request.setAttribute("dateNaissance", formattageDate(nouvClient.getDate()));
            request.setAttribute("email", nouvClient.getMail());
            request.setAttribute("tel", nouvClient.getNumTelephone());
            request.setAttribute("adresse", nouvClient.getAdresse());
            request.setAttribute("codePostal", nouvClient.getCodePostal().toString());
            request.setAttribute("ville", nouvClient.getVille());
            
        } catch (ParseException ex) {
            Logger.getLogger(InscrireClientAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true;
    }
    
}
