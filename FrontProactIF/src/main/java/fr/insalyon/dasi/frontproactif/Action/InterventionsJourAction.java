/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.frontproactif.Action;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.model.Client;
import metier.model.Employe;
import metier.model.Intervention;
import metier.model.Personne;
import metier.service.Service;

/**
 *
 * @author sosos
 */
public class InterventionsJourAction extends Action{
    @Override
    public boolean execute(HttpServletRequest request) {
        List<Intervention> li = Service.RechercherInterventionsDuJour(new Date());
        
        if (li == null) {
            request.setAttribute("intervention", null);
        } else {
            request.setAttribute("intervention", li);
        }
        return true;
    }
}
