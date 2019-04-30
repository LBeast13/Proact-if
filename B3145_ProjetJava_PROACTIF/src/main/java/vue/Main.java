package vue;

import com.google.maps.model.LatLng;
import dao.JpaUtil;
import java.text.ParseException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

import metier.modele.Client;
import metier.modele.Employe;
import metier.modele.Intervention;
import metier.modele.Intervention_Animal;
import metier.modele.Intervention_Incident;
import metier.modele.Intervention_Livraison;
import metier.modele.Personne;
import metier.modele.STATUT_INTERVENTION;
import metier.service.Service;
import util.DebugLogger;

/**
 * Classe principale pour les tests.
 *
 * @author DASI Team
 */
public class Main{
    
    /**
     * Afficher une personne sur la Console.
     * @param p Entité Personne à afficher
     */
    public static void afficherPersonne(Personne p) {

        System.out.println("#" + p.getNom() + " " + p.getPrenom()+ " " + p.getIdPersonne());
    }

    public static void afficherIntervention(Intervention i) {

        System.out.println("Intervention numero " + i.getNumIntervention());
        System.out.println("Description : " + i.getDescription());
    }
    
   
    public static Client creerClient(Client unClient) {
        return Service.creerClient(unClient);
    }
    
    

    public static void recupererHistoriqueClient(Client unClient) {
    
        List <Intervention> l = Service.recupererHistoriqueClient(unClient);
      
        if (!l.isEmpty()) {
            System.out.println("Historique des interventions");
            for (Intervention i : l) {
                afficherIntervention(i);
            }
        } else {
            System.out.println("Pas d'intervention ! ");
        }
    }
    
    public static void recupererHistoriqueEmploye(Employe unEmploye) {
        
        List <Intervention> l = Service.recupererHistoriqueEmploye(unEmploye);
      
        if (!l.isEmpty()) {
            System.out.println("Historique des interventions : ");            
            for (Intervention i : l) {
                afficherIntervention(i);
            }
        } else {
            System.out.println("Pas d'intervention");
        }
    }

    
    public static void recupererCarteJour(Date date) {
        List <Intervention> l = Service.recupererCarteJour(date);
 
        if (!l.isEmpty()) {
            for (Intervention i : l) {
                afficherIntervention(i);
            }
        } else {
            System.out.println("Pas d'intervention");
        }
    }
    

    public static void getIntervention(Integer numIntervention) {
        
        if (Service.getIntervention(numIntervention) != null) {
            afficherIntervention(Service.getIntervention(numIntervention));
        } else {
            System.out.println("Intervention introuvable... (#" + numIntervention + ")");
        }
    }

    public static void connecterClient(String username, String password) {
        Client c = Service.connecterClient(username, password);
        if (c!=null) {
            System.out.println("Connection reussie, numero client = "+c.getIdPersonne());
        } else {
            System.out.println("Erreur dans la connexion");  
        }      
    }
    
    public static void connecterEmploye(String username, String password) {

        Employe e = Service.connecterEmploye(username,password);
        if (e!=null) {
            System.out.printf("Connecte !");
        } else {
            System.out.printf("Erreur dans la connexion"); 
        }
    }
    
    
    public static void modifierInfosClients(Client clientModif) {
        Service.modifierInfosClients(clientModif);
        
    }
    
    public static void demanderIntervention(Client demandeur, Intervention inter){
        Intervention i = Service.demanderIntervention(demandeur, inter);
    }
    
