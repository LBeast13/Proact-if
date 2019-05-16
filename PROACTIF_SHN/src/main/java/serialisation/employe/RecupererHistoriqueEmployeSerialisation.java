package serialisation.employe;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.modele.Intervention;
import serialisation.Serialisation;

/**
 *
 * @author Liam
 */
public class RecupererHistoriqueEmployeSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        JsonObject jsonContainer = new JsonObject();

        // Cas liste vide
        if (request.getAttribute("interventions") == "Vide") {
            jsonContainer.addProperty("interventions", "Vide");
        } 
        
        // Cas liste non vide
        else {
            JsonArray jsonArrayInterventions = new JsonArray();
            List<Intervention> interventions = (List<Intervention>) request.getAttribute("interventions");

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");

            for (Intervention intervention : interventions) {
                JsonObject jsonIntervention = new JsonObject();

                String statut = getStatut(intervention);
                String type = getType(intervention);
                String prenomClient = intervention.getClient_associe().getPrenom();
                String nomClient = intervention.getClient_associe().getNom();

                jsonIntervention.addProperty("numero", intervention.getNumIntervention().toString());
                jsonIntervention.addProperty("type", type);
                jsonIntervention.addProperty("date", dateFormat.format(intervention.getDateDebut())); // Gérer la date pour le statut
                jsonIntervention.addProperty("client", prenomClient + " " + nomClient);
                jsonIntervention.addProperty("statut", statut);  // Gérer le statut ATTENTION au NULL

                jsonArrayInterventions.add(jsonIntervention);
            }

            jsonContainer.add("interventions", jsonArrayInterventions);
        }

        //System.out.println(jsonContainer.toString());
        // Formattage et écriture sur la sortie
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = this.getWriterWithJsonHeader(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(jsonContainer, out);
    }

    /**
     * Formate le statut de l'intervention
     * @param interv l'intervention à formater
     * @return le statut formaté
     */
    private String getStatut(Intervention interv) {
        if (interv.getDaterFin() != null) {
            String statut = interv.getStatut().toString();
            switch (statut) {
                case "SUCCES":
                    return "Succès";
                case "ECHEC":
                    return "Echec";
                default:
                    return "Erreur statut";
            }
        } else {
            return "En cours";
        }
    }

    /**
     * Fonction de formattage du type de l'intervention pour l'affichage.
     *
     * @param interv L'intervention à formatter
     * @return Le type formatté
     */
    private String getType(Intervention interv) {
        String type = "";

        switch (interv.getType()) {
            case "Intervention Animal":
                type = "Animal";
                break;
            case "Intervention Livraison":
                type = "Livraison";
                break;
            case "Intervention Incident":
                type = "Incident";
                break;
        }

        return type;
    }
}
