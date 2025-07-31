package com.example.pvbackend.config;

import com.example.pvbackend.security.JwtAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()

                        // ✅ Admin-only for create/update/delete Anlagen
                        .requestMatchers(HttpMethod.POST, "/api/anlagen/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/anlagen/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/anlagen/**").hasRole("ADMIN")

                        // ✅ Users can only view
                        .requestMatchers(HttpMethod.GET, "/api/anlagen/**").authenticated()

                        // ✅ Admin Dashboard APIs
                        .requestMatchers("/api/users/**").hasRole("ADMIN")
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}