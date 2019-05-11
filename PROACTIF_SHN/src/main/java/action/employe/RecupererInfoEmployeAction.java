package action.employe;

import action.Action;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.modele.Employe;
import metier.service.Service;

/**
 *
 * @author Liam
 */
public class RecupererInfoEmployeAction extends Action {

    @Override
    public boolean executer(HttpServletRequest request, HttpSession session) {
        String login = (String) session.getAttribute("utilisateur");
        String mdp = (String) session.getAttribute("mdp");
        
        Employe emp = Service.connecterEmploye(login, mdp);
        
        // Informations de l'employé
        String nom = emp.getNom();
        String prenom = emp.getPrenom();
        String civilite = emp.getCivilite();
        String dateNaissance = formattageDate(emp.getDate());
        String email = emp.getMail();
        String tel = emp.getNumTelephone();
        String adresse = emp.getAdresse();
        Integer codePostal = emp.getCodePostal();
        String ville = emp.getVille();
        
        // Ajout des informations dans la requête
        request.setAttribute("nom", nom);
        request.setAttribute("prenom", prenom);
        request.setAttribute("civilite", civilite);
        request.setAttribute("dateNaissance", dateNaissance);
        request.setAttribute("email", email);
        request.setAttribute("tel", tel);
        request.setAttribute("adresse", adresse);
        request.setAttribute("codePostal", codePostal.toString());
        request.setAttribute("ville", ville);
        
        return true;
    }
    
}
