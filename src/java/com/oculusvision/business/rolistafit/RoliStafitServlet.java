/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oculusvision.business.rolistafit;

import com.oculusvision.business.entities.Rolistafit;
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
@WebServlet(name = "RoliStafitServlet", urlPatterns = {"/rolistafit"})
public class RoliStafitServlet extends HttpServlet {

    @Inject
    private RoliStafitEJBLocal roliStafitEJB;
    
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
                shtoRoliStafitForma(request, response);
                break;
            case "edit" :
                editoRoliStafitForma(request, response);
                break;
            default :
                listoRoliStafitForma(request, response);
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
                shtoRolinStafit(request, response);
                break;
            case "mainEdito":
                editoRolinStafit(request, response);
                break;
            case "mainFshij":
                fshijRolinStafit(request, response);
                break;
        }
    }


    private void shtoRoliStafitForma(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher(UrlUtils.SHTO_ROLIN_STAFI_PAGE).forward(request, response);
    }

    private void editoRoliStafitForma(HttpServletRequest request, HttpServletResponse response) 
            throws IOException, ServletException {
        Integer roliStafitId = Integer.parseInt(request.getParameter("RolistafitId"));
        try {
            Rolistafit roliStafit = roliStafitEJB.gjejRolistafit(roliStafitId);
            request.setAttribute("rolistafit", roliStafit);
            request.setAttribute("RolistafitId", roliStafitId);
            request.getRequestDispatcher(UrlUtils.EDITO_ROLIN_STAFI_PAGE).forward(request, response);
        } catch (RoliStafitException ex) {
            Logger.getLogger(Rolistafit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listoRoliStafitForma(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Rolistafit> rolet = null;
        try {
            rolet = roliStafitEJB.ktheRoletstafit();
        } catch (RoliStafitException ex) {
            Logger.getLogger(Rolistafit.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(rolet != null) {
            request.setAttribute("rolet", rolet);
        }
        request.getRequestDispatcher(UrlUtils.SHIKO_ROLIN_STAFI_PAGE).forward(request, response);
    }

    private void shtoRolinStafit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<String> errorMessages = new ArrayList<>();
        String emri       = request.getParameter("emri");
        String pershkrimi = request.getParameter("pershkrimi");
        
        try {
            if(ValidationUtils.validNullEmpty(emri)) {
                errorMessages.add("Emri eshte i zbrazet");
            }


            if(ValidationUtils.validNullEmpty(pershkrimi)) {
                errorMessages.add("Pershkrimi eshte i zbrazet");
            }
            
            
            if(!errorMessages.isEmpty()) {
                throw new RoliStafitException("Forma te pa mbushura");
            }
        } catch (RoliStafitException ex) {
            request.setAttribute("errors", errorMessages);
            request.setAttribute("emri", emri);
            request.setAttribute("pershkrimi", pershkrimi);
            request.getRequestDispatcher(UrlUtils.SHTO_ROLIN_STAFI_PAGE).forward(request, response);
        }
        
        try {
            Rolistafit roli = new Rolistafit();
            roli.setEmriRolit(emri);
            roli.setPershkrimiRolit(pershkrimi);
            roliStafitEJB.shtoRoliStafit(roli);
            request.setAttribute("success", true);
            request.setAttribute("emri", emri);
            request.setAttribute("pershkrimi", pershkrimi);
            request.getRequestDispatcher(UrlUtils.SHTO_ROLIN_STAFI_PAGE).forward(request, response);
        } catch (RoliStafitException ex) {
            errorMessages.add(ex.toString());
            request.setAttribute("errors", errorMessages);
            request.getRequestDispatcher(UrlUtils.SHTO_KUALIFIKIMIN_PAGE).forward(request, response);
        }
    }

    private void editoRolinStafit(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        ArrayList<String> errorMessages = new ArrayList<>();
        String emri       = request.getParameter("emri");
        String pershkrimi = request.getParameter("pershkrimi");
        
        try {
            if(ValidationUtils.validNullEmpty(emri)) {
                errorMessages.add("Emri eshte i zbrazet");
            }


            if(ValidationUtils.validNullEmpty(pershkrimi)) {
                errorMessages.add("Pershkrimi eshte i zbrazet");
            }
            
            
            if(!errorMessages.isEmpty()) {
                throw new RoliStafitException("Forma te pa mbushura");
            }
        } catch (RoliStafitException ex) {
            request.setAttribute("errors", errorMessages);
            request.setAttribute("emri", emri);
            request.setAttribute("pershkrimi", pershkrimi);
            request.getRequestDispatcher(UrlUtils.SHTO_ROLIN_STAFI_PAGE).forward(request, response);
        }
        
        try {
            String roliStfIdStr = request.getParameter("RolistafitId");
            Integer roliStafitId = Integer.parseInt(roliStfIdStr);
            Rolistafit roli = roliStafitEJB.gjejRolistafit(roliStafitId);
            roli.setEmriRolit(emri);
            roli.setPershkrimiRolit(pershkrimi);
            roliStafitEJB.editoRolistafit(roliStafitId, roli);
            request.setAttribute("success", true);
            request.setAttribute("rolistafit", roli);
            request.getRequestDispatcher(UrlUtils.EDITO_ROLIN_STAFI_PAGE).forward(request, response);
        } catch (RoliStafitException ex) {
            errorMessages.add(ex.toString());
            request.setAttribute("errors", errorMessages);
            request.getRequestDispatcher(UrlUtils.EDITO_ROLIN_STAFI_PAGE).forward(request, response);
        }
    }

    private void fshijRolinStafit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer rolistafitId = Integer.parseInt(request.getParameter("RolistafitId"));
        try {
            if(roliStafitEJB.fshijRoliStafit(rolistafitId)) {
                request.setAttribute("successDelete", true);
            } else {
                request.setAttribute("successDelete", false);
            }
            listoRoliStafitForma(request, response);
        } catch (RoliStafitException ex) {
            request.setAttribute("successDelete", false);
            request.setAttribute("errorMsg", ex.toString());
            Logger.getLogger(Rolistafit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
