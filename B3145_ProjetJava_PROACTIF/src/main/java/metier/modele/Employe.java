/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import com.google.maps.model.LatLng;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author ascotto
 */
@Entity
@Table(name = "Employe")
@PrimaryKeyJoinColumn(name = "id")
public class Employe extends Personne {

    private static final long serialVersionUID = 1L;
    
    @Column(name = "Horaires")
    private List<double[]> horaires;
    
    @JoinColumn(name = "MesInterventions")
    @OneToMany(mappedBy="employe_associe")  
    private List<Intervention> mesInterventions;
    
    @Column(name = "Disponible")
    private boolean disponible;
    
    public Employe() {
    }

    public Employe(List<double[]> horaires, String mdp, String nom, String prenom, String civilite, Date date, String numTelephone, String mail, String adresse, Integer codePostal, String ville, LatLng coordGPS) {
        super(mdp, nom, prenom, civilite, date, numTelephone, mail, adresse, codePostal, ville, coordGPS);
        this.horaires = horaires;
        this.disponible = true;
    }


   
    

    public List<double[]> getHoraires() {
        return horaires;
    }

    public List<Intervention> getMesInterventions() {
        return mesInterventions;
    }

    public boolean getDisponible() {
        return disponible;
    }
    
    public void setHoraires(List<double[]> horaires) {
        this.horaires = horaires;
    }

    public void setDisponible( boolean dispo ) {
        this.disponible = dispo;
    }
    
    public void addToMesInterventions(Intervention interAjout) {
        this.mesInterventions.add(interAjout);
    }
}
