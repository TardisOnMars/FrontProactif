/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.frontproactif.Serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.maps.model.LatLng;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.model.Animal;
import metier.model.Client;
import metier.model.Incident;
import metier.model.Intervention;
import metier.model.Livraison;
import util.DebugLogger;
import util.GeoTest;

/**
 *
 * @author sosos
 */
public class InterventionsJourSerialiser extends Serialisation{
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try (PrintWriter out = response.getWriter()) {
            response.setContentType("text/html;charset=UTF-8");
            JsonObject jsonContainer = new JsonObject();
            JsonArray jsonArrayIntervention = new JsonArray();

            if (request.getAttribute("intervention") != null) {
                List<Intervention> li = (List<Intervention>) request.getAttribute("intervention");
                jsonContainer.addProperty("intervention", true);
                for (Intervention i : li) {
                    Client c = i.getClient();
                    String adresse = c.getAdressePostale();
                    LatLng coord = GeoTest.getLatLng(adresse);
                    JsonObject jsonIntervention = new JsonObject();
                    jsonIntervention.addProperty("statut", i.getStatut().toString());
                    jsonIntervention.addProperty("dateDebut", i.getDate_emission().toString());
                    jsonIntervention.addProperty("description", i.getDescription());
                    jsonIntervention.addProperty("latitude", coord.lat);
                    jsonIntervention.addProperty("longitude", coord.lng);

                    if (i instanceof Animal) {
                        jsonIntervention.addProperty("type", "Animal");
                    } else if (i instanceof Livraison) {
                        jsonIntervention.addProperty("type", "Livraison");
                    } else if (i instanceof Incident) {
                        jsonIntervention.addProperty("type", "Incident");
                    }
                    jsonArrayIntervention.add(jsonIntervention);
                }
                jsonContainer.add("interventions", jsonArrayIntervention);

            } else {
                jsonContainer.addProperty("intervention", false);
            }
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(jsonContainer, out);
        }
    }
}
