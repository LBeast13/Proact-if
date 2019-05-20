package metier.modele;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import metier.modele.Intervention;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-20T11:57:59")
@StaticMetamodel(Client.class)
public class Client_ extends Personne_ {

    public static volatile SingularAttribute<Client, Date> dateInscription;
    public static volatile ListAttribute<Client, Intervention> mesInterventions;

}