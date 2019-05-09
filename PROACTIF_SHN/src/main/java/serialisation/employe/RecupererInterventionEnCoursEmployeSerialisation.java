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
public class RecupererInterventionEnCoursEmployeSerialisation extends Serialisation{

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        JsonObject jsonContainer = new JsonObject();
        
        jsonContainer.addProperty("num_interv", (String) request.getAttribute("num_interv"));
        jsonContainer.addProperty("num_employe", (String) request.getAttribute("num_employe"));
        jsonContainer.addProperty("type_interv", (String) request.getAttribute("type_interv"));
        jsonContainer.addProperty("detail_type_interv", (String) request.getAttribute("detail_type_interv"));
        jsonContainer.addProperty("numero_client", (String) request.getAttribute("numero_client"));
        jsonContainer.addProperty("date_demande_interv", (String) request.getAttribute("date_demande_interv"));
        jsonContainer.addProperty("trajet_interv", (String) request.getAttribute("trajet_interv"));
        jsonContainer.addProperty("adresse_interv", (String) request.getAttribute("adresse_interv"));
        jsonContainer.addProperty("description_interv", (String) request.getAttribute("description_interv"));

        //System.out.println(jsonContainer.toString());
        // Formattage et écriture sur la sortie
        PrintWriter out = this.getWriterWithJsonHeader(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(jsonContainer,out);
    }
    
}
