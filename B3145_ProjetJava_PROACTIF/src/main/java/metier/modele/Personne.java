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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author alexa
 */
@Entity
@Table(name = "Personne")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Personne implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id")
    protected Integer idPersonne;
        
    
    @Column(name = "Mot_de_passe")
    protected String mdp;
    
    @Column(name = "Nom")
    private String nom;

    @Column(name = "Prenom")
    private String prenom;

    @Column(name = "Civilite")
    private String civilite;

    @Column(name = "Date")
    @Temporal(TemporalType.DATE)  
    private Date date;

    @Column(name = "NumTelephone")    
    private String numTelephone;

    @Column(name = "Mail")    
    private String mail;
    
    @Column(name = "Adresse")    
    private String adresse;

    @Column(name = "CodePostal")    
    private Integer codePostal;
    
    @Column(name = "Ville")    
    private String ville;
   
    @Column(name = "CoordGPS")    
    private LatLng coordGPS;
    
    
    
    public Personne() {
    }

    public Personne(String mdp, String nom, String prenom, String civilite, Date date, String numTelephone, String mail, String adresse, Integer codePostal, String ville, LatLng coordGPS) {
        this.mdp = mdp;
        this.nom = nom;
        this.prenom = prenom;
        this.civilite = civilite;
        this.date = date;
        this.numTelephone = numTelephone;
        this.mail = mail;
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.ville = ville;
        this.coordGPS = coordGPS;
    }

    
    

    public Integer getIdPersonne() {
        return idPersonne;
    }

    
    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getCivilite() {
        return civilite;
    }

    public Date getDate() {
        return date;
    }

    public String getNumTelephone() {
        return numTelephone;
    }

    public String getMail() {
        return mail;
    }

    public String getAdresse() {
        return adresse;
    }

    public Integer getCodePostal() {
        return codePostal;
    }

    public String getVille() {
        return ville;
    }

    public LatLng getCoordGPS() {
        return coordGPS;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setNumTelephone(String numTelephone) {
        this.numTelephone = numTelephone;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }


    public void setCodePostal(Integer codePostal) {
        this.codePostal = codePostal;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setCoordGPS(LatLng coordGPS) {
        this.coordGPS = coordGPS;
    }
    
    
    
    
    
}
