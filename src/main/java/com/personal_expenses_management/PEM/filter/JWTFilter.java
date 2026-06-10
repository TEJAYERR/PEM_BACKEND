package com.personal_expenses_management.PEM.filter;

import com.personal_expenses_management.PEM.service.JWTService;
import com.personal_expenses_management.PEM.service.MyUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NullMarked;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Set;

@Component
public class JWTFilter extends OncePerRequestFilter {


    private final JWTService jwtService;

    private final MyUserDetailsService myUserDetailsService;

    @Autowired
    public JWTFilter(JWTService jwtService, MyUserDetailsService myUserDetailsService) {

        this.jwtService = jwtService;
        this.myUserDetailsService = myUserDetailsService;
    }


    @Override
    @NullMarked
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        String token = "";

        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }

        if (request.getMethod().equals("OPTIONS")) {
            filterChain.doFilter(request, response);
            return;
        }

        token = authHeader.substring(7);

        try {

            String email = jwtService.extractEmail(token);

            if (email == null) {
                throw new RuntimeException("misformed token");
            }

            if (SecurityContextHolder.getContext().getAuthentication() != null) {
                filterChain.doFilter(request, response);
                return;
            }

            UserDetails userDetails = myUserDetailsService.loadUserByUsername(email);

            if (!jwtService.validateToken(token, userDetails)) {
                throw new RuntimeException("token is not valid");
            }

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails,
                            null,
                            userDetails.getAuthorities());

            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        } catch (Exception e) {
            System.out.println("Cannot set authentication = " + e.getMessage());
        }

        filterChain.doFilter(request, response);
    }
}
