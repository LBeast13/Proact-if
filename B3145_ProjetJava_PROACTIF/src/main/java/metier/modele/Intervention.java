/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import com.google.maps.model.LatLng;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ascotto,atondereau
 */

@Entity
@Table(name = "Intervention")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Intervention implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer numIntervention;
    
    private boolean estTerminee;
    private String description;
    private boolean attribue;
    private double distance;
    @Temporal(TemporalType.DATE)    
    private Date dateDebut;
    
    @Temporal(TemporalType.DATE)    
    private Date dateFin;
    
    
    private STATUT_INTERVENTION statut;
    private String commentaireFin;
    private LatLng coordGPS;
    
    @ManyToOne
    private Employe employe_associe;
    @ManyToOne
    private Client client_associe;
    
    public Intervention() {
    }

    public Intervention(boolean estTerminee, String description, boolean attribue, Date dateDebut, Date dateFin, STATUT_INTERVENTION statut, String commentaireFin, LatLng coordGPS, Employe employe_associe, Client client_associe) {
        this.estTerminee = estTerminee;
        this.description = description;
        this.attribue = attribue;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.statut = statut;
        this.commentaireFin = commentaireFin;
        this.coordGPS = coordGPS;
        this.employe_associe = employe_associe;
        this.client_associe = client_associe;
        
    }
    // constructeur utile pour l'appel depuis les services exterieurs = IHM
    public Intervention(String description, Date dateDebut) {
        this.estTerminee = false;
        this.description = description;
        this.attribue = false;
        this.dateDebut = dateDebut;
        this.dateFin=null;
        this.statut=null;
        this.commentaireFin = null;
        this.employe_associe = null;
        this.client_associe = null;
    }

    // getters
    public Integer getNumIntervention() {
        return numIntervention;
    }

    public boolean estTerminee() {
        return estTerminee;
    }

    public boolean isAttribue() {
        return attribue;
    }
    
    public String getDescription() {
        return description;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public Date getDaterFin() {
        return dateFin;
    }
    
    public STATUT_INTERVENTION getStatut() {
        return statut;
    }
    
    public String getCommentaireFin() {
        return commentaireFin;
    }

    public LatLng getCoordGPS() {
        return coordGPS;
    }

    public Employe getEmploye_associe() {
        return employe_associe;
    }

    public Client getClient_associe() {
        return client_associe;
    }
    
    public String getType()
    {
        return "Intervention";
    }
    // setters

    public void setEstTerminee(boolean estTerminee) {
        this.estTerminee = estTerminee;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAttribue(boolean attribue) {
        this.attribue = attribue;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public void setStatut(STATUT_INTERVENTION statut) {
        this.statut = statut;
    }

    public void setCommentaireFin(String commentaireFin) {
        this.commentaireFin = commentaireFin;
    }

    public void setCoordGPS(LatLng coordGPS) {
        this.coordGPS = coordGPS;
    }

    public void setEmploye_associe(Employe employe_associe) {
        this.employe_associe = employe_associe;
    }

    public void setClient_associe(Client client_associe) {
        this.client_associe = client_associe;
    }
    
    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

}
