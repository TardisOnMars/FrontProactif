/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.frontproactif.Action;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.model.Employe;
import metier.model.Intervention;
import metier.model.Statut;
import metier.service.Service;
import util.DebugLogger;

/**
 *
 * @author yzhao, svillenave
 */
public class CloturerAction extends Action {

    @Override
    public boolean execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        request.setAttribute("cloture", false);
        
        String s = request.getParameter("statut");
        Statut stat;
        if ("Succès".equals(s)) {
            stat = Statut.SUCCES;
        } else {
            stat = Statut.ECHEC;
        }
        String commentaire = (String)request.getParameter("commentaire");
        
        Employe e = (Employe) session.getAttribute("utilisateur");
        Intervention i = Service.ObtenirInterventionEnCours(e);
        if (Service.ValiderIntervention(i, stat, commentaire, new Date())) {
            request.setAttribute("cloture", true);
        }
        return true;
    }
}