    public static void cloturerIntervention(Intervention inter, GregorianCalendar dateFin, String commentaireFin, STATUT_INTERVENTION statut) {
        boolean ok = Service.cloturerIntervention(inter,dateFin,commentaireFin, statut);
        if (!ok) {
            System.out.println("L'intervention ne s'est pas cloturee correctement");
        } else {
            System.out.println("L'intervention est cloturee");
        }
    }
        
/***********************************************TEST DEMO*****************************************/
    public static void inscrireClientNormal(){
        GregorianCalendar d1 = new GregorianCalendar(2018, 12, 31, 9, 10, 00);
        GregorianCalendar d2 = new GregorianCalendar(1999, 5, 13, 21, 00, 00);
        LatLng coords = new LatLng();
        System.out.println("Ajout d'un nouveau client, dont les informations sont valides");
        Client c1 = new Client(d1.getTime(),"mdp1","Scotto", "Alexane", "Mme", d2.getTime(), "0767417097", "alexane.scotto@insa-lyon.fr", "128 bd du 11 novembre 1918", 69100, "Villeurbanne", coords);
        creerClient(c1);
    }
    

    public static void inscrireClientMailDouble(){
        GregorianCalendar d1 = new GregorianCalendar(2019,1, 31, 12, 50, 00);
        GregorianCalendar d2 = new GregorianCalendar(1998, 8, 25, 23, 00, 00);
        LatLng coords = new LatLng();
        
        
        Client c1 = new Client(d1.getTime(), "mdp1","Tondereau", "Arthur", "M", d2.getTime(), "0695817097", "arthur.tondereau@insa-lyon.fr", "55 Avenue Galline", 69100, "Villeurbanne", coords);
        Client c2 = new Client(d1.getTime(),"mdp1","Dupont", "Gontran", "M", d2.getTime(), "0695817097", "arthur.tondereau@insa-lyon.fr", "70 Avenue Galline", 69100, "Villeurbanne", coords);

        System.out.println("Ajout d'un nouveau client, avec le mail arthur.tondereau@insa-lyon.fr");
        creerClient(c1);
        
        System.out.println("Ajout d'un autre client, avec le mail arthur.tondereau@insa-lyon.fr");
        creerClient(c2);
    }  
    
    public static void connecterClientNormal() {
        GregorianCalendar d1 = new GregorianCalendar(2019, 3, 18, 14, 50, 00);
        GregorianCalendar d2 = new GregorianCalendar(1996, 8, 11, 22, 30, 00);
        LatLng coords = new LatLng();
        System.out.println("Ajout du client pour le test : ");
        Client c1 = new Client(d1.getTime(),"mdp1","Terreu", "Jorje", "M", d2.getTime(), "0749657097", "jorje.terreu@insa-lyon.fr", "105 Rue Alexis Perroncel", 69100, "Villeurbanne", coords);
        creerClient(c1);
        System.out.println("Tentative de connection : jorje.terreu@insa-lyon.fr,mdp1");        
        connecterClient("jorje.terreu@insa-lyon.fr", "mdp1");  
    }
        
        
    public static void connecterClientMdpFaux() {
        GregorianCalendar d1 = new GregorianCalendar(2019, 2, 1, 11, 25, 00);
        GregorianCalendar d2 = new GregorianCalendar(1995, 1, 8, 12, 50, 00);
        LatLng coords = new LatLng();
        System.out.println("creation d'un client : ");
        Client c1 = new Client(d1.getTime(),"mdp1","Donnangelo", "Franco", "M", d2.getTime(), "0631477097", "franco.donnangelo@insa-lyon.fr", "10 Rue du Pérou", 69100, "Villeurbanne", coords);
        creerClient(c1);   
        System.out.println("Tentative de connection : franco.donnangelo@insa-lyon.fr,faux");
        connecterClient("franco.donnangelo@insa-lyon.fr", "faux");  
    }
        
    public static void connecterClientInconnu() {
        connecterClient("inconnu", "mdp");    
    }
        
    public static void connecterEmployeNormal() {
        connecterEmploye("bastien.beugnon@yahoo.fr", "mdp2");
    }


    public static void connecterEmployeMdpFaux() {
        connecterEmploye("bastien.beugnon@yahoo.fr", "faux");
    }

    public static void connecterEmployeInconnu() {
        connecterEmploye("inconnu","mdp");
    }
    
