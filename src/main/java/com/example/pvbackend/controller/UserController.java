package com.example.pvbackend.controller;

import com.example.pvbackend.model.User;
import com.example.pvbackend.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserRepository userRepo;

    public UserController(UserRepository userRepo) {
        this.userRepo = userRepo;
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

    // ✅ Make admin (Only haider84it@gmail.com & myfriend@gmail.com can do this)
    @PatchMapping("/make-admin/{id}")
    public ResponseEntity<String> makeAdmin(@PathVariable Long id, Authentication auth) {
        String currentEmail = auth.getName();

        // ✅ Only global admins can promote users to admin
        if (!currentEmail.equals("haider84it@gmail.com") &&
                !currentEmail.equals("myfriend@gmail.com")) {
            return ResponseEntity.status(403).body("❌ You are not allowed to promote users to admin");
        }

        return userRepo.findById(id)
                .map(user -> {
                    user.setRole("ROLE_ADMIN");
                    user.setEnabled(true);
                    userRepo.save(user);
                    return ResponseEntity.ok("✅ User promoted to admin!");
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
