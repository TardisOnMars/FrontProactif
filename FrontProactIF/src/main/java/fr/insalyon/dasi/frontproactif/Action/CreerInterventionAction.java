/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.frontproactif.Action;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import metier.model.Animal;
import metier.model.Client;
import metier.model.Incident;
import metier.model.Intervention;
import metier.model.Livraison;
import metier.service.Service;
import util.DebugLogger;

/**
 *
 * @author yzhao
 */
public class CreerInterventionAction extends Action{
    @Override
    public boolean execute(HttpServletRequest request){
        String type = request.getParameter("type");
        DebugLogger.log(type);
        String nomAnimal = request.getParameter("nomAnimal");
        String objet = request.getParameter("objet");
        String entreprise = request.getParameter("entreprise");
        String description = request.getParameter("description");
        
        Client c = (Client)request.getAttribute("utilisateur");
        
        request.setAttribute("intervention", true);
        
        Intervention i;
        if(type.equals("Animal")){
            i = new Animal(description,new Date(),c,nomAnimal);
        }else if(type.equals("Incident")){
            i = new Incident(description,new Date(),c);
        }else if(type.equals("Livraison")){
            i = new Livraison(description,new Date(),c,objet,entreprise);
        }else{
            i = null;
            DebugLogger.log("i is null");
            request.setAttribute("intervention", false);
        }
        boolean b = Service.EnregistrerIntervention(i);
        
        if(!b){
            request.setAttribute("intervention", false);
        }
        return true;
    }
}
