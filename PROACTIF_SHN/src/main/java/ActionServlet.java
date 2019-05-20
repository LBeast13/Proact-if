
import action.Action;
import action.RecupererNomPrenomAction;
import action.client.DemanderInterventionAction;
import action.client.InscrireClientAction;
import action.client.ModifierInfoClientAction;
import action.client.RecupererHistoriqueClientAction;
import action.client.RecupererInfoClientAction;
import action.employe.CloturerInterventionEnCoursAction;
import action.employe.RecupererDetailInterventionEmployeAction;
import action.employe.RecupererEmplacementsInterventionsAction;
import action.employe.RecupererHistoriqueEmployeAction;
import action.employe.RecupererInfoEmployeAction;
import action.employe.RecupererInterventionEnCoursEmployeAction;
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
import metier.modele.Client;
import metier.service.Service;
import serialisation.RecupererNomPrenomSerialisation;
import serialisation.Serialisation;
import serialisation.client.DemanderInterventionSerialisation;
import serialisation.client.ModifierInfoClientSerialisation;
import serialisation.client.RecupererHistoriqueClientSerialisation;
import serialisation.client.RecupererInfoClientSerialisation;
import serialisation.employe.CloturerInterventionEnCoursSerialisation;
import serialisation.employe.RecupererDetailInterventionEmployeSerialisation;
import serialisation.employe.RecupererEmplacementsInterventionsSerialisation;
import serialisation.employe.RecupererHistoriqueEmployeSerialisation;
import serialisation.employe.RecupererInfoEmployeSerialisation;
import serialisation.employe.RecupererInterventionEnCoursEmployeSerialisation;

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
        System.out.println(todo);

        // Cas de connexion d'employe
        if ("connecterEmploye".equals(todo)) {
            String login = request.getParameter("login");
            String password = request.getParameter("password");

            JsonObject jsonContainer = new JsonObject();

            Employe c = Service.connecterEmploye(login, password);

            if (c != null) { // Cas Login et password corrects
                // Enregistrement de la session
                session.setAttribute("utilisateur", login);
                session.setAttribute("mdp", password);

                jsonContainer.addProperty("connexion", Boolean.TRUE);
                System.out.println("Test connexion : Employé connecté");
            } else {  // Echec Connexion
                jsonContainer.addProperty("connexion", Boolean.FALSE);
                System.out.println("Test connexion : Erreur de connexion employé");
            }

            // Formattage et écriture de la sortie
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter out = response.getWriter();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(jsonContainer, out);

        } // Cas de connexion de client
        else if ("connecterClient".equals(todo)) {
            String login = request.getParameter("login");
            String password = request.getParameter("password");

            JsonObject jsonContainer = new JsonObject();

            Client c = Service.connecterClient(login, password);

            if (c != null) { // Cas Login et password corrects
                // Enregistrement de la session
                session.setAttribute("utilisateur", login);
                session.setAttribute("mdp", password);

                jsonContainer.addProperty("connexion", Boolean.TRUE);
                System.out.println("Test connexion : Client connecté");
            } else {  // Echec Connexion
                jsonContainer.addProperty("connexion", Boolean.FALSE);
                System.out.println("Test connexion : Erreur de connexion client");
            }

            // Formattage et écriture de la sortie
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter out = response.getWriter();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(jsonContainer, out);

        } 
        // Déconnexion
        else if("deconnexion".equals(todo)){
            session.setAttribute("utilisateur", null);
            session.setAttribute("mdp", null);
            
            JsonObject jsonContainer = new JsonObject();
            jsonContainer.addProperty("connexion", Boolean.FALSE);
            
            // Formattage et écriture de la sortie
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter out = response.getWriter();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(jsonContainer, out);
        }
        // Autres actions
        else {
           
            String user = (String) session.getAttribute("utilisateur");
            System.out.println("Test utilisateur dans la session " + user);
            // Cas utilisateur non connecté (accès refusé)
            if (user == null && !"inscrire_client".equals(todo)) {
                response.sendError(403, "Forbidden (No User)");
            } // Cas utilisateur connecté
            else {
                Action action = null;
                Serialisation serialisation = null;

                // Ensemble des actions
                switch (todo) {
                    case "remplir_informations_perso_employe":
                        action = new RecupererInfoEmployeAction();
                        serialisation = new RecupererInfoEmployeSerialisation();
                        System.out.println("Test appel de la fonction " + todo + " OK");
                        break;

                    case "remplir_historique_interventions_employe":
                        action = new RecupererHistoriqueEmployeAction();
                        serialisation = new RecupererHistoriqueEmployeSerialisation();
                        System.out.println("Test appel de la fonction " + todo + " OK");
                        break;

                    case "remplir_detail_intervention":
                        action = new RecupererDetailInterventionEmployeAction();
                        serialisation = new RecupererDetailInterventionEmployeSerialisation();
                        System.out.println("Test appel de la fonction " + todo + " OK");
                        break;

                    case "remplir_intervention_en_cours":
                        action = new RecupererInterventionEnCoursEmployeAction();
                        serialisation = new RecupererInterventionEnCoursEmployeSerialisation();
                        System.out.println("Test appel de la fonction " + todo + " OK");
                        break;
                        
                    case "cloturer_intervention_en_cours":
                        action = new CloturerInterventionEnCoursAction();
                        serialisation = new CloturerInterventionEnCoursSerialisation();
                        System.out.println("Test appel de la fonction " + todo + " OK");
                        break;

                    case "recuperer_emplacements_interventions":
                        action = new RecupererEmplacementsInterventionsAction();
                        serialisation = new RecupererEmplacementsInterventionsSerialisation();
                        System.out.println("Test appel de la fonction " + todo + " OK");
                        break;

                    case "inscrire_client":
                        action = new InscrireClientAction();
                        serialisation = new RecupererInfoClientSerialisation();
                        System.out.println("Test appel de la fonction " + todo + " OK");
                        break;

                    case "remplir_informations_perso_client":
                        action = new RecupererInfoClientAction();
                        serialisation = new RecupererInfoClientSerialisation();
                        System.out.println("Test appel de la fonction " + todo + " OK");
                        break;

                    case "modifer_info_perso_client":
                        action = new ModifierInfoClientAction();
                        serialisation = new ModifierInfoClientSerialisation();
                        System.out.println("Test appel de la fonction " + todo + " OK");
                        break;

                    case "demander_intervention":
                        action = new DemanderInterventionAction();
                        serialisation = new DemanderInterventionSerialisation();
                        System.out.println("Test appel de la fonction " + todo + " OK");
                        break;
                        
                    case "remplir_historique_interventions_client":
                        action = new RecupererHistoriqueClientAction();
                        serialisation = new RecupererHistoriqueClientSerialisation();
                        System.out.println("Test appel de la fonction " + todo + " OK");
                        break;
                    
                    case "recuperer_nom_prenom":
                        action = new RecupererNomPrenomAction();
                        serialisation = new RecupererNomPrenomSerialisation();
                        System.out.println("Test appel de la fonction " + todo + " OK");
                        break;
                }

                // Cas action introuvable
                if (action == null) {
                    response.sendError(400, "Bad Request (Wrong TODO parameter)");
                    System.out.println("TODO inconnu");
                } // Execution de l'action et de la sérialisation
                else {
                    System.out.println("TODO : " + todo);
                    boolean actionStatus = action.executer(request, session);
                    if (serialisation != null) {
                        serialisation.serialiser(request, response);
                        System.out.println("Test Sérialisation OK");
                    }
                }
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
