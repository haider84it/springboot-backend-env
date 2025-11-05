package com.example.pvbackend.controller;


import com.example.pvbackend.model.MobilefunkRouterAnlage;
import com.example.pvbackend.service.MobilefunkRouterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mobilfunkrouter")
@CrossOrigin(origins = "https://envaris.cloudaxes.de")
public class MobilefunkRouterAnlageController {


    private final MobilefunkRouterService mobilefunkRouterService;

    public MobilefunkRouterAnlageController(MobilefunkRouterService mobilefunkRouterService) {
        this.mobilefunkRouterService = mobilefunkRouterService;
    }


    @GetMapping
    public List<MobilefunkRouterAnlage> getAllMobilefunkRouter() {
        return mobilefunkRouterService.getAllMobilefunkRouter();
    }

    @GetMapping("/{id}")
    public Optional<MobilefunkRouterAnlage> getMobilefunkRouterById(@PathVariable Long id) {
        return mobilefunkRouterService.getMobilefunkRouterById(id);
    }

    @GetMapping("/anlage/{anlageId}")
    public MobilefunkRouterAnlage getMobilefunkRouterByAnlage(@PathVariable Long anlageId) {
        return mobilefunkRouterService.getMobilefunkRouterByAnlage(anlageId);
    }

    @PostMapping
    public MobilefunkRouterAnlage createMobilefunkRouter(@RequestBody MobilefunkRouterAnlage mobilefunkRouterAnlage) {
        return mobilefunkRouterService.saveMobilefunkRouter(mobilefunkRouterAnlage);
    }

    @PutMapping("/{id}")
    public MobilefunkRouterAnlage updateMobilefunkRouter(@PathVariable Long id, @RequestBody MobilefunkRouterAnlage updateMobilefunkRouter) {
        updateMobilefunkRouter.setId(id);
        return mobilefunkRouterService.saveMobilefunkRouter(updateMobilefunkRouter);
    }


    @DeleteMapping("/{id}")
    public void deleteMobilefunkRouter(@PathVariable Long id) {
        mobilefunkRouterService.deleteMobilefunkRouter(id);
    }


}
