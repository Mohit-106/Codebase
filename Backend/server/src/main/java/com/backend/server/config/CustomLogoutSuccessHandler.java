package com.backend.server.config;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import org.springframework.http.MediaType;

@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        // Check the Content-Type header of the request
        String contentTypeHeader = request.getHeader("Content-Type");

        if (contentTypeHeader != null && contentTypeHeader.equals(MediaType.APPLICATION_JSON_VALUE)) {
            // Respond with JSON if the Content-Type is application/json
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            
            PrintWriter writer = response.getWriter();
            writer.write("{\"message\":\"Logout successful\"}");
            writer.flush();
            
        } else {
            // Otherwise, redirect to a different URL
            response.sendRedirect("/login?logout=true");
        }
    }
}
