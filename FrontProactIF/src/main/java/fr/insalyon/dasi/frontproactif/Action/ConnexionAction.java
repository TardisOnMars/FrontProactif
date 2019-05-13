/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.frontproactif.Action;

import javax.servlet.http.HttpServletRequest;
import metier.model.Personne;
import metier.service.Service;

/**
 *
 * @author svillenave
 */
public class ConnexionAction extends Action {

    @Override
    public boolean execute(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        Personne p = Service.ValiditeConnexion(login, password);

        if (p == null) {
            request.setAttribute("connexion", null);
        } else {
            request.setAttribute("connexion", p);
        }
        return true;
    }
}