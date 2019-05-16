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
public class RecupererInfoEmployeSerialisation extends Serialisation{

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException  {
        
        JsonObject jsonContainer = new JsonObject();
        
        jsonContainer.addProperty("civilite", (String) request.getAttribute("civilite"));
        jsonContainer.addProperty("nom", (String) request.getAttribute("nom"));
        jsonContainer.addProperty("prenom", (String) request.getAttribute("prenom"));
        jsonContainer.addProperty("dateNaissance", (String) request.getAttribute("dateNaissance"));
        jsonContainer.addProperty("email", (String) request.getAttribute("email"));
        jsonContainer.addProperty("tel", (String) request.getAttribute("tel"));
        jsonContainer.addProperty("adresse", (String) request.getAttribute("adresse"));
        jsonContainer.addProperty("codePostal", (String) request.getAttribute("codePostal"));
        jsonContainer.addProperty("ville", (String) request.getAttribute("ville"));

        //System.out.println(jsonContainer.toString());
        // Formattage et écriture sur la sortie
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = this.getWriterWithJsonHeader(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(jsonContainer,out);
    }
    
}