    public static void modifierInfosClientsDemo() {
        
        GregorianCalendar d1 = new GregorianCalendar(2019, 1, 2, 18, 40, 20);
        GregorianCalendar d2 = new GregorianCalendar(1992, 2, 15, 5, 20, 50);
        LatLng coords = new LatLng();
        System.out.println("Ajout d'un nouveau client : ");
        Client c1 = new Client(d1.getTime(),"mdp1","Aras", "Paco", "M", d2.getTime(), "06871177097", "paco.aras@insa-lyon.fr", "19 Avenue Gaston Berger", 69100, "Villeurbanne", coords);
        creerClient(c1);
        arret();
        c1.setNom("nouveauNom");
        System.out.println("modification de son nom dans la base : ");
        modifierInfosClients(c1);
    }
            
            
    public static void demanderInterventionDemo(){
        GregorianCalendar d1 = new GregorianCalendar(2018, 11, 25, 4, 40, 20);
        GregorianCalendar d2 = new GregorianCalendar(1990, 2, 31, 18, 20, 50);
        LatLng coords = new LatLng();
        
        System.out.println("Ajout d'un nouveau client : ");
        Client c1 = new Client(d1.getTime(),"mdp1","Mas", "Marta", "Mme", d2.getTime(), "0698777097", "marta.mas@insa-lyon.fr", "46 Cours de la République", 69100, "Villeurbanne", coords);
        creerClient(c1);
        
        System.out.println("Demande d'une intervention, animal : ");
        GregorianCalendar d3 = new GregorianCalendar(2018, 03, 26, 22, 00, 00);
        Intervention_Animal i1 = new Intervention_Animal("Chien", "Promener Chien", d3.getTime());
        demanderIntervention(c1, i1);
        
        System.out.println("Demande d'une intervention, incident : ");
        GregorianCalendar d4 = new GregorianCalendar(2018, 03, 26, 22, 10, 00);
        Intervention_Incident i2 = new Intervention_Incident("Fuite", d4.getTime());
        demanderIntervention(c1, i2);
        System.out.println("Demande d'une intervention, livraison : ");
        GregorianCalendar d5 = new GregorianCalendar(2018, 03, 25, 22, 00, 00);
        Intervention_Livraison i3 = new Intervention_Livraison("Cactus", "PlanteVerte","Livrer", d5.getTime());
        demanderIntervention(c1, i3);
    }
    
    public static void getInterventionDemo() {
        GregorianCalendar d1 = new GregorianCalendar(2019, 1, 23, 4, 40, 20);
        GregorianCalendar d2 = new GregorianCalendar(1992, 2, 25, 18, 20, 50);
        LatLng coords = new LatLng();
        System.out.println("Ajout du client qui demandera l'intervention : ");
        Client c1 = new Client(d1.getTime(),"mdp1","Perrin", "Louise", "Mme", d2.getTime(), "0631277097", "louise.perrin@insa-lyon.fr", "67 Boulevard Marius Vivier Merle", 69003, "Lyon", coords);
        creerClient(c1);
        
        GregorianCalendar d3 = new GregorianCalendar(2018, 03, 24, 12, 00, 00);
        GregorianCalendar d4 = new GregorianCalendar(2018, 03, 24, 14, 00, 00);
        System.out.println("Ajout de l'intervention");
        Intervention_Animal i1 = new Intervention_Animal("Lapin", "Nettoyer lapin", d3.getTime());
        demanderIntervention(c1, i1); 
        System.out.println("Recupérer l'intervention gràce à son numero");
        getIntervention(i1.getNumIntervention());
    }
    
