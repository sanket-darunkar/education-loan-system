
package com.els.educationloansystem.service;

import org.springframework.security.core.Authentication;

public interface AdminService {
    Authentication authenticate(String email, String password);
}
