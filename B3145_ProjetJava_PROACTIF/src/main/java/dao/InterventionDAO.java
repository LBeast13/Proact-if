/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import java.util.Date;
import java.util.List;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import metier.modele.Intervention;

/**
 *
 * @author ascotto
 */
public class InterventionDAO {
    
    public static void creerIntervention(Intervention uneIntervention) {
      JpaUtil.obtenirEntityManager().persist(uneIntervention);
    }
    
    public static Intervention findById(Integer numIntervention) {
        
        Intervention i = JpaUtil.obtenirEntityManager().find(Intervention.class, numIntervention);

        return i;
    }
    public static List<Intervention> findAll()
    {
        String jpql = "select c from Intervention c";
        TypedQuery<Intervention> query = JpaUtil.obtenirEntityManager().createQuery(jpql, Intervention.class);
        List<Intervention> res = query.getResultList();
        return res;
    }
    
    public static List <Intervention> findInterventionByCalendar(Date date) {
        String jpql = "select i from Intervention i where i.dateDebut >= :laDate";
        TypedQuery<Intervention> query = JpaUtil.obtenirEntityManager().createQuery(jpql, Intervention.class);
        query.setParameter("laDate", date, TemporalType.DATE);
        List<Intervention> res = query.getResultList();
        return res;
    }
    public static boolean interventionExiste(Intervention inter)
    {
        String jpql = "select i from Intervention i where i.numIntervention = :numIntervention";
        TypedQuery<Intervention> query = JpaUtil.obtenirEntityManager().createQuery(jpql, Intervention.class);
        query.setParameter("numIntervention", inter.getNumIntervention());
        List<Intervention> res = query.getResultList();
        
        return !res.isEmpty();
    }
}
