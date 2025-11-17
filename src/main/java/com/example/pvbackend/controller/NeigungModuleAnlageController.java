package com.example.pvbackend.controller;


import com.example.pvbackend.model.NeigungModuleAnlage;
import com.example.pvbackend.model.PhotovoltaikAnlage;
import com.example.pvbackend.service.NeigungModuleAnlageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/neigungmodule")
@CrossOrigin(origins = "https://envaris.cloudaxes.de")
@RequiredArgsConstructor
public class NeigungModuleAnlageController {

    private final NeigungModuleAnlageService neigungModuleAnlageService;

    @GetMapping
    public List<NeigungModuleAnlage> getAllNeigungModuleAnlage() {
        return neigungModuleAnlageService.getAllNeigungModule();
    }

    @GetMapping("/{id}")
    public Optional<NeigungModuleAnlage> getNeigungModuleAnlageById(@PathVariable Long id) {
        return neigungModuleAnlageService.getNeigungModuleById(id);
    }

    @GetMapping("/anlage/{anlageId}")
    public NeigungModuleAnlage getNeigungModulByAnlage(@PathVariable Long anlageId) {
        return neigungModuleAnlageService.getNeigungModuleByAnlage(anlageId);
    }

    @PostMapping
    public NeigungModuleAnlage createNeigungModuleAnlage(@RequestBody Map<String, Object> body) {

        BigDecimal neigung = BigDecimal.valueOf(Double.valueOf(body.get("neigung").toString()));

        Map anlageMap = (Map) body.get("anlage");
        Long anlageId = Long.valueOf(anlageMap.get("id").toString());

        NeigungModuleAnlage n = new NeigungModuleAnlage();
        n.setAnlage(new PhotovoltaikAnlage(anlageId));
        n.setNeigung(neigung);

        return neigungModuleAnlageService.saveNeigungModule(n);
    }

    @PutMapping("/{id}")
    public NeigungModuleAnlage updateNeigungModuleAnlage(@PathVariable Long id, @RequestBody NeigungModuleAnlage updateNeigungModuleAnlage) {
        updateNeigungModuleAnlage.setId(id);
        return neigungModuleAnlageService.saveNeigungModule(updateNeigungModuleAnlage);
    }

    @DeleteMapping("/{id}")
    public void deleteNeigungModuleAnlage(@PathVariable Long id) {
        neigungModuleAnlageService.deleteNeigungModule(id);
    }


}
