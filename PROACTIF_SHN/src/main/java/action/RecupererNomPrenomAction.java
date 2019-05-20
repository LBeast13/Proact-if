/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.modele.Client;
import metier.modele.Employe;
import metier.modele.Personne;
import metier.service.Service;

/**
 *
 * @author Liam
 */
public class RecupererNomPrenomAction extends Action{

    @Override
    public boolean executer(HttpServletRequest request, HttpSession session) {

        String login = (String) session.getAttribute("utilisateur");
        String mdp = (String) session.getAttribute("mdp");
        
        Personne p = Service.connecterEmploye(login, mdp);
        if(p == null){
            p = Service.connecterClient(login, mdp);
        }
        
        request.setAttribute("nom", p.getNom());
        request.setAttribute("prenom", p.getPrenom());
        
        return true;
    }
    
}
