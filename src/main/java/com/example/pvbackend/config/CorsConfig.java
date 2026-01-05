package com.example.pvbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // Allow API requests
                registry.addMapping("/api/**")
                        .allowedOriginPatterns("https://envaris.cloudaxes.de",
                                "https://*.sslip.io")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowCredentials(true)
                        .allowedHeaders("*");

                // Allow Auth (login/register) requests
                registry.addMapping("/auth/**")
                        .allowedOriginPatterns("https://envaris.cloudaxes.de",
                                "https://i0ko848g8wwgws400884sg4c.168.119.177.216.sslip.io").allowedMethods("POST", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}
