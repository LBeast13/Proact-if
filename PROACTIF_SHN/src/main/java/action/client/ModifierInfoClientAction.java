/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.client;

import action.Action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.modele.Client;
import metier.service.Service;

/**
 *
 * @author tremy
 */
public class ModifierInfoClientAction extends Action {

    @Override
    public boolean executer(HttpServletRequest request, HttpSession session) {
        
        String login = (String) session.getAttribute("utilisateur");
        String mdp = (String) session.getAttribute("mdp");
        
        Client c = Service.connecterClient(login, mdp);
        System.out.println("Test Modification");
        
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String tel = request.getParameter("numero");
        String adresse = request.getParameter("adresse");
        String codePostal = request.getParameter("codePostal");
        String ville = request.getParameter("ville");
        
        c.setNom(nom);
        c.setPrenom(prenom);
        c.setNumTelephone(tel);
        c.setAdresse(adresse);
        c.setCodePostal(Integer.parseInt(codePostal));
        c.setVille(ville);
        
        Service.modifierInfosClients(c);
        
        return true;
    }
    
}
