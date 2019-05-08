
package serialisation.employe;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.modele.Intervention;
import serialisation.Serialisation;

/**
 *
 * @author Liam
 */
public class RecupererHistoriqueEmployeSerialisation extends Serialisation{

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        JsonObject jsonContainer = new JsonObject();
        
        JsonArray jsonArrayInterventions = new JsonArray();
        
        List<Intervention> interventions = (List<Intervention>) request.getAttribute("interventions");
        
        for(Intervention intervention: interventions){
            JsonObject jsonIntervention = new JsonObject();
            
            String prenomClient = intervention.getClient_associe().getPrenom();
            String nomClient = intervention.getClient_associe().getNom();  
            
            jsonIntervention.addProperty("numero", intervention.getNumIntervention().toString());
            jsonIntervention.addProperty("type", intervention.getType());
            jsonIntervention.addProperty("date", intervention.getDateDebut().toString()); // Gérer la date pour le statut
            jsonIntervention.addProperty("client", prenomClient + " " + nomClient);
            jsonIntervention.addProperty("statut", "test");  // Gérer le statut ATTENTION au NULL
            
            jsonArrayInterventions.add(jsonIntervention);
        }
        
        jsonContainer.add("interventions",jsonArrayInterventions);

        //System.out.println(jsonContainer.toString());
        // Formattage et écriture sur la sortie
        PrintWriter out = this.getWriterWithJsonHeader(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(jsonContainer,out);
    }
    
}
