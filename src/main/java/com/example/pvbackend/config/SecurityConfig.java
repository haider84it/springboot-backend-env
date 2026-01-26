package com.example.pvbackend.config;

import com.example.pvbackend.security.JwtAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    @Autowired
    private CustomUserDetailsService userDetailsService;



    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder auth = http.getSharedObject(AuthenticationManagerBuilder.class);
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        return auth.build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOriginPatterns(List.of(
                            "https://envaris.cloudaxes.de",
                            "https://wartung.envaris.de",
                            "https://*.sslip.io"
                    ));
                    config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                    config.setAllowedHeaders(List.of("Authorization", "Content-Type", "*"));
                    config.setExposedHeaders(List.of("Authorization"));
                    config.setAllowCredentials(true);
                    return config;
                }))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers("/auth/**").permitAll()



                        // allow create protocol
                        .requestMatchers(HttpMethod.POST, "/api/wartungsprotokoll/**").authenticated()

                        // allow upload images
                        .requestMatchers(HttpMethod.POST, "/api/wartungsprotokoll/*/bilder").authenticated()

                        // allow viewing
                        .requestMatchers(HttpMethod.GET, "/api/wartungsprotokoll/**").authenticated()



                        // ✅ Admin-only for create/update/delete Anlagen
                        .requestMatchers(HttpMethod.POST, "/api/anlagen/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/anlagen/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/anlagen/**").hasRole("ADMIN")

                        // ✅ Users can only view
                        .requestMatchers(HttpMethod.GET, "/api/anlagen/**").authenticated()

                        // ✅ Allow logged-in users to change their password
                        .requestMatchers("/api/users/change-password").authenticated()

                        // ✅ Admin Dashboard APIs
                        .requestMatchers("/api/users/**").hasRole("ADMIN")


                        // ⬇️ ADD THIS LINE
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        System.out.println("✅ SecurityFilterChain configured successfully");
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}