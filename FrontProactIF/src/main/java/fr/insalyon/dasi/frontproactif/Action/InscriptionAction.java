/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.frontproactif.Action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import metier.model.Civilite;
import metier.model.Client;
import metier.model.Personne;
import metier.service.Service;

/**
 *
 * @author svillenave
 */
public class InscriptionAction extends Action {

    @Override
    public boolean execute(HttpServletRequest request) {
        String nom = request.getParameter("nom");
        
        String prenom = request.getParameter("prenom");
        
        String civilite = request.getParameter("civilite");
        Civilite civ;
        if (civilite.equals("Madame")) {
            civ = Civilite.Madame;
        } else {
            civ = Civilite.Monsieur;
        }
        
        String adresse = request.getParameter("adresse");
        
        String ville = request.getParameter("ville");
        
        String telephone = request.getParameter("telephone");
        
        String naissance = request.getParameter("naissance");
        
        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        Date dateNaissance = new Date();
        try {
            dateNaissance = format.parse(naissance);
        } catch (ParseException ex) {
            Logger.getLogger(InscriptionAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String email = request.getParameter("email");
        
        String password = request.getParameter("password");
        
        Client c = new Client(civ, nom, prenom, dateNaissance, adresse, email, telephone, password);

        if (Service.InscrireClient(c)) {
            request.setAttribute("connexion", true);
        } else {
            request.setAttribute("connexion", false);
        }
        return true;
    }
}
