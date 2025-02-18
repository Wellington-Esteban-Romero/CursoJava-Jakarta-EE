package com.webapp.headers.services;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.Optional;

public class LoginServiceImpl implements LoginService {
    @Override
    public Optional<Cookie> getCookie(HttpServletRequest req) {
        Cookie[] cookies =  req.getCookies() != null ? req.getCookies() : new Cookie[0];

        return Arrays.stream(cookies)
                .filter(c -> c.getName().equals("username"))
                .findFirst();
    }
}
