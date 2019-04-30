/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import com.google.maps.model.LatLng;
import java.time.LocalDate;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 *
 * @author ascotto
 */

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Intervention_Animal extends Intervention {

    public Intervention_Animal() {
    }
    
    public Intervention_Animal(String animal, boolean estTerminee, String description, boolean attribue, Date dateDebut, Date dateFin, STATUT_INTERVENTION statut, String commentaireFin, LatLng coordGPS, Employe employe_associe, Client client_associe) {
        super(estTerminee, description, attribue, dateDebut, dateFin, statut, commentaireFin, coordGPS, employe_associe, client_associe);
        this.animal = animal;
    }
    
    public Intervention_Animal(String animal, String description, Date dateDebut) {
        super(description, dateDebut);
        this.animal = animal;
    }
    
    private String animal;

    public String getAnimal() {
        return animal;
    }
    public String getType()
    {
        return super.getType()+" Animal";
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }
    
    
}
