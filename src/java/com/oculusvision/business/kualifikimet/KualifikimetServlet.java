/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oculusvision.business.kualifikimet;

import com.oculusvision.business.entities.Kualifikimet;
import com.oculusvision.business.utils.UrlUtils;
import com.oculusvision.business.utils.ValidationUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
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
@WebServlet(name = "KualifikimetServlet", urlPatterns = {"/kualifikimi"})
public class KualifikimetServlet extends HttpServlet {

    
    @Inject
    private KualifikimetEJBLocal kualifikimiEJB;
    
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
                shtoKualifikiminForma(request, response);
                break;
            case "edit" :
                editoKualifikiminForma(request, response);
                break;
            default :
                listoKualifikiminForma(request, response);
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
                shtoKualifikimin(request, response);
                break;
            case "mainEdito":
                editoKualifikimin(request, response);
                break;
            case "mainFshij":
                fshijKualifikimin(request, response);
                break;
        }
    }

    /*
        Format per CRUD
    
    */
    
    private void shtoKualifikiminForma(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(UrlUtils.SHTO_KUALIFIKIMIN_PAGE).forward(request, response);
    }

    private void editoKualifikiminForma(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer kualifikimiId = Integer.parseInt(request.getParameter("KualifikimiId"));
        try {
            Kualifikimet kualifikimi = kualifikimiEJB.gjejKualifikimin(kualifikimiId);
            request.setAttribute("kualifikimi", kualifikimi);
            request.setAttribute("KualifikimiId", kualifikimiId);
            request.getRequestDispatcher(UrlUtils.EDITO_KUALIFIKIMIN_PAGE).forward(request, response);
        } catch (KualifikimetException ex) {
            Logger.getLogger(Kualifikimet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listoKualifikiminForma(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<Kualifikimet> kualifikimet = null;
        try {
            kualifikimet = kualifikimiEJB.ktheKualifikimet();
        } catch (KualifikimetException ex) {
            Logger.getLogger(Kualifikimet.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(kualifikimet != null) {
            request.setAttribute("kualifikimet", kualifikimet);
        }
        request.getRequestDispatcher(UrlUtils.SHIKO_KUALIFIKIMIN_PAGE).forward(request, response);
    }
    
    /*
        
        Logjika Per CRUD
    
    */

    private void shtoKualifikimin(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        ArrayList<String> errorMessages = new ArrayList<>();
        String emri       = request.getParameter("emri");
        String pershkrimi = request.getParameter("pershkrimi");
        String shteti     = request.getParameter("shteti");
        String fakulteti  = request.getParameter("fakulteti");
        
        try {
            if(ValidationUtils.validNullEmpty(emri)) {
                errorMessages.add("Emri eshte i zbrazet");
            }


            if(ValidationUtils.validNullEmpty(pershkrimi)) {
                errorMessages.add("Pershkrimi eshte i zbrazet");
            }
            
            if(ValidationUtils.validNullEmpty(shteti)) {
                errorMessages.add("Shteti eshte i zbrazet");
            }
            
            if(ValidationUtils.validNullEmpty(fakulteti)) {
                errorMessages.add("Fakulteti eshte i zbrazet");
            }
            
            if(!errorMessages.isEmpty()) {
                throw new KualifikimetException("Forma te pa mbushura");
            }
        } catch (KualifikimetException ex) {
            request.setAttribute("errors", errorMessages);
            request.setAttribute("emri", emri);
            request.setAttribute("pershkrimi", pershkrimi);
            request.setAttribute("shteti", shteti);
            request.setAttribute("fakulteti", fakulteti);
            request.getRequestDispatcher(UrlUtils.SHTO_KUALIFIKIMIN_PAGE).forward(request, response);
        }
        
        try {
            Kualifikimet kualifikimi = new Kualifikimet();
            kualifikimi.setEmri(emri);
            kualifikimi.setPershkrimi(pershkrimi);
            kualifikimi.setShteti(shteti);
            kualifikimi.setFakulteti(fakulteti);
            kualifikimiEJB.shtoKualifikimin(kualifikimi);
            request.setAttribute("success", true);
            request.setAttribute("emri", emri);
            request.setAttribute("shteti", shteti);
            request.setAttribute("fakulteti", fakulteti);
            request.setAttribute("pershkrimi", pershkrimi);
            request.getRequestDispatcher(UrlUtils.SHTO_KUALIFIKIMIN_PAGE).forward(request, response);
        } catch (KualifikimetException ex) {
            errorMessages.add(ex.toString());
            request.setAttribute("errors", errorMessages);
            request.getRequestDispatcher(UrlUtils.SHTO_KUALIFIKIMIN_PAGE).forward(request, response);
            Logger.getLogger(KualifikimetServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void editoKualifikimin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<String> errorMessages = new ArrayList<>();
        String emri       = request.getParameter("emri");
        String pershkrimi = request.getParameter("pershkrimi");
        String shteti     = request.getParameter("shteti");
        String fakulteti  = request.getParameter("fakulteti");
        
        try {
            if(ValidationUtils.validNullEmpty(emri)) {
                errorMessages.add("Emri eshte i zbrazet");
            }


            if(ValidationUtils.validNullEmpty(pershkrimi)) {
                errorMessages.add("Pershkrimi eshte i zbrazet");
            }
            
            if(ValidationUtils.validNullEmpty(shteti)) {
                errorMessages.add("Shteti eshte i zbrazet");
            }
            
            if(ValidationUtils.validNullEmpty(fakulteti)) {
                errorMessages.add("Fakulteti eshte i zbrazet");
            }
            
            if(!errorMessages.isEmpty()) {
                throw new KualifikimetException("Forma te pa mbushura");
            }
        } catch (KualifikimetException ex) {
            request.setAttribute("errors", errorMessages);
            request.setAttribute("emri", emri);
            request.setAttribute("pershkrimi", pershkrimi);
            request.setAttribute("shteti", shteti);
            request.setAttribute("fakulteti", fakulteti);
            request.getRequestDispatcher(UrlUtils.EDITO_KUALIFIKIMIN_PAGE).forward(request, response);
        }
        
        try {
            String kualifikimiIdStr = request.getParameter("KualifikimiId");
            Integer kualifikimiId = Integer.parseInt(kualifikimiIdStr);
            Kualifikimet k = kualifikimiEJB.gjejKualifikimin(kualifikimiId);
            k.setEmri(emri);
            k.setPershkrimi(pershkrimi);
            k.setShteti(shteti);
            k.setFakulteti(fakulteti);
            kualifikimiEJB.editoKualifikimin(kualifikimiId, k);
            request.setAttribute("success", true);
            request.setAttribute("kualifikimi", k);
            request.getRequestDispatcher(UrlUtils.EDITO_KUALIFIKIMIN_PAGE).forward(request, response);
        } catch (KualifikimetException ex) {
            errorMessages.add(ex.toString());
            request.setAttribute("errors", errorMessages);
            request.getRequestDispatcher(UrlUtils.EDITO_KUALIFIKIMIN_PAGE).forward(request, response);
            Logger.getLogger(KualifikimetServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fshijKualifikimin(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        Integer kualifikimiId = Integer.parseInt(request.getParameter("KualifikimiId"));
        try {
            if(kualifikimiEJB.fshijKualifikimin(kualifikimiId)) {
                request.setAttribute("successDelete", true);
            } else {
                request.setAttribute("successDelete", false);
            }
            listoKualifikiminForma(request, response);
        } catch (KualifikimetException ex) {
            request.setAttribute("successDelete", false);
            request.setAttribute("errorMsg", ex.toString());
            Logger.getLogger(Kualifikimet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
