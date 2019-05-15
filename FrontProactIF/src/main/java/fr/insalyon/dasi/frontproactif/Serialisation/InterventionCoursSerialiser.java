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
                    jsonContainer.addProperty("description", i.getDescription());

                    if (i instanceof Animal) {
                        jsonContainer.addProperty("type", "Animal");
                    } else if (i instanceof Livraison) {
                        jsonContainer.addProperty("type", "Livraison");
                    } else if (i instanceof Incident) {
                        jsonContainer.addProperty("type", "Incident");
                    }
                } else if (session.getAttribute("utilisateur") instanceof Employe) {
                    jsonContainer.addProperty("intervention", true);
                    jsonContainer.addProperty("nom", i.getClient().getNom());
                    jsonContainer.addProperty("prenom", i.getClient().getPrenom());
                    jsonContainer.addProperty("adresse", i.getClient().getAdressePostale());
                    jsonContainer.addProperty("description", i.getDescription());

                    if (i instanceof Animal) {
                        jsonContainer.addProperty("type", "Animal");
                    } else if (i instanceof Livraison) {
                        jsonContainer.addProperty("type", "Livraison");
                    } else if (i instanceof Incident) {
                        jsonContainer.addProperty("type", "Incident");
                    }
                }
            } else {
                jsonContainer.addProperty("intervention", false);
            }
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(jsonContainer, out);
        }
    }
}
