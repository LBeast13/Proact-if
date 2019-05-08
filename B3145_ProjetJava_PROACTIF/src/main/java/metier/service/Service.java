/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.service;

import com.google.maps.model.LatLng;
import dao.ClientDAO;
import dao.EmployeDAO;
import dao.InterventionDAO;
import dao.JpaUtil;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import metier.modele.Client;
import metier.modele.Employe;
import metier.modele.Intervention;
import metier.modele.STATUT_INTERVENTION;
import util.DebugLogger;
import util.Message;

/**
 *
 * @author alexa
 */
public class Service {
    static String mailDepart = "contact@proact.if";
    
    public static void initializeSys() {
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();

        double[] t1 = {1 , 23};
        double[] t2 = {6.5 , 11.75};
        double[] t3 = {12 , 14.25};
        double[] t4 = {16 , 17};
        double[] t5 = {7.5 , 11.75};
        double[] t6 = {18.5 , 23};
        double[] t7 = {10.5 , 12};
        double[] t8 = {14.5 , 18};
        double[] t9 = {19.5 , 21};
        
        List <double[]> l1  = Arrays.asList(t1,t3,t4);
        List <double[]> l2  = Arrays.asList(t2,t8,t9);
        List <double[]> l3  = Arrays.asList(t5,t6);
        List <double[]> l4  = Arrays.asList(t2,t4,t9);
        List <double[]> l5  = Arrays.asList(t6);
        
        GregorianCalendar d1 = new GregorianCalendar(1999, 5, 13, 9, 10, 00);
        GregorianCalendar d2 = new GregorianCalendar(1990, 2, 31, 18, 20, 50);
        GregorianCalendar d3 = new GregorianCalendar(2000, 12, 9, 6, 30, 30);
        GregorianCalendar d4 = new GregorianCalendar(1995, 11, 25, 4, 40, 20);
        GregorianCalendar d5 = new GregorianCalendar(1989, 8, 1, 19, 50, 10);
        
        LatLng coords4 = new LatLng();
        
        Employe e1 = new Employe(l1, "mdp1", "Navarro", "Alexane", "Mme", d1.getTime(), "0659847512", "alexane.navarro@yahoo.fr", "22 av Albert Einstein", 69100, "Villeurbanne", coords4);
	Employe e2 = new Employe(l2, "mdp2", "Beugnon", "Bastien", "M", d2.getTime(), "0659847512", "bastien.beugnon@yahoo.fr", "114 bd du 11 novembre 1918", 69100, "Villeurbanne", coords4);
 	Employe e3 = new Employe(l3,"mdp3",  "Floriot", "Carole", "Mme", d3.getTime(), "0654978652", "carole.floriot@yahoo.fr", "9 Rue de Longchamp", 69100, "Villeurbanne", coords4);
	Employe e4 = new Employe(l4,"mdp4",  "Etienne", "Damien", "M", d4.getTime(), "0749978652", "damien.etienne@yahoo.fr", "192 avenue thiers", 69006, "Lyon", coords4);
	Employe e5 = new Employe(l5,"mdp5",  "Scotto", "Elodie", "Mme", d5.getTime(), "0745869517", "elodie.scotto@yahoo.fr", "20 Avenue Maréchal de Saxe", 69006, "Lyon", coords4);
	Employe e6 = new Employe(l1,"md61", "Tondereau", "Florian", "M", d1.getTime(), "0651649652", "florian.tondereau@yahoo.fr", "99 Rue Duguesclin", 69006, "Lyon", coords4);
	Employe e7 = new Employe(l2,"mdp7",  "Dupont", "Gontran", "M", d2.getTime(), "0654461235", "gontran.dupont@yahoo.fr", "125 Boulevard de Stalingrad", 69100, "Villeurbanne", coords4);
	Employe e8 = new Employe(l3,"mdp8", "Giovanni", "Hugo", "M", d3.getTime(), "074164517", "hugo.giovanni@yahoo.fr", "64 Rue du 8 Mai 1945", 69100, "Villeurbanne", coords4);
	Employe e9 = new Employe(l4,"mdp9", "Merve", "Idriss", "M", d4.getTime(), "071749517", "idriss.merve@yahoo.fr", "27 Rue Lançon", 69100, "Villeurbanne", coords4);
	Employe e10 = new Employe(l5, "mdp10",  "Estienne", "Jordan", "M", d5.getTime(), "07236517", "jordan.estienne@yahoo.fr", "14 Rue Paul Lafargue", 69100, "Villeurbanne", coords4);
	Employe e11 = new Employe(l1,"mdp11", "Badin", "Kylie", "Mme", d1.getTime(), "074989517", "kylie.badin@yahoo.fr", "72 Cours Tolstoï", 69100, "Villeurbanne", coords4);
	Employe e12 = new Employe(l2,"mdp12",  "Compagni", "Lilian", "M", d2.getTime(), "0654879517", "lilian.compagni@yahoo.fr", "134 Route de Genas", 69003, "Lyon", coords4);
	Employe e13 = new Employe(l3,"mdp13", "Montchat", "Marie", "Mme", d3.getTime(), "0652769517", "marie.montchat@yahoo.fr", "1 Rue Félix Rollet", 69003, "Lyon", coords4);
	Employe e14 = new Employe(l4,"mdp14", "Bettoli", "Noemie", "Mme", d4.getTime(), "0716958717", "noemie.bettoli@yahoo.fr", "57 Avenue des Frères Lumière", 69008, "Lyon", coords4);
	Employe e15 = new Employe(l5,"mdp15", "Moretto", "Ophelie", "Mme", d5.getTime(), "065887517", "ophelie.moretto@yahoo.fr", "228 Avenue Berthelot", 69008, "Lyon", coords4);
        
        ServiceUtils.creerEmploye(e1);
        ServiceUtils.creerEmploye(e2);
        ServiceUtils.creerEmploye(e3);
        ServiceUtils.creerEmploye(e4);
        ServiceUtils.creerEmploye(e5);
        ServiceUtils.creerEmploye(e6);
        ServiceUtils.creerEmploye(e7);
        ServiceUtils.creerEmploye(e8);
        ServiceUtils.creerEmploye(e9);
        ServiceUtils.creerEmploye(e10);
        ServiceUtils.creerEmploye(e11);
        ServiceUtils.creerEmploye(e12);
        ServiceUtils.creerEmploye(e13);
        ServiceUtils.creerEmploye(e14);
        ServiceUtils.creerEmploye(e15);

        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
 
    }
    
    
    public static Client creerClient(Client unClient) {
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        // verifier si il n'existe pas deja un client avec le meme pseudo
        boolean dejaPresent = ClientDAO.clientExiste(unClient);
        if(!dejaPresent)
        {
            LatLng coord = ServiceUtils.recupererCoordGPS(unClient.getAdresse(), unClient.getCodePostal(), unClient.getVille());
            unClient.setCoordGPS(coord);
            //DebugLogger.log(unClient.getCoordGPS().toString());
            ClientDAO.createClient(unClient);
           Message.envoyerMail(mailDepart, unClient.getMail(),"Bienvenue chez PROACTIF",Notifications_texte.inscriptionReussie(unClient));
        }
        else
        {
            Message.envoyerMail(mailDepart, unClient.getMail(),"Echec de votre inscription a PROACTIF",Notifications_texte.inscriptionImpossible(unClient));
        }
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        return unClient;
    }
    
