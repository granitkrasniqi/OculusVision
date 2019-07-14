/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oculusvision.business.klinika;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import com.oculusvision.business.entities.Klinika;
import com.oculusvision.business.utils.UrlUtils;
import com.oculusvision.business.utils.ValidationUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 *
 * @author Granit Krasniqi
 */
@WebServlet(name = "KlinikaServlet", urlPatterns = {"/klinika"})
public class KlinikaServlet extends HttpServlet {
    
    @Inject
    private KlinikaEJBLocal klinikaEJB;


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
                shtoKlinikaForma(request, response);
                break;
            case "edit" :
                editoKlinikaForma(request, response);
                break;
            default :
                listoKlinikaForma(request, response);
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
                shtoKliniken(request, response);
                break;
            case "mainEdito":
                editoKliniken(request, response);
                break;
            case "mainFshij":
                fshijKliniken(request, response);
                break;
        }
    }


    private void shtoKliniken(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<String> errorMessages = new ArrayList<>();
        String emri         = request.getParameter("emri");
        String adresa       = request.getParameter("adresa");
        String pershkrimi   = request.getParameter("pershkrimi");
        
        try {
            if(ValidationUtils.validNullEmpty(emri)) {
                errorMessages.add("Emri eshte i zbrazet");
            }

            if(ValidationUtils.validNullEmpty(adresa)) {
                errorMessages.add("Adresa eshte e zbrazet");
            }

            if(ValidationUtils.validNullEmpty(pershkrimi)) {
                errorMessages.add("Pershkrimi eshte i zbrazet");
            }
            
            if(!errorMessages.isEmpty()) {
                throw new KlinikaException("Forma te pa mbushura");
            }
        } catch (KlinikaException ex) {
            request.setAttribute("errors", errorMessages);
            request.setAttribute("emri", emri);
            request.setAttribute("adresa", adresa);
            request.setAttribute("pershkrimi", pershkrimi);
            request.getRequestDispatcher(UrlUtils.SHTO_KLINIKEN_PAGE).forward(request, response);
        }
        
        try {
            Klinika klinika = new Klinika();
            klinika.setEmri(emri);
            klinika.setAdresa(adresa);
            klinika.setPershkrimi(pershkrimi);
            klinikaEJB.shtoKliniken(klinika);
            request.setAttribute("success", true);
            request.setAttribute("emri", emri);
            request.setAttribute("adresa", adresa);
            request.setAttribute("pershkrimi", pershkrimi);
            request.getRequestDispatcher(UrlUtils.SHTO_KLINIKEN_PAGE).forward(request, response);
        } catch (KlinikaException ex) {
            errorMessages.add(ex.toString());
            request.setAttribute("errors", errorMessages);
            request.getRequestDispatcher(UrlUtils.SHTO_KLINIKEN_PAGE).forward(request, response);
            Logger.getLogger(KlinikaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    private void editoKliniken(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<String> errorMessages = new ArrayList<>();
        String emri         = request.getParameter("emri");
        String adresa       = request.getParameter("adresa");
        String pershkrimi   = request.getParameter("pershkrimi");
        Integer klinikaId   = Integer.parseInt(request.getParameter("KlinikaId"));
        
        try {
            if(ValidationUtils.validNullEmpty(emri)) {
                errorMessages.add("Emri eshte i zbrazet");
            }

            if(ValidationUtils.validNullEmpty(adresa)) {
                errorMessages.add("Adresa eshte e zbrazet");
            }

            if(ValidationUtils.validNullEmpty(pershkrimi)) {
                errorMessages.add("Pershkrimi eshte i zbrazet");
            }
            
            if(!errorMessages.isEmpty()) {
                throw new KlinikaException("Forma te pa mbushura");
            }
        } catch (KlinikaException ex) {
            request.setAttribute("errors", errorMessages);
            request.setAttribute("emri", emri);
            request.setAttribute("adresa", adresa);
            request.setAttribute("pershkrimi", pershkrimi);
            request.getRequestDispatcher(UrlUtils.EDITO_KLINIKEN_PAGE).forward(request, response);
        }
        
        try {
            klinikaEJB.editoKliniken(klinikaId, emri, adresa, pershkrimi);
            request.setAttribute("success", true);
//            request.setAttribute("emri", emri);
//            request.setAttribute("adresa", adresa);
//            request.setAttribute("pershkrimi", pershkrimi);
            editoKlinikaForma(request, response);
        } catch (KlinikaException ex) {
            errorMessages.add(ex.toString());
            request.setAttribute("errors", errorMessages);
            request.getRequestDispatcher(UrlUtils.EDITO_KLINIKEN_PAGE).forward(request, response);
            Logger.getLogger(KlinikaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*
        Redirektimet ne Forma per Klinika - CRUD
    
    */

    private void shtoKlinikaForma(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(UrlUtils.SHTO_KLINIKEN_PAGE).forward(request, response);
    }

    private void editoKlinikaForma(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        Integer klinikaId = Integer.parseInt(request.getParameter("KlinikaId"));
        try {
            Klinika klinika = klinikaEJB.gjejKliniken(klinikaId);
            request.setAttribute("klinika", klinika);
            request.setAttribute("KlinikaId", klinika.getId());
            request.getRequestDispatcher(UrlUtils.EDITO_KLINIKEN_PAGE).forward(request, response);
        } catch (KlinikaException ex) {
            Logger.getLogger(KlinikaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }


    private void listoKlinikaForma(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Klinika> klinikat = null;
        try {
            klinikat = klinikaEJB.ktheKlinikat();
        } catch (KlinikaException ex) {
            Logger.getLogger(KlinikaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(klinikat != null) {
            request.setAttribute("klinikat", klinikat);
        }
        request.getRequestDispatcher(UrlUtils.SHIKO_KLINIKAT_PAGE).forward(request, response);
    }

    private void fshijKliniken(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer klinikaId = Integer.parseInt(request.getParameter("KlinikaId"));
        try {
            if(klinikaEJB.fshijKliniken(klinikaId)) {
                request.setAttribute("successDelete", true);
            } else {
                request.setAttribute("successDelete", false);
            }
            listoKlinikaForma(request, response);
        } catch (KlinikaException ex) {
            request.setAttribute("successDelete", false);
            request.setAttribute("errorMsg", ex.toString());
            listoKlinikaForma(request, response);
            Logger.getLogger(KlinikaServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (EJBException ex) {
            System.out.println("Root Cause");
            request.setAttribute("successDelete", false);
            String exceptionMessage = ExceptionUtils.getRootCauseMessage(ex);
            if(exceptionMessage.contains("foreign key constraint fails")) {
                exceptionMessage = "Keni të dhëna të asocuara me kliniken,"
                        + " klinika mund te fshihet vetem pasi që të fshihen te gjitha te dhënat e asocuara";
            }
            request.setAttribute("errorMsg", exceptionMessage);
            listoKlinikaForma(request, response);
//            ExceptionUtils.getRootCause(ex).printStackTrace();
        }
    }

}
