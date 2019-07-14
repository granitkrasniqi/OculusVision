/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oculusvision.business.stafi;

import com.oculusvision.business.entities.Klinika;
import com.oculusvision.business.entities.Kualifikimet;
import com.oculusvision.business.entities.Rolistafit;
import com.oculusvision.business.entities.Stafi;
import com.oculusvision.business.klinika.KlinikaEJB;
import com.oculusvision.business.klinika.KlinikaEJBLocal;
import com.oculusvision.business.klinika.KlinikaException;
import com.oculusvision.business.kualifikimet.KualifikimetEJBLocal;
import com.oculusvision.business.kualifikimet.KualifikimetException;
import com.oculusvision.business.rolistafit.RoliStafitEJBLocal;
import com.oculusvision.business.rolistafit.RoliStafitException;
import com.oculusvision.business.utils.UrlUtils;
import com.oculusvision.business.utils.ValidationUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Granit Krasniqi
 */
@WebServlet(name = "StafiServlet", urlPatterns = {"/stafi"})
public class StafiServlet extends HttpServlet {
    
    @Inject
    private KlinikaEJBLocal klinikaEJB;
    
    @Inject
    private KualifikimetEJBLocal kualifikimetEJB;
    
    @Inject
    private RoliStafitEJBLocal rolitStafitEJB;
    
    @Inject
    private StafiEJBLocal stafiEJB;
    

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
        String action = request.getParameter("action");
        
        if (action == null) {
            action = "listo";
        }
        
        switch(action) {
            case "shto" :
                shtoStafinForma(request, response);
                break;
            case "edit" :
                editoStafinForma(request, response);
                break;
            default :
                listoStafinForma(request, response);
        }
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
        String action = request.getParameter("mainAction");
        
        if (action == null) {
            action = "mainListo";
        }
        
        switch(action) {
            case "mainShto":
                shtoStafin(request, response);
                break;
            case "mainEdito":
                editoStafin(request, response);
                break;
            case "mainFshij":
                fshijStafin(request, response);
                break;
        }
    }

    private void shtoStafinForma(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        try {
            List<Klinika> klinikat = klinikaEJB.ktheKlinikat();
            List<Kualifikimet> kualifikimet = kualifikimetEJB.ktheKualifikimet();
            List<Rolistafit> rolet = rolitStafitEJB.ktheRoletstafit();
            request.setAttribute("klinikat", klinikat);
            request.setAttribute("kualifikimet", kualifikimet);
            request.setAttribute("rolet", rolet);
            request.getRequestDispatcher(UrlUtils.SHTO_STAFIN_PAGE).forward(request, response);
        } catch (KlinikaException | KualifikimetException | RoliStafitException ex) {
            Logger.getLogger(StafiServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void editoStafinForma(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
    }

    private void listoStafinForma(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        List<Stafi> stafi = null;
        try {
            stafi = stafiEJB.ktheStafin();
        } catch (StafiException ex) {
            Logger.getLogger(Rolistafit.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(stafi != null) {
            request.setAttribute("stafi", stafi);
        }
        
        request.getRequestDispatcher(UrlUtils.SHIKO_STAFIN_PAGE).forward(request, response);
    }

    private void shtoStafin(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        ArrayList<String> errorMessages = new ArrayList<>();
        String emri       = request.getParameter("emri");
        String mbiemri    = request.getParameter("mbiemri");
        String gjinia     = request.getParameter("gjinia");
        Integer kualifikimiId = Integer.parseInt(request.getParameter("kualifikimi"));
        Integer klinikaId = Integer.parseInt(request.getParameter("klinika"));
        Integer roliStfId = Integer.parseInt(request.getParameter("rolistafit"));
        String pershkrimi = request.getParameter("pershkrimi");
        
        try {
            if(ValidationUtils.validNullEmpty(emri)) {
                errorMessages.add("Emri është i zbrazët");
            }
            
            if(ValidationUtils.validNullEmpty(mbiemri)) {
                errorMessages.add("Mbiemri është i zbrazët");
            }

            if(!errorMessages.isEmpty()) {
                throw new StafiException("Forma te pa mbushura");
            }
        } catch (StafiException ex) {
            request.setAttribute("errors", errorMessages);
            request.setAttribute("emri", emri);
            request.setAttribute("mbiemri", mbiemri);
            request.setAttribute("pershkrimi", pershkrimi);
            request.getRequestDispatcher(UrlUtils.SHTO_STAFIN_PAGE).forward(request, response);
        }
        try {
            Stafi stafi = new Stafi();
            stafi.setEmri(emri);
            stafi.setMbiemri(mbiemri);
            stafi.setGjinia(gjinia.charAt(0));
            Klinika klinika = klinikaEJB.gjejKliniken(klinikaId);
            Kualifikimet kualifikimet = kualifikimetEJB.gjejKualifikimin(kualifikimiId);
            Rolistafit   rolistafit = rolitStafitEJB.gjejRolistafit(roliStfId);
            stafi.setKlinikaID(klinika);
            stafi.setKualifikimetID(kualifikimet);
            stafi.setRoliStafitID(rolistafit);
            stafiEJB.shtoStafin(stafi);
            request.setAttribute("success", true);
            request.setAttribute("emri", emri);
            request.setAttribute("mbiemri", mbiemri);
            request.setAttribute("pershkrimi", pershkrimi);
            request.getRequestDispatcher(UrlUtils.SHTO_STAFIN_PAGE).forward(request, response);
        } catch (KlinikaException ex) {
            Logger.getLogger(StafiServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (KualifikimetException ex) {
            Logger.getLogger(StafiServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (StafiException ex) {
            Logger.getLogger(StafiServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RoliStafitException ex) {
            Logger.getLogger(StafiServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void editoStafin(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void fshijStafin(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        Integer stafiId = Integer.parseInt(request.getParameter("StafiId"));
        try {
            if(stafiEJB.fshijStafin(stafiId)) {
                request.setAttribute("successDelete", true);
            } else {
                request.setAttribute("successDelete", false);
            }
            listoStafinForma(request, response);
        } catch (StafiException ex) {
            request.setAttribute("successDelete", false);
            request.setAttribute("errorMsg", ex.toString());
            Logger.getLogger(Kualifikimet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
