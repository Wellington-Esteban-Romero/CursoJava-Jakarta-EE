package com.webapp.session.listeners;

import com.webapp.session.models.Carro;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class AplicacionListener implements ServletContextListener,
        ServletRequestListener, HttpSessionListener {

    private ServletContext servletContext;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().log("Iniciando contexto de la aplicacion");
        servletContext = sce.getServletContext();
        servletContext.setAttribute("mensaje", "Valor global de la aplicación");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        servletContext.log("Finalizando contexto de la aplicacion");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        servletContext.log("Iniciando request");
        sre.getServletRequest().setAttribute("mensaje", "Guardar el mensaje en la request");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        servletContext.log("Finalizando request");
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) { // cuando iniciamos session
        servletContext.log("Iniciando session");
        Carro carro = new Carro();
        HttpSession session = se.getSession();
        session.setAttribute("carro", carro);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) { // cuando hacemos logout
        servletContext.log("Finalizando session");
    }
}
