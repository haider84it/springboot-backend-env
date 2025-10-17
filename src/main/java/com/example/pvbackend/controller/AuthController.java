package com.example.pvbackend.controller;

import com.example.pvbackend.model.User;
import com.example.pvbackend.repository.UserRepository;
import com.example.pvbackend.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository userRepo, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        String email = jwtUtil.extractUsername(token);
        return userRepo.findByEmail(email)
                .map(u -> ResponseEntity.ok(Map.of(
                        "email", u.getEmail(),
                        "role", u.getRole(),
                        "adminLevel", u.getAdminLevel()
                )))
                .orElse(ResponseEntity.status(404).body(Map.of("error", "User not found")));
    }


    // ✅ Register a new user (disabled by default until admin approves)
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> req) {
        String email = req.get("email");
        String password = req.get("password");

        if (userRepo.findByEmail(email).isPresent()) {
            return ResponseEntity.badRequest().body(Map.of("error", "User already exists"));
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole("ROLE_USER"); // default role
        user.setEnabled(false);    // require admin approval

        userRepo.save(user);

        return ResponseEntity.ok(Map.of("message", "Registration successful. Await admin approval."));
    }

    // ✅ Login and return JWT token
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> req) {
        String email = req.get("email");
        String password = req.get("password");

        return userRepo.findByEmail(email)
                .filter(u -> passwordEncoder.matches(password, u.getPassword()))
                .filter(u -> u.isEnabled() || u.getRole().equals("ROLE_ADMIN"))
                .map(u -> {
                    String token = jwtUtil.generateToken(u.getEmail(), u.getRole());
                    return ResponseEntity.ok(Map.of(
                            "token", token,
                            "email", u.getEmail(),
                            "role", u.getRole()
                    ));
                })
                .orElse(ResponseEntity.status(401).body(Map.of(
                        "error", "Invalid credentials or account not approved"
                )));
    }
}