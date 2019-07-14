/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oculusvision.business.raportet;

import com.oculusvision.business.entities.Termini;
import com.oculusvision.business.terminet.TerminetEJBLocal;
import com.oculusvision.business.terminet.TerminetException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author Granit Krasniqi
 */
@WebServlet(name = "TerminetSotmetReportServlet", urlPatterns = {"/TerminetSotmetReportServlet"})
public class TerminetSotmetReportServlet extends HttpServlet {
    
    @Inject
    private TerminetEJBLocal terminetEJB;
    
    private JRDataSource createReportDataSource() throws TerminetException {
        JRBeanCollectionDataSource dataSource;
        Collection reportRows = initializeBeanCollection();
        dataSource = new JRBeanCollectionDataSource(reportRows);
        return dataSource;
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
        ServletOutputStream servletOutputStream = 
                response.getOutputStream();
        InputStream reportStream = getServletConfig().getServletContext()
                .getResourceAsStream("/WEB-INF/reports/terminet.jasper");
        try {
            JRDataSource dataSource = createReportDataSource();
            JasperRunManager.runReportToPdfStream(reportStream, servletOutputStream,
                    new HashMap(), dataSource);
            response.setContentType("application/pdf");
            servletOutputStream.flush();
            servletOutputStream.close();
            
        } catch (Exception e) {
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);
            response.setContentType("text/plain");
            response.getOutputStream().print(stringWriter.toString());
        }
    }



    private Collection initializeBeanCollection() throws TerminetException {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        List<Termini> terminet = terminetEJB.ktheTerminetSotme();
        ArrayList reportRows = new ArrayList();
        terminet.forEach((t) -> {
            reportRows.add(new TerminiData(t.getPacientiID().toString(),
                    t.getStafiID().toString(), sdf.format(t.getKohaPlanifikuarF()), sdf.format(t.getKohaPlanifikuarM())));
        });
        return reportRows;
    }

}