    public static void cloturerInterventionDemo() {
        GregorianCalendar d1 = new GregorianCalendar(2018, 12, 25, 20, 40, 20);
        GregorianCalendar d2 = new GregorianCalendar(1996, 2, 31, 18, 20, 50);
        LatLng coords = new LatLng();
        System.out.println("Creation du client qui demandera l'intervention");
        Client c1 = new Client(d1.getTime(),"mdp1","Martin", "Elena", "Mme", d2.getTime(), "0788777097", "elena.martin@insa-lyon.fr", "47 Avenue du Maréchal Foch", 69006, "Lyon", coords);
        c1 = creerClient(c1);
        
        System.out.println("Demande de l'intervention");
        GregorianCalendar d3 = new GregorianCalendar(2018, 03, 26, 22, 00, 00);
        Intervention_Livraison i1 = new Intervention_Livraison("Colis", "Amazon", "Livrer", d3.getTime());
        demanderIntervention(c1, i1);
        DebugLogger.log("demande ok");
        
        arret();
        System.out.println("Puis cloture");
        cloturerIntervention(i1, new GregorianCalendar(2018, 03, 26, 23, 00, 00), "RAS", STATUT_INTERVENTION.SUCCES);
        
    }
    
    public static void recupererCarteJourDemo() {
        
        GregorianCalendar d1 = new GregorianCalendar(2018, 11, 25, 4, 40, 20);
        GregorianCalendar d2 = new GregorianCalendar(1990, 2, 31, 18, 20, 50);
        LatLng coords = new LatLng();
        
        System.out.println("creation d'un client qui demandera une intervention");
        Client c1 = new Client(d1.getTime(),"mdp1","Bonnet", "Julie", "Mme", d2.getTime(), "0654972135", "julie.bonnet@insa-lyon.fr", "10 Rue Marignan", 69003, "Lyon", coords);
        creerClient(c1);
        GregorianCalendar d3 = new GregorianCalendar(2018, 03, 26, 22, 00, 00);
        Intervention_Animal i1 = new Intervention_Animal("Chat", "Promener chat", d3.getTime());
        demanderIntervention(c1, i1);
        
        System.out.println("deuxieme intervention");
        GregorianCalendar d4 = new GregorianCalendar(2018, 12, 25, 4, 40, 20);
        GregorianCalendar d5 = new GregorianCalendar(1999, 2, 31, 18, 20, 50);
        Client c2 = new Client(d4.getTime(),"mdp1","Piperno", "Chloe", "Mme", d5.getTime(), "0685972135", "chloe.piperno@insa-lyon.fr", "14 Rue Frédéric Mistral", 69003, "Lyon", coords);
        creerClient(c2);
        GregorianCalendar d6 = new GregorianCalendar(2018, 03, 26, 10, 10, 00);
        Intervention_Incident i2 = new Intervention_Incident("Fuite de gaz", d6.getTime());
        demanderIntervention(c2, i2);
        
        
        System.out.println("troisième intervention, pas le même jour.");
        GregorianCalendar d7 = new GregorianCalendar(2018, 12, 25, 4, 40, 20);
        GregorianCalendar d8 = new GregorianCalendar(1998, 2, 31, 18, 20, 50);
        Client c3 = new Client(d7.getTime(),"mdp1","Porzer", "Isabelle", "Mme", d8.getTime(), "0741972135", "isabelle.porzer@insa-lyon.fr", "100 Rue du 1er Mars 1943", 69100, "Villeurbanne", coords);
        creerClient(c3);
        GregorianCalendar d9 = new GregorianCalendar(2018, 03, 24, 12, 00, 00);        
        Intervention_Livraison i3 = new Intervention_Livraison("Cadeau Noel", "Lutin.com", "Receptionner",d9.getTime());
        demanderIntervention(c3, i3);
        
        System.out.println("Les interventions du jour sont les suivantes : ");
        recupererCarteJour(d3.getTime());
    }
    
