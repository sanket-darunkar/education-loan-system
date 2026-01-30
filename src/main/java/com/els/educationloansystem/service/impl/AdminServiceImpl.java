package com.els.educationloansystem.service.impl;

import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.els.educationloansystem.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

    // 🔐 HARD-CODED ADMIN CREDENTIALS
    private static final String ADMIN_EMAIL = "admin@eduloan.com";
    private static final String ADMIN_PASSWORD = "admin123";

    @Override
    public Authentication authenticate(String email, String password) {

        if (!ADMIN_EMAIL.equals(email) || !ADMIN_PASSWORD.equals(password)) {
            throw new RuntimeException("Invalid admin credentials");
        }

        return new UsernamePasswordAuthenticationToken(
                email,
                null,
                List.of(new SimpleGrantedAuthority("ROLE_ADMIN"))
        );
    }
}
