/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.frontproactif;

import dao.JpaUtil;
import fr.insalyon.dasi.frontproactif.Action.Action;
import fr.insalyon.dasi.frontproactif.Action.CloturerAction;
import fr.insalyon.dasi.frontproactif.Action.ConnexionAction;
import fr.insalyon.dasi.frontproactif.Action.CreerInterventionAction;
import fr.insalyon.dasi.frontproactif.Action.DeconnexionAction;
import fr.insalyon.dasi.frontproactif.Action.HistoriqueAction;
import fr.insalyon.dasi.frontproactif.Action.InscriptionAction;
import fr.insalyon.dasi.frontproactif.Action.InterventionCoursAction;
import fr.insalyon.dasi.frontproactif.Action.InterventionsJourAction;
import fr.insalyon.dasi.frontproactif.Action.VerificationAction;
import fr.insalyon.dasi.frontproactif.Serialisation.CloturerSerialiser;
import fr.insalyon.dasi.frontproactif.Serialisation.ConnexionSerialiser;
import fr.insalyon.dasi.frontproactif.Serialisation.CreerInterventionSerialiser;
import fr.insalyon.dasi.frontproactif.Serialisation.HistoriqueSerialiser;
import fr.insalyon.dasi.frontproactif.Serialisation.InscriptionSerialiser;
import fr.insalyon.dasi.frontproactif.Serialisation.InterventionCoursSerialiser;
import fr.insalyon.dasi.frontproactif.Serialisation.InterventionsJourSerialiser;
import fr.insalyon.dasi.frontproactif.Serialisation.Serialisation;
import fr.insalyon.dasi.frontproactif.Serialisation.VerificationSerialisation;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import metier.model.Personne;
import util.DebugLogger;

/**
 *
 * @author svillenave
 */
@WebServlet(name = "ActionServlet", urlPatterns = {"/ActionServlet"})
public class ActionServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        request.setCharacterEncoding("UTF-8");
        String todo = request.getParameter("todo");

        if ("inscription".equals(todo)) {
            InscriptionAction ia = new InscriptionAction();
            ia.execute(request);
            InscriptionSerialiser is = new InscriptionSerialiser();
            is.serialiser(request, response);
            DebugLogger.log("Inscription !");
        } else if ("connexion".equals(todo)) {
            ConnexionAction ca = new ConnexionAction();
            ca.execute(request);
            ConnexionSerialiser cs = new ConnexionSerialiser();
            cs.serialiser(request, response);
            session.setAttribute("utilisateur", request.getAttribute("connexion"));
            Personne user = (Personne) session.getAttribute("utilisateur");

        } else {
            Personne user = (Personne) session.getAttribute("utilisateur");

            if (user == null) {
                response.sendError(403, "Forbidden (NoUser)");
                DebugLogger.log("Forbidden");
            } else {
                Action a = null;
                Serialisation s = null;
                switch (todo) {
                    case "historiqueIntervention":
                        a = new HistoriqueAction();
                        a.execute(request);
                        s = new HistoriqueSerialiser();
                        s.serialiser(request, response);
                        break;
                    case "creerIntervention":
                        a = new CreerInterventionAction();
                        a.execute(request);
                        s = new CreerInterventionSerialiser();
                        s.serialiser(request, response);
                        break;
                    case "interventionEnCours":
                        a = new InterventionCoursAction();
                        a.execute(request);
                        s = new InterventionCoursSerialiser();
                        s.serialiser(request, response);
                        break;
                    case "cloturerIntervention":
                        a = new CloturerAction();
                        a.execute(request);
                        s = new CloturerSerialiser();
                        s.serialiser(request, response);
                        break;
                    case "interventionsJour":
                        a = new InterventionsJourAction();
                        a.execute(request);
                        s = new InterventionsJourSerialiser();
                        s.serialiser(request, response);
                        break;
                    case "deconnexion":
                        a = new DeconnexionAction();
                        a.execute(request);
                        break;
                    case "verification":
                        a = new VerificationAction();
                        a.execute(request);
                        s = new VerificationSerialisation();
                        s.serialiser(request, response);
                        break;
                }
                if (todo == null) {
                    response.sendError(400, "Bad Request (Wrong TODO parameter");
                } else {

                }
            }
        }
    }

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        JpaUtil.init();
    }

    @Override
    public void destroy() {
        JpaUtil.destroy();
        super.destroy(); //To change body of generated methods, choose Tools | Templates.
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
