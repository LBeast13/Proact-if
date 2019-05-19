package serialisation.employe;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import serialisation.Serialisation;

/**
 *
 * @author Liam
 */
public class RecupererInterventionEnCoursEmployeSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        JsonObject jsonContainer = new JsonObject();

        String enCours = (String) request.getAttribute("en_cours");

        if (enCours.equals("oui")) {
            String typeInterv = (String) request.getAttribute("type_interv");

            switch (typeInterv) {
                case "Intervention Animal":
                    jsonContainer.addProperty("animal_interv", (String) request.getAttribute("animal_interv"));
                    break;

                case "Intervention Livraison":
                    jsonContainer.addProperty("objet_interv", (String) request.getAttribute("objet_interv"));
                    jsonContainer.addProperty("entreprise_interv", (String) request.getAttribute("entreprise_interv"));
                    break;
            }

            jsonContainer.addProperty("num_interv", (String) request.getAttribute("num_interv"));
            jsonContainer.addProperty("num_employe", (String) request.getAttribute("num_employe"));
            jsonContainer.addProperty("type_interv", typeInterv);
            jsonContainer.addProperty("numero_client", (String) request.getAttribute("numero_client"));
            jsonContainer.addProperty("date_demande_interv", (String) request.getAttribute("date_demande_interv"));
            jsonContainer.addProperty("trajet_interv", (String) request.getAttribute("trajet_interv"));
            jsonContainer.addProperty("adresse_interv", (String) request.getAttribute("adresse_interv"));
            jsonContainer.addProperty("description_interv", (String) request.getAttribute("description_interv"));
            
        }
        
        jsonContainer.addProperty("en_cours", (String) request.getAttribute("en_cours"));

        //System.out.println(jsonContainer.toString());
        // Formattage et écriture sur la sortie
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = this.getWriterWithJsonHeader(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(jsonContainer, out);
    }

}
