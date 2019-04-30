/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import com.google.maps.model.LatLng;
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
public class Intervention_Livraison extends Intervention{
    private String objet;
    private String entreprise;

    public Intervention_Livraison() {
    }
    
    public Intervention_Livraison(String objet, String entreprise, boolean estTerminee, String description, boolean attribue, Date dateDebut, Date dateFin, STATUT_INTERVENTION statut, String commentaireFin, LatLng coordGPS, Employe employe_associe, Client client_associe) {
        super(estTerminee, description, attribue, dateDebut, dateFin, statut, commentaireFin, coordGPS, employe_associe, client_associe);
        this.objet = objet;
        this.entreprise = entreprise;
    }
    
    public Intervention_Livraison(String objet, String entreprise, String description, Date dateDebut) {
        super(description, dateDebut);
        this.objet = objet;
        this.entreprise = entreprise;
    }
    
    
    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getEntreprise() {
        return entreprise;
    }
    
    public String getType()
    {
        return super.getType()+" Livraison";
    }
    
    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }
    
}
