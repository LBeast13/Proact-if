package serialisation.client;

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
public class DemanderInterventionSerialisation extends Serialisation{

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        JsonObject jsonContainer = new JsonObject();
        
        jsonContainer.addProperty("num_interv", (String) request.getAttribute("num_interv"));
        
        // Formattage et écriture sur la sortie
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = this.getWriterWithJsonHeader(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(jsonContainer,out);
    }
    
}
