package com.oculusvision.business;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class LoginServlet extends HttpServlet
{
//    private static final Map<String, String> userDatabase = new Hashtable<>();
//    
//    static {
//        userDatabase.put("granit", "granit");
//        userDatabase.put("Nicholas", "password");
//        userDatabase.put("admin", "admin");
//        userDatabase.put("Sarah", "drowssap");
//        userDatabase.put("Mike", "wordpass");
//        userDatabase.put("John", "green");
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
//            throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        if (session.getAttribute("username") != null) {
//            response.sendRedirect("dashboard");
//            return;
//        }
//        
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        if (username == null || password == null ||
//                !LoginServlet.userDatabase.containsKey(username) ||
//                !password.equals(LoginServlet.userDatabase.get(username))) {
//            request.setAttribute("loginFailed", true);
//            request.getRequestDispatcher("/WEB-INF/jsp/view/login.jsp")
//                    .forward(request, response);
//        } else {
//            session.setAttribute("username", username);
//            request.changeSessionId();
//            response.sendRedirect("dashboard");
//        }
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        if (request.getParameter("logout") != null) {
//            session.invalidate();
//            response.sendRedirect("login");
//            return;
//        }else if (session.getAttribute("username") != null) {
//            response.sendRedirect("dashboard");
//            return;
//        }
//        
//        
//        
//        request.setAttribute("loginFailed", false);
//        request.getRequestDispatcher("/WEB-INF/jsp/view/login.jsp")
//                .forward(request, response);
//    }
    
    
}