    public static void recupererHistoriqueClientDemo() {
        GregorianCalendar d1 = new GregorianCalendar(2019, 1, 5, 22, 40, 20);
        GregorianCalendar d2 = new GregorianCalendar(1995, 2, 31, 18, 20, 50);
        LatLng coords = new LatLng();
        Client c1 = new Client(d1.getTime(),"mdp1","Bremont", "Celine", "Mme", d2.getTime(), "0666972135", "celine.bremont@insa-lyon.fr", "54 Rue Jean Jaurès", 69100, "Villeurbanne", coords);
        creerClient(c1);
        
        System.out.println("Creation de deux interventions avec ce premier client: ");
        arret();
        GregorianCalendar d3 = new GregorianCalendar(2018, 03, 26, 22, 00, 00);
        Intervention_Animal i1 = new Intervention_Animal("Chat", "Promener hamster", d3.getTime());
        demanderIntervention(c1, i1);
        
        GregorianCalendar d4 = new GregorianCalendar(2018, 02, 20, 15, 10, 00);
        Intervention_Incident i2 = new Intervention_Incident("Reparer la porte",d4.getTime());
        demanderIntervention(c1, i2);
        
        System.out.println("Creation d'une intervention avec un autre client: ");
        arret();
        
        Client c2 = new Client(d4.getTime(),"mdp1","Brunie", "Lionel", "Mr", d2.getTime(), "0685972135", "lionel.brunie@insa-lyon.fr", "14 Rue Frédéric Mistral", 69003, "Lyon", coords);
        creerClient(c2);
        
        GregorianCalendar d5 = new GregorianCalendar(2019, 03, 20, 17, 00, 00);        
        Intervention_Livraison i3 = new Intervention_Livraison("Souris d'ordinateur verticale", "Geek.com", "Receptionner", d5.getTime());
        demanderIntervention(c2, i3);
        
        recupererHistoriqueClient(c1);
    }
    
    public static void recupererHistoriqueEmployeDemo() {
        
        GregorianCalendar d1 = new GregorianCalendar(2018, 5, 5, 22, 40, 20);
        GregorianCalendar d2 = new GregorianCalendar(1997, 5, 26, 18, 20, 50);
        LatLng coords = new LatLng();
        Client c1 = new Client(d1.getTime(),"mdp1","Scotto", "Valentin", "M", d2.getTime(), "0633972135", "valentin.scotto@insa-lyon.fr", "22 av Albert Einstein", 69100, "Villeurbanne", coords);
        creerClient(c1);
        
        GregorianCalendar d3 = new GregorianCalendar(2019, 03, 26, 9 , 00, 00);
        Intervention_Animal i1 = new Intervention_Animal("Oiseau", "Ouvrez la cage aux oiseaux", d3.getTime());
        demanderIntervention(c1, i1);
        cloturerIntervention(i1,d3,"cage ouverte",STATUT_INTERVENTION.SUCCES);
        GregorianCalendar d4 = new GregorianCalendar(2018, 7, 25, 22, 40, 20);
        GregorianCalendar d5 = new GregorianCalendar(1999, 6, 13, 4, 20, 50);
        Client c2 = new Client(d4.getTime(),"mdp1","Ragon", "Lise", "Mme", d5.getTime(), "0733972135", "lise.ragon@insa-lyon.fr", "22 av Albert Einstein", 69100, "Villeurbanne", coords);
        creerClient(c2);
        
        GregorianCalendar d6 = new GregorianCalendar(2019, 02, 20, 13, 10, 00);
        Intervention_Incident i2 = new Intervention_Incident("Reparer la sonnette", d3.getTime());
        demanderIntervention(c2, i2);
        
        recupererHistoriqueEmploye(Service.connecterEmploye("alexane.navarro@yahoo.fr","mdp1"));

    }
    
