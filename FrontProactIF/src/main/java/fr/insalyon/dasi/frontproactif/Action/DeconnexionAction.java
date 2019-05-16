/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.frontproactif.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author svillenave
 */
public class DeconnexionAction extends Action {

    @Override
    public boolean execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.invalidate();
        return true;
    }
}