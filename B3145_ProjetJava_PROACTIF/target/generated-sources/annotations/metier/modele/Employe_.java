package metier.modele;

import java.util.List;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import metier.modele.Intervention;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-04-30T11:27:07")
@StaticMetamodel(Employe.class)
public class Employe_ extends Personne_ {

    public static volatile SingularAttribute<Employe, List> horaires;
    public static volatile ListAttribute<Employe, Intervention> mesInterventions;
    public static volatile SingularAttribute<Employe, Boolean> disponible;

}