/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oculusvision.business.terminet;

import com.oculusvision.business.entities.Kualifikimet;
import com.oculusvision.business.entities.Termini;
import com.oculusvision.business.kualifikimet.KualifikimetException;
import com.oculusvision.business.kualifikimet.KualifikimetServlet;
import com.oculusvision.business.utils.UrlUtils;
import com.oculusvision.business.utils.ValidationUtils;
import java.io.IOException;
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
@WebServlet(name = "TerminetServlet", urlPatterns = {"/termini"})
public class TerminetServlet extends HttpServlet {
    
    @Inject
    private TerminetEJBLocal terminetEJB;
    

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
        String action = request.getParameter("action");
        
        if (action == null) {
            action = "listo";
        }
        
        switch(action) {
            case "shto" :
                shtoTermininForma(request, response);
                break;
            case "edit" :
                editoTermininForma(request, response);
                break;
            default :
                listoTermininForma(request, response);
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
                shtoTerminin(request, response);
                break;
            case "mainEdito":
                editoTerminin(request, response);
                break;
            case "mainFshij":
                fshijTerminin(request, response);
                break;
        }
    }

    private void shtoTermininForma(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        request.getRequestDispatcher(UrlUtils.SHTO_TERMININ_PAGE).forward(request, response);
    }

    private void editoTermininForma(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer terminiId = Integer.parseInt(request.getParameter("TerminiId"));
        try {
            Termini termini = terminetEJB.gjejTerminin(terminiId);
            request.setAttribute("termini", termini);
            request.setAttribute("TerminiId", terminiId);
            request.getRequestDispatcher(UrlUtils.EDITO_TERMININ_PAGE).forward(request, response);
        } catch (TerminetException ex) {
        }
    }

    private void listoTermininForma(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        List<Termini> terminet = null;
        try {
            terminet = terminetEJB.ktheTerminet();
        } catch (TerminetException ex) {
            System.out.println(ex);
        }
        
        if(terminet != null) {
            request.setAttribute("terminet", terminet);
        }
        request.getRequestDispatcher(UrlUtils.SHIKO_TERMININ_PAGE).forward(request, response);
    }

    private void shtoTerminin(HttpServletRequest request, HttpServletResponse response)
        throws  ServletException, IOException {
    }

    private void editoTerminin(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void fshijTerminin(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
