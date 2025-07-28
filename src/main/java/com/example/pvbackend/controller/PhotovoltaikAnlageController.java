package com.example.pvbackend.controller;

import com.example.pvbackend.model.PhotovoltaikAnlage;
import com.example.pvbackend.service.PhotovoltaikAnlageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/anlagen")
@CrossOrigin(origins = "http://localhost:3000") // Allow React frontend
public class PhotovoltaikAnlageController {

    private final PhotovoltaikAnlageService service;

    public PhotovoltaikAnlageController(PhotovoltaikAnlageService service) {
        this.service = service;
    }

    // POST endpoint to create a new Anlage
    @PostMapping
    public PhotovoltaikAnlage create(@RequestBody PhotovoltaikAnlage anlage) {
        return service.save(anlage);
    }

    // GET endpoint to fetch all Anlagen
    @GetMapping
    public List<PhotovoltaikAnlage> getAll() {
        return service.findAll();
    }
}
