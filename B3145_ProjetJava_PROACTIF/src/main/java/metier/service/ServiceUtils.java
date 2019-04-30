/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.service;

import com.google.maps.model.LatLng;
import dao.EmployeDAO;
import java.util.Date;
import java.util.List;
import metier.modele.Employe;
import metier.modele.Intervention;
import util.GeoTest;

/**
 *
 * @author atondereau
 */
public class ServiceUtils {
    
    protected static LatLng recupererCoordGPS(String adresse, Integer codePostal, String ville) {
        String adresseComplete = adresse + codePostal + ville;
        return GeoTest.getLatLng(adresseComplete);
    }
    protected static Employe getEmployeDisponible(Intervention proposition)
    {
        List< Employe > tousEmploye = EmployeDAO.getTousEmploye();
        double minDist = -1;
        Employe choisi = null;
        for(Employe emp:tousEmploye){
            //l'employe n'est pas sur une intervention, et est en service
            if(emp.getDisponible() && estEnService(emp,proposition.getDateDebut()))
            {
                //l'employe est le plus proche
                double dist = GeoTest.getTripDurationByBicycleInMinute(emp.getCoordGPS(),proposition.getCoordGPS());
                if(minDist == -1 || dist < minDist)
                {
                    choisi = emp;
                    minDist = dist;
                }
            }
        }
        return choisi;
    }
    private static boolean estEnService(Employe emp, Date date){
        for(double[] horaire:emp.getHoraires())
        {
            if(dateApresHeure(horaire[0],date) && !dateApresHeure(horaire[1],date))
            {
                return true;
            }
                
        }
        return false;
    }
    private static boolean dateApresHeure(double heure,Date date)
    {
        return date.getHours()>(int)(heure) 
                ||(date.getHours()==(int)(heure) && date.getMinutes()>=(int)((heure-(int)(heure))*60));
    }
    
    protected static void assignerEmployeIntervention(Employe emp, Intervention inter){
        inter.setAttribue(true);
        inter.setEmploye_associe(emp);       
        emp.addToMesInterventions(inter);
        emp.setDisponible(false);
        inter.setDistance(GeoTest.getTripDistanceByBycycleInKm(emp.getCoordGPS(),inter.getCoordGPS()));
    }
    
    public static void creerEmploye(Employe unEmploye){
        LatLng coord = ServiceUtils.recupererCoordGPS(unEmploye.getAdresse(), unEmploye.getCodePostal(), unEmploye.getVille());
        unEmploye.setCoordGPS(coord);
        EmployeDAO.createEmploye(unEmploye);
    }
}
