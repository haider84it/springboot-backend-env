package com.example.pvbackend.controller;

import com.example.pvbackend.model.User;
import com.example.pvbackend.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;   // ⬅️ inject

    public UserController(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    // ✅ Get all users (Admin Dashboard)
    @GetMapping
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    // ✅ Approve user
    @PatchMapping("/approve/{id}")
    public ResponseEntity<String> approveUser(@PathVariable Long id) {
        return userRepo.findById(id)
                .map(user -> {
                    user.setEnabled(true);
                    userRepo.save(user);
                    return ResponseEntity.ok("User approved!");
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Reject user (disable or delete)
    @PatchMapping("/reject/{id}")
    public ResponseEntity<String> rejectUser(@PathVariable Long id) {
        return userRepo.findById(id)
                .map(user -> {
                    user.setEnabled(false);
                    userRepo.delete(user); // or just mark as rejected
                    return ResponseEntity.ok("User rejected and removed!");
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Block user (disable but keep in DB)
    @PatchMapping("/block/{id}")
    public ResponseEntity<String> blockUser(@PathVariable Long id) {
        return userRepo.findById(id)
                .map(user -> {
                    user.setEnabled(false);
                    userRepo.save(user);
                    return ResponseEntity.ok("User blocked!");
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Make admin (Only haider84it@gmail.com)
    @PatchMapping("/make-admin/{id}")
    public ResponseEntity<String> makeAdmin(@PathVariable Long id, Authentication auth) {
        String currentEmail = auth.getName();

        return userRepo.findById(id).map(user -> {

            // ✅ Get current admin info
            User currentAdmin = userRepo.findByEmail(currentEmail).orElse(null);
            if (currentAdmin == null || !currentAdmin.getRole().equals("ROLE_ADMIN")) {
                return ResponseEntity.status(403).body("❌ You are not allowed to promote users to admin");
            }

            // ✅ Only GLOBAL admin can create GLOBAL or HIGH admins
            if ("GLOBAL".equals(currentAdmin.getAdminLevel())) {
                user.setRole("ROLE_ADMIN");
                user.setAdminLevel("NORMAL"); // Or set to HIGH/GLOBAL manually if needed
                user.setEnabled(true);
                userRepo.save(user);
                return ResponseEntity.ok("✅ User promoted to ADMIN by GLOBAL ADMIN!");
            }

            // ✅ High admin can only create normal admins
            if ("HIGH".equals(currentAdmin.getAdminLevel())) {
                user.setRole("ROLE_ADMIN");
                user.setAdminLevel("NORMAL");
                user.setEnabled(true);
                userRepo.save(user);
                return ResponseEntity.ok("✅ User promoted to NORMAL ADMIN by HIGH ADMIN!");
            }

            return ResponseEntity.status(403).body("❌ You are not allowed to promote users to admin");

        }).orElse(ResponseEntity.notFound().build());
    }

    // ↓ add DTO (or put in its own file)
    public record ChangePasswordRequest(String currentPassword, String newPassword, String confirmPassword) {}

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest req, Authentication auth) {
        User user = userRepo.findByEmail(auth.getName()).orElse(null);
        if (user == null) return ResponseEntity.status(401).build();
        if (!passwordEncoder.matches(req.currentPassword(), user.getPassword()))
            return ResponseEntity.badRequest().body("Current password incorrect");
        if (!req.newPassword().equals(req.confirmPassword()))
            return ResponseEntity.badRequest().body("Passwords do not match");

        user.setPassword(passwordEncoder.encode(req.newPassword())); // hash!
        userRepo.save(user);
        return ResponseEntity.ok("Password updated");
    }
}

