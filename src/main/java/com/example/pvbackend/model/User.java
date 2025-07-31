package com.example.pvbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    private String role; // ROLE_ADMIN or ROLE_USER

    private boolean enabled = false;

    // ✅ New field to differentiate admin levels
    private String adminLevel = "NORMAL"; // GLOBAL, HIGH, NORMAL
}