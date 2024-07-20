package com.backend.server.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CustomJsonAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public CustomJsonAuthenticationFilter() {
        super(new AntPathRequestMatcher("/authenticate", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) 
            throws AuthenticationException, IOException, ServletException {
        Map<String, String> loginData = new HashMap<>();

        String contentType = request.getContentType();
        if (contentType != null && contentType.contains("application/json")) {
            // Handle JSON request
            loginData = objectMapper.readValue(request.getInputStream(), HashMap.class);
        } else if (contentType != null && contentType.contains("application/x-www-form-urlencoded")) {
            // Handle form URL encoded request
            Map<String, String[]> parameterMap = request.getParameterMap();
            for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
                loginData.put(entry.getKey(), entry.getValue()[0]);
            }
        } else {
            throw new ServletException("Unsupported content type: " + contentType);
        }

        String username = loginData.get("phoneNumber");
        String password = loginData.get("password");

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, 
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");

        Map<String, String> responseData = new HashMap<>();
        responseData.put("message", "User signed in successfully");
        responseData.put("username", authResult.getName());

        response.getWriter().write(objectMapper.writeValueAsString(responseData));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, 
                                              AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        Map<String, String> responseData = new HashMap<>();
        responseData.put("message", "Authentication failed");
        responseData.put("error", failed.getMessage());

        response.getWriter().write(objectMapper.writeValueAsString(responseData));
    }

    @Override
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }
}
