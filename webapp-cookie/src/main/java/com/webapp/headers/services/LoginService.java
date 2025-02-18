package com.webapp.headers.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Cookie;

import java.util.Optional;

public interface LoginService {
    Optional<Cookie> getCookie(HttpServletRequest req);
}
