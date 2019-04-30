/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.TypedQuery;
import metier.modele.Employe;

/**
 *
 * @author ascotto
 */
public class EmployeDAO {
    public static void createEmploye(Employe unEmploye) {
      JpaUtil.obtenirEntityManager().persist(unEmploye);
    }
    public static Employe connecterEmploye(String mail, String mdp)
    {
        String jpql = "select c from Employe c where c.mail = :mail and c.mdp = :mdp";
        TypedQuery<Employe> query = JpaUtil.obtenirEntityManager().createQuery(jpql, Employe.class);
        query.setParameter("mail", mail);
        query.setParameter("mdp",mdp);
        List<Employe> res = query.getResultList();
        
        if (res.isEmpty()){
            return null;
        }
        else
        {
            return res.get(0);
        }
    }
    public static List <Employe> getTousEmploye() {
        String jpql = "select e from Employe e";
        TypedQuery<Employe> query = JpaUtil.obtenirEntityManager().createQuery(jpql, Employe.class);
        List<Employe> res = query.getResultList();
        
        return res;
    }
    
    public static boolean employeExiste(Employe nouveau)
    {
        String jpql = "select c from Employe c where c.mail = :mail";
        TypedQuery<Employe> query = JpaUtil.obtenirEntityManager().createQuery(jpql, Employe.class);
        query.setParameter("mail", nouveau.getMail());
        List<Employe> res = query.getResultList();
        
        return !res.isEmpty();
    }
    
    public static Employe findById(int id)
    {
        return JpaUtil.obtenirEntityManager().find(Employe.class, id);
    }
    public static List<Employe> findAll()
    {
        return getTousEmploye();
    }
}