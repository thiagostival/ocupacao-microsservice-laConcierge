package com.laconcierge.ocupacao.auth;

import static com.laconcierge.ocupacao.util.JWTUtil.getJWTFromRequest;
import static com.laconcierge.ocupacao.util.JWTUtil.getSubjectFromToken;
import static com.laconcierge.ocupacao.util.JWTUtil.isTokenOnHeader;
import static com.laconcierge.ocupacao.util.JWTUtil.isTokenValid;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.laconcierge.ocupacao.user.entity.User;
import com.laconcierge.ocupacao.user.service.UserService;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    private final UserService userService;

    public JWTAuthorizationFilter(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        if (!isTokenOnHeader(req)) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        String token = getJWTFromRequest(req);

        if (!isTokenValid(token)) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        UUID subject = getSubjectFromToken(token);
        if (subject == null) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        Optional<User> user = userService.findById(subject);

        if (user.isEmpty()) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        chain.doFilter(req, res);
    }


}