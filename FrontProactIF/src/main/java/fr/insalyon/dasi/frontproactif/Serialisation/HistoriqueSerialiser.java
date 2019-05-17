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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.model.Animal;
import metier.model.Incident;
import metier.model.Intervention;
import metier.model.Livraison;

/**
 *
 * @author sosos
 */
public class HistoriqueSerialiser extends Serialisation {

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
                    JsonObject jsonIntervention = new JsonObject();
                    jsonIntervention.addProperty("statut", i.getStatut().toString());
                    jsonIntervention.addProperty("dateDebut", i.getDate_emission().toString());

                    if (i.getDate_fin() != null) {
                        jsonIntervention.addProperty("dateFin", i.getDate_fin().toString());
                    }

                    jsonIntervention.addProperty("description", i.getDescription());

                    if (i.getCommentaire() != null) {
                        jsonIntervention.addProperty("commentaire", i.getCommentaire());
                    }

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
