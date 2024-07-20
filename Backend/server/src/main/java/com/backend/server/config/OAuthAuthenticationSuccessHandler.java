package com.backend.server.config;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.backend.server.entities.Providers;
import com.backend.server.entities.User;
import com.backend.server.helper.AppConstants;
import com.backend.server.repositories.UserRepo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuthAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    
    private final UserRepo userRepo;

    private static final Logger logger = LoggerFactory.getLogger(OAuthAuthenticationSuccessHandler.class);

    public OAuthAuthenticationSuccessHandler(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        logger.info("OAuthAuthenticationSuccessHandler");
        
        // Saving data in the db
        DefaultOAuth2User user = (DefaultOAuth2User) authentication.getPrincipal();
        String email = user.getAttribute("email").toString();
        String name = user.getAttribute("name").toString();

        // Create user and save in db
        User user1 = new User();
        user1.setEmail(email);
        user1.setName(name);
        user1.setPassword("password");
        user1.setUserId(UUID.randomUUID().toString());
        user1.setProvider(Providers.GOOGLE);
        user1.setEnabled(true);
        user1.setProviderUserId(user.getName());
        user1.setRoleList(List.of(AppConstants.ROLE_USER));
       

        // Save to db
        User existingUser = userRepo.findByEmail(email).orElse(null);
        if (existingUser == null) {
            userRepo.save(user1);
            logger.info("User Saved: " + email);
        }

        new DefaultRedirectStrategy().sendRedirect(request, response, "/home");
    }
}
