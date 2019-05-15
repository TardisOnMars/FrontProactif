/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.frontproactif.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.model.Client;
import metier.model.Intervention;
import metier.service.Service;

/**
 *
 * @author sosos
 */
public class InterventionCoursAction extends Action{
    @Override
    public boolean execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Client c = (Client)session.getAttribute("utilisateur");
        Intervention i = Service.ObtenirInterventionEnCours(c);

        if (c == null) {
            request.setAttribute("intervention", null);
        } else {
            request.setAttribute("intervention", i);
        }
        return true;
    }
}
