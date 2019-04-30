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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "Client")
@PrimaryKeyJoinColumn(name = "id")
public class Client extends Personne {
    
    private static final long serialVersionUID = 1L;
    
    @Column(name = "DateInscription")
    @Temporal(TemporalType.DATE)  
    private Date dateInscription;

    @JoinColumn(name = "MesInterventions")
    @OneToMany(mappedBy="client_associe")  
    private List<Intervention> mesInterventions;
    
    
    public Client() {
    }

    public Client(Date dateInscription, String mdp, String nom, String prenom, String civilite, Date date, String numTelephone, String mail, String adresse, Integer codePostal, String ville, LatLng coordGPS) {
        super(mdp, nom, prenom, civilite, date, numTelephone, mail, adresse, codePostal, ville, coordGPS);
        this.dateInscription = dateInscription;
    }





    public Date getDateInscription() {
        return dateInscription;
    }

    public List<Intervention> getMesInterventions() {
        return mesInterventions;
    }

    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }

    public void setMesInterventions(Intervention interAjout) {
        this.mesInterventions.add(interAjout);
    }

    

    
    
    
}
