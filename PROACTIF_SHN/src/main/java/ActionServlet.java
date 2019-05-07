/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import action.Action;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import dao.JpaUtil;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import metier.modele.Employe;
import metier.service.Service;
import serialisation.Serialisation;

/**
 *
 * @author lbette
 */
@WebServlet(urlPatterns = {"/ActionServlet"})
public class ActionServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(true); // Contexte de la Session
        request.setCharacterEncoding("UTF-8"); // Encodage des paramètres de la requête
        String todo = request.getParameter("todo"); // Paramètre de choix de l'action
        
        Action action = null;
        Serialisation serialisation = null;
        
        // Cas de connexion d'employe
        if("connecterEmploye".equals(todo)){
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            
            JsonObject jsonContainer = new JsonObject();
            
            Employe c = Service.connecterEmploye(login, password); 
        
            if(c != null){ // Cas Login et password corrects
                session.setAttribute("utilisateur",login);  // Enregistrement de la session
                
                jsonContainer.addProperty("connexion", Boolean.TRUE);
                System.out.println("Employé connecté");
            }
            else{  // Echec Connexion
                jsonContainer.addProperty("connexion", Boolean.FALSE);
                System.out.println("Erreur de connexion employé");
            }
            
            // Formattage et écriture de la sortie
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter out = response.getWriter();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(jsonContainer,out);
            
        } else{
            String user = (String) session.getAttribute("utilisateur");
            if(user==null){
                response.sendError(403,"Forbidden (No User)");
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        JpaUtil.init();
    }

    @Override
    public void destroy() {
        super.destroy(); //To change body of generated methods, choose Tools | Templates.
        JpaUtil.destroy();
    }

    


    
}
