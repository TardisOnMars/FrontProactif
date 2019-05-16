/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.frontproactif.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.model.Client;
import metier.model.Employe;
import metier.model.Personne;

/**
 *
 * @author svillenave
 */
public class VerificationAction extends Action {

    @Override
    public boolean execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Personne p = (Personne) session.getAttribute("utilisateur");
        if (p instanceof Client) {
            request.setAttribute("type", "client");
        } else if (p instanceof Employe) {
            request.setAttribute("type", "employe");
        } else {
            request.setAttribute("type", null);
        }
        return true;
    }
}
