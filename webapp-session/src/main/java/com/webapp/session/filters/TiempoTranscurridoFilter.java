package com.webapp.session.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

@WebFilter("/*")
public class TiempoTranscurridoFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        long tiempoAntes = System.currentTimeMillis();
        filterChain.doFilter(servletRequest, servletResponse);
        long tiempoDespues = System.currentTimeMillis();
        long tiempoTranscurrido = tiempoDespues - tiempoAntes;
        servletRequest.getServletContext().log("Tiempo transcurrido: " + tiempoTranscurrido + " ms");
    }
}
