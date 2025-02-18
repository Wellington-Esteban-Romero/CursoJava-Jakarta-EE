package com.webapp.headers.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/cabeceras-request")
public class CabeceraHttpRequestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        String metodoHttp =req.getMethod();
        String uri = req.getRequestURI();
        String requestUrl = req.getRequestURL().toString();
        String contextPath = req.getContextPath();
        String servletPath = req.getServletPath();
        int ipCliente = req.getRemotePort();//ip del cliente
        String serverName = req.getServerName();//Nombre del servidor que está enviando el request como por ejemplo un servidor en node.js
        String ip = req.getRemoteAddr(); // ip de tomcat de nuestro servidor
        int port = req.getLocalPort();
        String scheme = req.getScheme();
        String host = req.getHeader("Host");
        String url1 = scheme + "://" + host + contextPath + servletPath;
        String url2 = scheme + "://" + ip + ":" + port + contextPath + servletPath;

        try (PrintWriter out = resp.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("   <head>");
            out.println("       <meta charset=\"UTF-8\">");
            out.println("       <title>Cabeceras HTTP Request</title>");
            out.println("   </head>");
            out.println("   <body>");
            out.println("       <h1>Cabeceras HTTP Request!</h1>");
            out.println("       <ul>");
            out.println("           <li>Método HTTP: " + metodoHttp + "</li>");
            out.println("           <li>Request URI: " + uri + "</li>");
            out.println("           <li>Request URL: " + requestUrl + "</li>");
            out.println("           <li>Context Path:" + contextPath + "</li>");
            out.println("           <li>Servlet Path: " + servletPath + "</li>");
            out.println("           <li>Server Name: " + serverName + "</li>");
            out.println("           <li>Ip Cliente: " + ipCliente + "</li>");
            out.println("           <li>Ip local: " + ip + "</li>");
            out.println("           <li>Puerto local: " + port + "</li>");
            out.println("           <li>Esquema: " + scheme + "</li>");
            out.println("           <li>Host: " + host + "</li>");
            out.println("           <li>URL1: " + url1 + "</li>");
            out.println("           <li>URL2: " + url2 + "</li>");
            Enumeration<String> headerNames =  req.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                String headerValue = req.getHeader(headerName);
                out.println("           <li>" + headerName + ": " + headerValue + "</li>");
            }
            out.println("       </ul>");
            out.println("   </body>");
            out.println("</html>");
        }
    }
}
