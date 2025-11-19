package com.example.pvbackend.controller;

import com.example.pvbackend.model.Wartungsprotokoll;
import com.example.pvbackend.service.WartungsprotokollService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/wartungsprotokoll")
@RequiredArgsConstructor
public class WartungsprotokollController {

    private final WartungsprotokollService service;

    @PostMapping
    public Wartungsprotokoll create(@RequestBody Wartungsprotokoll protokoll) {
        return service.save(protokoll);
    }

    @GetMapping("/{id}")
    public Wartungsprotokoll getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    // ⭐ IMAGE UPLOAD
    @PostMapping("/{id}/bilder")
    public void uploadImage(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file
    ) {
        service.saveImage(id, file);
    }
}