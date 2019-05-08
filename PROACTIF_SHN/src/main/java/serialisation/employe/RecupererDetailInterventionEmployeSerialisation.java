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
public class RecupererDetailInterventionEmployeSerialisation extends Serialisation{

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
       
        JsonObject jsonContainer = new JsonObject();
        
        jsonContainer.addProperty("numero_interv", (String) request.getAttribute("numero_interv"));
        jsonContainer.addProperty("numero_client", (String) request.getAttribute("numero_client"));
        jsonContainer.addProperty("numero_employe", (String) request.getAttribute("numero_employe"));
        jsonContainer.addProperty("type_interv", (String) request.getAttribute("type_interv"));
        jsonContainer.addProperty("statut_interv", (String) request.getAttribute("statut_interv"));
        jsonContainer.addProperty("objet_interv", (String) request.getAttribute("objet_interv"));
        jsonContainer.addProperty("entreprise_interv", (String) request.getAttribute("entreprise_interv"));
        jsonContainer.addProperty("date_demande", (String) request.getAttribute("date_demande"));
        jsonContainer.addProperty("date_cloture", (String) request.getAttribute("date_cloture"));
        jsonContainer.addProperty("description_interv", (String) request.getAttribute("description_interv"));
        jsonContainer.addProperty("commentaire_interv", (String) request.getAttribute("commentaire_interv"));
        
        //System.out.println(jsonContainer.toString());
        // Formattage et écriture sur la sortie
        PrintWriter out = this.getWriterWithJsonHeader(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(jsonContainer,out);
    }
    
}
