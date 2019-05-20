package metier.modele;

import com.google.maps.model.LatLng;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-20T11:57:59")
@StaticMetamodel(Personne.class)
public abstract class Personne_ { 

    public static volatile SingularAttribute<Personne, Date> date;
    public static volatile SingularAttribute<Personne, String> ville;
    public static volatile SingularAttribute<Personne, LatLng> coordGPS;
    public static volatile SingularAttribute<Personne, String> mail;
    public static volatile SingularAttribute<Personne, Integer> idPersonne;
    public static volatile SingularAttribute<Personne, String> mdp;
    public static volatile SingularAttribute<Personne, String> adresse;
    public static volatile SingularAttribute<Personne, Integer> codePostal;
    public static volatile SingularAttribute<Personne, String> nom;
    public static volatile SingularAttribute<Personne, String> prenom;
    public static volatile SingularAttribute<Personne, String> numTelephone;
    public static volatile SingularAttribute<Personne, String> civilite;

}