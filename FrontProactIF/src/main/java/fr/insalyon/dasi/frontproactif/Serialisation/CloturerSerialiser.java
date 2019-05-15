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

/**
 *
 * @author svillenave
 */
public class CloturerSerialiser extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try (PrintWriter out = response.getWriter()) {
            response.setContentType("text/html;charset=UTF-8");
            JsonObject jsonContainer = new JsonObject();
            if ((boolean)request.getAttribute("cloture") == true) {
                jsonContainer.addProperty("cloture", true);
            } else {
                jsonContainer.addProperty("cloture", false);
            }
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(jsonContainer, out);
        }
    }
}
