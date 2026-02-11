package com.els.educationloansystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

import com.els.educationloansystem.jwt.JWTAuthenticationEntryPoint;
import com.els.educationloansystem.jwt.JWTAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class StudentSecurity {

    @Autowired
    private JWTAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private CorsConfigurationSource corsConfigurationSource;

    /* PASSWORD ENCODER */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /* AUTH MANAGER */
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    /* SECURITY FILTER */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            /* CORS */
            .cors(cors -> cors.configurationSource(corsConfigurationSource))

            /* CSRF */
            .csrf(csrf -> csrf.disable())

            /* FRAME OPTIONS (🔥 REQUIRED FOR IFRAME) */
            .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))

            /* STATELESS */
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            /* AUTH ERROR HANDLER */
            .exceptionHandling(ex ->
                ex.authenticationEntryPoint(jwtAuthenticationEntryPoint)
            )

            /* AUTH RULES */
            .authorizeHttpRequests(auth -> auth

                /* PUBLIC */
                .requestMatchers(
                    "/api/auth/login",
                    "/api/auth/register",
                    "/api/admin/login"
                ).permitAll()

                /* 🔥 DOCUMENT PREVIEW (IFRAME SAFE) */
                .requestMatchers("/api/admin/viewdocuments/view/**").permitAll()

                /* ADMIN (SECURED) */
                .requestMatchers("/api/admin/**").hasRole("ADMIN")

                /* STUDENT */
                .requestMatchers("/api/student/**").hasRole("STUDENT")

                /* EVERYTHING ELSE */
                .anyRequest().authenticated()
            );

        /* JWT FILTER */
        http.addFilterBefore(
            jwtAuthenticationFilter,
            UsernamePasswordAuthenticationFilter.class
        );

        return http.build();
    }
}
