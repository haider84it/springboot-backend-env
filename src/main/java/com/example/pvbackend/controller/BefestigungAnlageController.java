package com.example.pvbackend.controller;


import com.example.pvbackend.model.BefestigungAnlage;
import com.example.pvbackend.model.PhotovoltaikAnlage;
import com.example.pvbackend.service.BefestigungAnlageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/befestigung")
@CrossOrigin(origins = {
        "https://envaris.cloudaxes.de",
        "https://wartung.envaris.de"
})
@RequiredArgsConstructor
public class BefestigungAnlageController {

    private final BefestigungAnlageService befestigungAnlageService;

    @GetMapping
    public List<BefestigungAnlage> getAllBefestigungen() {
        return befestigungAnlageService.getAllBefestigungen();
    }

    @GetMapping("/{id}")
    public Optional<BefestigungAnlage> getBefestigungAnlage(@PathVariable Long id) {
        return befestigungAnlageService.getBefestigungAnlageById(id);
    }

    @GetMapping("/anlage/{anlageId}")
    public BefestigungAnlage getBefestigungByAnlage(@PathVariable Long anlageId) {
        return befestigungAnlageService.getBefestigungByAnlage(anlageId);
    }

    @PostMapping
    public BefestigungAnlage createBefestigung(@RequestBody Map<String, Object> body) {

        List<String> arten = (List<String>) body.get("befestigungsarten");
        Map anlageMap = (Map) body.get("anlage");
        Long anlageId = Long.valueOf(anlageMap.get("id").toString());

        BefestigungAnlage b = new BefestigungAnlage();
        b.setAnlage(new PhotovoltaikAnlage(anlageId));

        b.setDachhacken(arten.contains("Dachhacken"));
        b.setStockschrauben(arten.contains("Stockschrauben"));
        b.setBallastierung(arten.contains("Ballastierung"));
        b.setAndere(arten.contains("andere"));

        return befestigungAnlageService.saveBefestigungAnlage(b);
    }

    @PutMapping("/{id}")
    public BefestigungAnlage updateBefestigungAnlage(@PathVariable Long id, @RequestBody BefestigungAnlage updateBefestigungAnlage) {
        updateBefestigungAnlage.setId(id);
        return befestigungAnlageService.saveBefestigungAnlage(updateBefestigungAnlage);
    }

    @DeleteMapping("/{id}")
    public void deleteBefestigungAnlage(@PathVariable Long id) {
        befestigungAnlageService.deleteBefestigungAnlage(id);
    }


}
