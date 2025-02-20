package com.webapp.listener.tarea7.listeners;

import jakarta.servlet.ServletContextListener;
import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class InfoPersonalListener implements ServletContextListener, ServletRequestListener {

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        sre.getServletContext().log("InfoPersonalListener requestInitialized");
        sre.getServletRequest().setAttribute("nombreCompleto ", "Wellington Romero");
        System.out.println("InfoPersonalListener requestInitialized xxx");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        sre.getServletContext().log("InfoPersonalListener requestDestroyed");
    }
}
