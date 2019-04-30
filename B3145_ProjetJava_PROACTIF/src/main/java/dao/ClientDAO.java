/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.TypedQuery;
import metier.modele.Client;


public class ClientDAO {
    
    public static void createClient(Client unClient) {
        JpaUtil.obtenirEntityManager().persist(unClient);
    }
    public static void modifierClient(Client unClient){
        unClient = JpaUtil.obtenirEntityManager().merge(unClient);
    }
    public static Client connecterClient(String mail, String mdp)
    {
        String jpql = "select c from Client c where c.mail = :mail and c.mdp = :mdp";
        TypedQuery<Client> query = JpaUtil.obtenirEntityManager().createQuery(jpql, Client.class);
        query.setParameter("mail", mail);
        query.setParameter("mdp",mdp);
        List<Client> res = query.getResultList();
        
        if (res.isEmpty()){
            return null;
        }
        else
        {
            return res.get(0);
        }
    }
    
    public static boolean clientExiste(Client nouveau)
    {
        String jpql = "select c from Client c where c.mail = :mail";
        TypedQuery<Client> query = JpaUtil.obtenirEntityManager().createQuery(jpql, Client.class);
        query.setParameter("mail", nouveau.getMail());
        List<Client> res = query.getResultList();
        
        return !res.isEmpty();
    }
    
    public static Client trouverClient(Client unClient)
    {
        return JpaUtil.obtenirEntityManager().find(Client.class, unClient.getIdPersonne());
    }
    public static Client findById(int id)
    {
        return JpaUtil.obtenirEntityManager().find(Client.class, id);
    }
    public static List <Client> findAll() {
        String jpql = "select c from Client c";
        TypedQuery<Client> query = JpaUtil.obtenirEntityManager().createQuery(jpql, Client.class);
        List<Client> res = query.getResultList();
        return res;
    }
}
