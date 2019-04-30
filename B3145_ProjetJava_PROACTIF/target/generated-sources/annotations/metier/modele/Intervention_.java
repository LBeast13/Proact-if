package metier.modele;

import com.google.maps.model.LatLng;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import metier.modele.Client;
import metier.modele.Employe;
import metier.modele.STATUT_INTERVENTION;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-04-30T11:27:07")
@StaticMetamodel(Intervention.class)
public abstract class Intervention_ { 

    public static volatile SingularAttribute<Intervention, String> commentaireFin;
    public static volatile SingularAttribute<Intervention, LatLng> coordGPS;
    public static volatile SingularAttribute<Intervention, Boolean> attribue;
    public static volatile SingularAttribute<Intervention, Double> distance;
    public static volatile SingularAttribute<Intervention, Date> dateDebut;
    public static volatile SingularAttribute<Intervention, Integer> numIntervention;
    public static volatile SingularAttribute<Intervention, Client> client_associe;
    public static volatile SingularAttribute<Intervention, String> description;
    public static volatile SingularAttribute<Intervention, Boolean> estTerminee;
    public static volatile SingularAttribute<Intervention, Date> dateFin;
    public static volatile SingularAttribute<Intervention, STATUT_INTERVENTION> statut;
    public static volatile SingularAttribute<Intervention, Employe> employe_associe;

}