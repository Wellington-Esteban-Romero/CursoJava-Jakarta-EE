package com.webapp.session.filters;

import com.webapp.session.services.LoginService;
import com.webapp.session.services.LoginServiceSessionImpl;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebFilter({"/carro/*"}) // cualquier ruta que empieza con carro con el patron * (carro/ver o carro/actualizar) solo funcionario de esta manera
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws
            IOException, ServletException {
        LoginService loginService = new LoginServiceSessionImpl();
        Optional<String> username = loginService.getUsername((HttpServletRequest) servletRequest);
        if (username.isPresent()) {
            filterChain.doFilter(servletRequest, servletResponse); //dependiendo del servlet ejecutado, aquí se ejecuta
        } else {
            ((HttpServletResponse)servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED,
                    "No está autorizado acceder a está página");
        }
    }
}
