/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.frontproactif.Serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import metier.model.Animal;
import metier.model.Client;
import metier.model.Employe;
import metier.model.Incident;
import metier.model.Intervention;
import metier.model.Livraison;
import util.DebugLogger;

/**
 *
 * @author sosos
 */
public class InterventionCoursSerialiser extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession(true);
            response.setContentType("text/html;charset=UTF-8");
            JsonObject jsonContainer = new JsonObject();
            if (request.getAttribute("intervention") != null) {

                Intervention i = (Intervention) request.getAttribute("intervention");

                if (session.getAttribute("utilisateur") instanceof Client) {
                    jsonContainer.addProperty("intervention", true);
                    jsonContainer.addProperty("statut", i.getStatut().toString());
                    jsonContainer.addProperty("dateDebut", i.getDate_emission().toString());
                    if (i.getDate_fin() != null) {
                        jsonContainer.addProperty("dateFin", i.getDate_fin().toString());
                    }
                    jsonContainer.addProperty("description", i.getDescription());

                    if (i instanceof Animal) {
                        jsonContainer.addProperty("type", "Animal");
                        jsonContainer.addProperty("nomAnimal", ((Animal) i).getNom_animal());
                        
                    } else if (i instanceof Livraison) {
                        jsonContainer.addProperty("type", "Livraison");
                        jsonContainer.addProperty("objet", ((Livraison) i).getObjet());
                        jsonContainer.addProperty("entreprise", ((Livraison) i).getEntreprise());
                    } else if (i instanceof Incident) {
                        jsonContainer.addProperty("type", "Incident");
                    }
                } else if (session.getAttribute("utilisateur") instanceof Employe) {
                    jsonContainer.addProperty("intervention", true);
                    jsonContainer.addProperty("nomEmp", ((Employe) session.getAttribute("utilisateur")).getNom());
                    jsonContainer.addProperty("prenomEmp", ((Employe) session.getAttribute("utilisateur")).getPrenom());
                    jsonContainer.addProperty("nom", i.getClient().getNom());
                    jsonContainer.addProperty("prenom", i.getClient().getPrenom());
                    jsonContainer.addProperty("adresse", i.getClient().getAdressePostale());
                    jsonContainer.addProperty("telephone", i.getClient().getNumTel());
                    jsonContainer.addProperty("email", i.getClient().getEmail());
                    jsonContainer.addProperty("description", i.getDescription());
                    jsonContainer.addProperty("statut", i.getStatut().toString());
                    jsonContainer.addProperty("dateDebut", i.getDate_emission().toString());
                    if (i.getDate_fin() != null) {
                        jsonContainer.addProperty("dateFin", i.getDate_fin().toString());
                    }
                    
                    if (i instanceof Animal) {
                        jsonContainer.addProperty("type", "Animal");
                        jsonContainer.addProperty("nomAnimal", ((Animal) i).getNom_animal());
                    } else if (i instanceof Livraison) {
                        jsonContainer.addProperty("type", "Livraison");
                        jsonContainer.addProperty("objet", ((Livraison) i).getObjet());
                        jsonContainer.addProperty("entreprise", ((Livraison) i).getEntreprise());
                    } else if (i instanceof Incident) {
                        jsonContainer.addProperty("type", "Incident");
                    }
                }
            } else {
                jsonContainer.addProperty("intervention", false);
                if (session.getAttribute("utilisateur") instanceof Employe) {
                    jsonContainer.addProperty("nomEmp", ((Employe) session.getAttribute("utilisateur")).getNom());
                    jsonContainer.addProperty("prenomEmp", ((Employe) session.getAttribute("utilisateur")).getPrenom());
                }
            }
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(jsonContainer, out);
        }
    }
}
