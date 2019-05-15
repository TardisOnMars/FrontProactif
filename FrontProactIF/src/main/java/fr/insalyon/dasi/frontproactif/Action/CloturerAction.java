/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.frontproactif.Action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.model.Employe;
import metier.model.Intervention;
import metier.model.Statut;
import metier.service.Service;

/**
 *
 * @author yzhao, svillenave
 */
public class CloturerAction extends Action {

    @Override
    public boolean execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Employe e = (Employe) session.getAttribute("utilisateur");
        request.setAttribute("cloture", false);
        
        String s = (String) request.getAttribute("statut");
        Statut stat;
        if ("Succès".equals(s)) {
            stat = Statut.SUCCES;
        } else {
            stat = Statut.ECHEC;
        }

        String commentaire = (String) request.getAttribute("commentaire");
        Intervention i = Service.ObtenirInterventionEnCours(e);
        
        if(Service.ValiderIntervention(i, stat, commentaire, new Date())){
            request.setAttribute("cloture", true);
        }
        return true;
    }
}
