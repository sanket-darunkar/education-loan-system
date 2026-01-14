package com.els.educationloansystem.jwt;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;
    
    /**
     * 🔥 VERY IMPORTANT
     * Public endpoints ke liye JWT filter skip karo
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();

        return path.equals("/api/auth/register")
                || path.equals("/api/auth/login")
                || path.equals("/api/admin/login")
                || "OPTIONS".equalsIgnoreCase(request.getMethod());
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {

            String token = header.substring(7);
            // token expired → just continue (unauthenticated)
            if (jwtUtil.isTokenExpired(token)) {
                filterChain.doFilter(request, response);
                return;
            }

            String email = jwtUtil.extractUsername(token);
            String role = jwtUtil.extractRole(token);
            
            if (email != null && role != null &&
                    SecurityContextHolder.getContext().getAuthentication() == null) {
            	 List<SimpleGrantedAuthority> authorities =
                         List.of(new SimpleGrantedAuthority("ROLE_" + role));
            	 
            	 UsernamePasswordAuthenticationToken authentication =
                         new UsernamePasswordAuthenticationToken(
                                 email,
                                 null,
                                 authorities
                         );
            	 
            	 authentication.setDetails(
                         new WebAuthenticationDetailsSource()
                                 .buildDetails(request));
            
            	  SecurityContextHolder.getContext()
                  .setAuthentication(authentication);
      }
  }

  filterChain.doFilter(request, response);
}
}