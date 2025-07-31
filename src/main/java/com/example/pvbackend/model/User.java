package com.example.pvbackend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    // ✅ Use ROLE_USER / ROLE_ADMIN
    private String role = "ROLE_USER";

    private boolean enabled = false;
}
