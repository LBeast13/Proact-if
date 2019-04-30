/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import com.google.maps.model.LatLng;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 *
 * @author ascotto
 */
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Intervention_Incident extends Intervention{

    public Intervention_Incident() {
    }

    public Intervention_Incident(boolean estTerminee, String description, boolean attribue, Date dateDebut, Date dateFin, STATUT_INTERVENTION statut, String commentaireFin, LatLng coordGPS, Employe employe_associe, Client client_associe) {
        super(estTerminee, description, attribue, dateDebut, dateFin, statut, commentaireFin, coordGPS, employe_associe, client_associe);
    }

    public Intervention_Incident(String description, Date dateDebut) {
        super(description, dateDebut);
    }
    public String getType()
    {
        return super.getType()+" Incident";
    }
    
}