    public static Client modifierInfosClients(Client unClient){
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        if(ClientDAO.trouverClient(unClient)!=null)
        {
            ClientDAO.modifierClient(unClient);
            //DebugLogger.log("Les informations ont bien été modifiées");
        }
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        return unClient;
    }
    
    public static Client connecterClient(String mail,String mdp){
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        Client res = ClientDAO.connecterClient(mail,mdp);
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        return res;
    }
    
     public static Employe connecterEmploye(String mail,String mdp){
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        Employe res = EmployeDAO.connecterEmploye(mail,mdp);
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        return res;
    }
     
     
    public static Intervention demanderIntervention(Client demandeur, Intervention inter){
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        //verifier que le demandeur existe bien dans la base.
        if(ClientDAO.trouverClient(demandeur)!=null)
        {
            demandeur = ClientDAO.trouverClient(demandeur);
            inter.setClient_associe(demandeur);
            inter.setCoordGPS(demandeur.getCoordGPS());
            demandeur.setMesInterventions(inter);
            InterventionDAO.creerIntervention(inter);
            Employe choisi = ServiceUtils.getEmployeDisponible(inter);
            if(choisi != null){
                ServiceUtils.assignerEmployeIntervention(choisi,inter);
                Message.envoyerNotification(choisi.getNumTelephone(), Notifications_texte.debutInterventionEmploye(inter));
                Message.envoyerNotification(inter.getClient_associe().getNumTelephone(),Notifications_texte.debutInterventionClient(inter));         
            }
            else{
                Message.envoyerNotification(inter.getClient_associe().getNumTelephone(),Notifications_texte.interventionImpossible());
            }
        }
        else
        {
            DebugLogger.log("erreur d'authentification pour la demande d'intervention");
            inter = null;
        }
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager(); 
        return inter;
    }
    
    public static Intervention getIntervention(Integer numIntervention) {
        JpaUtil.creerEntityManager();
        Intervention inter = InterventionDAO.findById(numIntervention);
        JpaUtil.fermerEntityManager(); 
        return inter;
    }
    
    public static List<Intervention> recupererCarteJour(Date date) {
        JpaUtil.creerEntityManager();
        List<Intervention> i = InterventionDAO.findInterventionByCalendar(date);
        JpaUtil.fermerEntityManager();
        return i;
    }
    
    public static List<Intervention> recupererHistoriqueClient(Client unClient) {
        List < Intervention > histInterventions = null;
        JpaUtil.creerEntityManager();
        if(ClientDAO.trouverClient(unClient)!=null)
        {
            unClient = ClientDAO.trouverClient(unClient);
            histInterventions = unClient.getMesInterventions();
        }
        JpaUtil.fermerEntityManager();
        return histInterventions;
    }
    
    public static List<Intervention> recupererHistoriqueEmploye(Employe unEmploye) {
        List < Intervention > histInterventions = null;
        JpaUtil.creerEntityManager();
        if(EmployeDAO.employeExiste(unEmploye))
        {
            histInterventions = unEmploye.getMesInterventions();
        }
        JpaUtil.fermerEntityManager();
        return unEmploye.getMesInterventions();
    }
    public static boolean cloturerIntervention(Intervention inter, GregorianCalendar dateFin, String commentaireFin, STATUT_INTERVENTION statut)
    {
        boolean res = true;
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        if(InterventionDAO.interventionExiste(inter) && inter.isAttribue())
        {
            inter = InterventionDAO.findById(inter.getNumIntervention());
            inter.setDateFin(dateFin.getTime());
            inter.setStatut(statut);
            inter.setEstTerminee(true);
            inter.setCommentaireFin(commentaireFin);
            inter.getEmploye_associe().setDisponible(true);
            Message.envoyerNotification(inter.getClient_associe().getNumTelephone(), Notifications_texte.clotureInterventionClient(inter));
            Message.envoyerNotification(inter.getEmploye_associe().getNumTelephone(), Notifications_texte.clotureInterventionEmploye(inter));
        }
        else
        {
            res = false;
        }
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        return res;
    }
}
