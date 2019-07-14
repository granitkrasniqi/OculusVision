/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oculusvision.business.pacientet;

import com.oculusvision.business.entities.Pacienti;
import com.oculusvision.business.utils.UrlUtils;
import com.oculusvision.business.utils.ValidationUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
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
@WebServlet(name = "PacientiServlet", urlPatterns = {"/pacienti"})
public class PacientiServlet extends HttpServlet {

    @Inject
    private PacientiEJBLocal pacientiEJB;

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

        switch (action) {
            case "shto":
                shtoPacientinForma(request, response);
                break;
            case "edit":
                editoPacientinForma(request, response);
                break;
            default:
                listoPacientinForma(request, response);
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

        switch (action) {
            case "mainShto":
                shtoPacientin(request, response);
                break;
            case "mainEdito":
                editoPacientin(request, response);
                break;
            case "mainFshij":
                fshijPacientin(request, response);
                break;
        }
    }


    private void shtoPacientinForma(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.getRequestDispatcher(UrlUtils.SHTO_PACIENTIN_PAGE).forward(request, response);
    }

    private void editoPacientinForma(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        Integer pacientiId = Integer.parseInt(request.getParameter("PacientiId"));
        try {
            Pacienti pacienti = pacientiEJB.gjejPacientin(pacientiId);
            request.setAttribute("pacienti", pacienti);
            request.setAttribute("PacientiId", pacientiId);
            request.getRequestDispatcher(UrlUtils.EDITO_PACIENIN_PAGE).forward(request, response);
        } catch (PacientiException ex) {
            Logger.getLogger(Pacienti.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void listoPacientinForma(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        List<Pacienti> pacientet = null;
        try {
            pacientet = pacientiEJB.kthePacientet();
        } catch(PacientiException ex) {
            
        }
        if(pacientet != null) {
            request.setAttribute("pacientet", pacientet);
        }
        request.getRequestDispatcher(UrlUtils.SHIKO_PACIENIN_PAGE).forward(request, response);
    }

    private void shtoPacientin(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        ArrayList<String> errorMessages = new ArrayList<>();
        String emri = request.getParameter("emri");
        String mbiemri = request.getParameter("mbiemri");
        String gjinia = request.getParameter("gjinia");
        String dataLindjes = request.getParameter("dataLindjes");
        String adresa = request.getParameter("adresa");
        String email = request.getParameter("email");
        String numriTelefonit = request.getParameter("numriTelefonit");
        String pershkrimi = request.getParameter("pershkrimi");

        try {
            if (ValidationUtils.validNullEmpty(emri)) {
                errorMessages.add("Emri eshte i zbrazet");
            }
            
            if(!ValidationUtils.onlyAlphabetic(emri)) {
                errorMessages.add("Emri përmban karaktere invalide");
            }

            if (ValidationUtils.validNullEmpty(mbiemri)) {
                errorMessages.add("Mbiemri eshte i zbrazet");
            }
            
            if(!ValidationUtils.onlyAlphabetic(mbiemri)) {
                errorMessages.add("Mbiemri përmban karaktere invalide");
            }

            if (ValidationUtils.validNullEmpty(dataLindjes)) {
                errorMessages.add("Data e Lindjes eshte e zbrazet");
            }

            if (ValidationUtils.validNullEmpty(adresa)) {
                errorMessages.add("Adresa eshte e zbrazet");
            }

            if (ValidationUtils.validNullEmpty(pershkrimi)) {
                errorMessages.add("Pershkrimi eshte i zbrazet");
            }

            if (!errorMessages.isEmpty()) {
                throw new PacientiException("Forma te pa mbushura");
            }
        } catch (PacientiException ex) {
            request.setAttribute("errors", errorMessages);
            request.setAttribute("emri", emri);
            request.setAttribute("mbiemri", mbiemri);
            request.setAttribute("gjinia", gjinia);
            request.setAttribute("dataLindjes", dataLindjes);
            request.setAttribute("adresa", adresa);
            request.setAttribute("email", email);
            request.setAttribute("numriTelefonit", numriTelefonit);
            request.setAttribute("pershkrimi", pershkrimi);
            request.getRequestDispatcher(UrlUtils.SHTO_PACIENTIN_PAGE).forward(request, response);
        }

        try {
            String[] dobArray = dataLindjes.split("/");
            Integer dobDay = Integer.parseInt(dobArray[0]);
            Integer dobMonth = Integer.parseInt(dobArray[1]);
            Integer dobYear = Integer.parseInt(dobArray[2]);
            GregorianCalendar gc = new GregorianCalendar(dobYear, dobMonth, dobDay);
            Date dob = Date.from(gc.toInstant());
            Pacienti pacienti = new Pacienti();
            pacienti.setEmri(emri);
            pacienti.setMbiemri(mbiemri);
            pacienti.setGjinia(gjinia.charAt(0));
            pacienti.setDataLindjes(dob);
            pacienti.setEmail(email);
            pacienti.setPershkrim(pershkrimi);
            pacienti.setNumriTelefonit(numriTelefonit);
            pacienti.setAdresa(adresa);
            pacientiEJB.shtoPacientin(pacienti);
            request.setAttribute("emri", emri);
            request.setAttribute("mbiemri", mbiemri);
            request.setAttribute("gjinia", gjinia);
            request.setAttribute("dataLindjes", dataLindjes);
            request.setAttribute("adresa", adresa);
            request.setAttribute("email", email);
            request.setAttribute("numriTelefonit", numriTelefonit);
            request.setAttribute("pershkrimi", pershkrimi);
            request.setAttribute("success", true);
            request.getRequestDispatcher(UrlUtils.SHTO_PACIENTIN_PAGE).forward(request, response);
        } catch (PacientiException ex) {
            errorMessages.add(ex.toString());
            request.setAttribute("errors", errorMessages);
            request.getRequestDispatcher(UrlUtils.SHTO_PACIENTIN_PAGE).forward(request, response);
            Logger.getLogger(PacientiException.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void editoPacientin(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        ArrayList<String> errorMessages = new ArrayList<>();
        String emri = request.getParameter("emri");
        String mbiemri = request.getParameter("mbiemri");
        String gjinia = request.getParameter("gjinia");
        String dataLindjes = request.getParameter("dataLindjes");
        String adresa = request.getParameter("adresa");
        String email = request.getParameter("email");
        String numriTelefonit = request.getParameter("numriTelefonit");
        String pershkrimi = request.getParameter("pershkrimi");

        try {
            if (ValidationUtils.validNullEmpty(emri)) {
                errorMessages.add("Emri eshte i zbrazet");
            }

            if (ValidationUtils.validNullEmpty(mbiemri)) {
                errorMessages.add("Mbiemri eshte i zbrazet");
            }

            if (ValidationUtils.validNullEmpty(dataLindjes)) {
                errorMessages.add("Data e Lindjes eshte e zbrazet");
            }

            if (ValidationUtils.validNullEmpty(adresa)) {
                errorMessages.add("Adresa eshte e zbrazet");
            }

            if (ValidationUtils.validNullEmpty(pershkrimi)) {
                errorMessages.add("Pershkrimi eshte i zbrazet");
            }

            if (!errorMessages.isEmpty()) {
                throw new PacientiException("Forma te pa mbushura");
            }
        } catch (PacientiException ex) {
            request.setAttribute("errors", errorMessages);
            request.setAttribute("emri", emri);
            request.setAttribute("mbiemri", mbiemri);
            request.setAttribute("gjinia", gjinia);
            request.setAttribute("dataLindjes", dataLindjes);
            request.setAttribute("adresa", adresa);
            request.setAttribute("email", email);
            request.setAttribute("numriTelefonit", numriTelefonit);
            request.setAttribute("pershkrimi", pershkrimi);
            request.getRequestDispatcher(UrlUtils.SHTO_PACIENTIN_PAGE).forward(request, response);
        }

        try {
            String pacientiIdStr = request.getParameter("PacientiId");
            Integer pacientiId = Integer.parseInt(pacientiIdStr);
            String[] dobArray = dataLindjes.split("/");
            Integer dobDay = Integer.parseInt(dobArray[0]);
            Integer dobMonth = Integer.parseInt(dobArray[1]);
            Integer dobYear = Integer.parseInt(dobArray[2]);
            GregorianCalendar gc = new GregorianCalendar(dobYear, dobMonth, dobDay);
            Date dob = Date.from(gc.toInstant());
            Pacienti pacienti = pacientiEJB.gjejPacientin(pacientiId);
            pacienti.setEmri(emri);
            pacienti.setMbiemri(mbiemri);
            pacienti.setGjinia(gjinia.charAt(0));
            pacienti.setDataLindjes(dob);
            pacienti.setEmail(email);
            pacienti.setPershkrim(pershkrimi);
            pacienti.setNumriTelefonit(numriTelefonit);
            pacienti.setAdresa(adresa);
            pacientiEJB.editoPacientin(pacientiId, pacienti);
            request.setAttribute("success", true);
            request.setAttribute("pacienti", pacienti);
            request.getRequestDispatcher(UrlUtils.EDITO_PACIENIN_PAGE).forward(request, response);
        } catch (PacientiException ex) {
            errorMessages.add(ex.toString());
            request.setAttribute("errors", errorMessages);
            request.getRequestDispatcher(UrlUtils.EDITO_PACIENIN_PAGE).forward(request, response);
            Logger.getLogger(PacientiException.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fshijPacientin(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        Integer pacientiId = Integer.parseInt(request.getParameter("PacientiId"));
        try {
            if(pacientiEJB.fshijPacientin(pacientiId)) {
                request.setAttribute("successDelete", true);
            } else {
                request.setAttribute("successDelete", false);
            }
            listoPacientinForma(request, response);
        } catch (PacientiException ex) {
            request.setAttribute("successDelete", false);
            request.setAttribute("errorMsg", ex.toString());
            Logger.getLogger(Pacienti.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
