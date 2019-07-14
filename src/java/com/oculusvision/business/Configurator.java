/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oculusvision.business;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Configurator implements ServletContextListener
{
    @Override
    public void contextInitialized(ServletContextEvent event)
    {
//        ServletContext context = event.getServletContext();
//
//        FilterRegistration.Dynamic registration = context.addFilter(
//                "authenticationFilter", new AuthenticationFilter()
//        );
//        registration.setAsyncSupported(true);
//        registration.addMappingForUrlPatterns(
//                null, false, "/dashboard", "/mjeku","/tung"
//        );
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) { }
}
