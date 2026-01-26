package com.example.pvbackend.controller;


import com.example.pvbackend.model.BeschwerungAnlage;
import com.example.pvbackend.model.PhotovoltaikAnlage;
import com.example.pvbackend.service.BeschwerungAnlageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/beschwerung")
@CrossOrigin(origins = {
        "https://envaris.cloudaxes.de",
        "https://wartung.envaris.de"
})
@RequiredArgsConstructor
public class BeschwerungAnlageController {

    private final BeschwerungAnlageService beschwerungAnlageService;

    @GetMapping
    public List<BeschwerungAnlage> getAllBeschwerungen() {
        return beschwerungAnlageService.getAllBeschwerungen();
    }

    @GetMapping("/{id}")
    public Optional<BeschwerungAnlage> getBeschwerungAnlage(@PathVariable Long id) {
        return beschwerungAnlageService.getBeschwerungById(id);
    }

    @GetMapping("/anlage/{anlageId}")
    public BeschwerungAnlage getBeschwerungByAnlage(@PathVariable Long anlageId) {
        return beschwerungAnlageService.getBeschwerungByAnlage(anlageId);
    }

    @PostMapping
    public BeschwerungAnlage createBeschwerung(@RequestBody Map<String, Object> body) {

        List<String> arten = (List<String>) body.get("beschwerungsarten");
        Map anlageMap = (Map) body.get("anlage");
        Long anlageId = Long.valueOf(anlageMap.get("id").toString());

        BeschwerungAnlage b = new BeschwerungAnlage();
        b.setAnlage(new PhotovoltaikAnlage(anlageId));

        b.setWannen(arten.contains("Wannen"));
        b.setSteine(arten.contains("Steine"));
        b.setKies(arten.contains("Kies"));
        b.setAndere(arten.contains("andere"));

        return beschwerungAnlageService.saveBeschwerung(b);
    }

    @PutMapping("/{id}")
    public BeschwerungAnlage updateBeschwerung(@PathVariable Long id, @RequestBody BeschwerungAnlage updateBeschwerungAnlage) {
        updateBeschwerungAnlage.setId(id);
        return beschwerungAnlageService.saveBeschwerung(updateBeschwerungAnlage);
    }

    @DeleteMapping("/{id}")
    public void deleteBeschwerungAnlage(@PathVariable Long id) {
        beschwerungAnlageService.deleteBeschwerungAnlage(id);
    }

}
