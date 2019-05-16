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
import metier.model.Client;
import metier.model.Employe;
import metier.model.Personne;

/**
 *
 * @author svillenave
 */
public class ConnexionSerialiser extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try (PrintWriter out = response.getWriter()) {
            response.setContentType("text/html;charset=UTF-8");
            JsonObject jsonContainer = new JsonObject();
            if (request.getAttribute("connexion") != null) {
                jsonContainer.addProperty("connexion", true);
                if ((Personne) request.getAttribute("connexion") instanceof Client) {
                    jsonContainer.addProperty("type", "client");
                } else if ((Personne) request.getAttribute("connexion") instanceof Employe) {
                    jsonContainer.addProperty("type", "employe");
                }
            } else {
                jsonContainer.addProperty("connexion", false);
            }
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(jsonContainer, out);
        }
    }
}