    public static void filServicesDemo()
    {
        System.out.println("creation d'un client : ");
        arret();
        GregorianCalendar d = new GregorianCalendar(1997, 5, 26, 10,15, 0);
        LatLng coords = new LatLng();
        Client c1 = new Client(d.getTime(),"mdp1","Dupont", "Pierre", "M", d.getTime(), "0633972135", "pierre.dupont@insa-lyon.fr", "10 av Albert Einstein", 69100, "Villeurbanne", coords);
        creerClient(c1);
        System.out.println("connection de ce client : ");
        arret();
        connecterClient("pierre.dupont@insa-lyon.fr","mdp1");
        System.out.println("Demande d'une intervention : ");
        arret();
        Intervention_Incident i1 = new Intervention_Incident("Fenetre bloquee", d.getTime());
        demanderIntervention(c1,i1);
        System.out.println("Cloture de l'intervention");
        arret();
        GregorianCalendar dFin = new GregorianCalendar(1997, 5, 26, 19, 20, 50);
        cloturerIntervention(i1,dFin,"Reparation de la fenetre terminee",STATUT_INTERVENTION.SUCCES);
    }
/************************************************MENU*********************************************/
    public static void menu() {
        
        Scanner sc = new Scanner(System.in);
        
        int entree = 1;
        
        while (entree != 0) {
            
            System.out.println();
            System.out.println("Veuillez entrer un choix parmi la liste ci-dessous");
            System.out.println("0. Retour");
            System.out.println("1. S'inscrire en tant que client (cas normal)");
            System.out.println("2. S'inscrire en tant que client (avec un mail deja utilise)");
            System.out.println("3. Se connecter en tant que client (cas normal)");
            System.out.println("4. Se connecter en tant que client (avec un mdp errone)");
            System.out.println("5. Se connecter en tant que client (avec un mail non present dans la base)");
            System.out.println("6. Se connecter en tant qu'employe (cas normal)");
            System.out.println("7. Se connecter en tant quemploye (avec un mdp errone)");
            System.out.println("8. Se connecter en tant qu'employe (avec un mail non present dans la base)");            
            System.out.println("9. Modifier ses informations personnelles");
            System.out.println("10. Faire une demande d'intervention");
            System.out.println("11. Obtenir les details d'une intervention (en tant que client ou employe)");
            System.out.println("12. Cloturer une intervention");
            System.out.println("13. Voir la carte topographique des interventions du jour");
            System.out.println("14. Voir son historique (en tant que client)");
            System.out.println("15. Voir son historique (en tant qu'employe)");
            System.out.println("16. Voir le déroulement classique d'une intervention");

            entree = sc.nextInt();

            switch(entree) {
                case 1 :
                    inscrireClientNormal();
                    break;  
                case 2 : 
                    inscrireClientMailDouble();
                    break;
                case 3 : 
                    connecterClientNormal();
                    break;
                case 4 : 
                   connecterClientMdpFaux();
                    break;
                case 5 : 
                    connecterClientInconnu();
                    break;
                case 6 : 
                    connecterEmployeNormal();
                    break;
                case 7 : 
                    connecterEmployeMdpFaux();
                    break;
                case 8 : 
                    connecterEmployeInconnu();
                    break;
                case 9 :
                    modifierInfosClientsDemo();
                    break;
                case 10 :
                    demanderInterventionDemo();
                    break;
                case 11 : 
                    getInterventionDemo();
                    break;
                case 12 : 
                    cloturerInterventionDemo();
                    break;
                case 13 :
                    recupererCarteJourDemo();
                    break;
                case 14 : 
                    recupererHistoriqueClientDemo();
                    break;
                case 15 : 
                    recupererHistoriqueEmployeDemo();
                    break;
                case 16:
                    filServicesDemo();
                    break;
            }
            arret();

        }
    }

    public static void arret()
    {
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
    }
    /**
     * Méthode main(): point d'entrée de ce programme de test.
     * @param args
     */
    public static void main(String[] args) throws ParseException {

        // Initialisation du JpaUtil
        JpaUtil.init();
        
        Service.initializeSys();
        //recupererHistoriqueEmployeDemo();
        menu();
        
        // Libération du JpaUtil
        JpaUtil.destroy();
    }

}
