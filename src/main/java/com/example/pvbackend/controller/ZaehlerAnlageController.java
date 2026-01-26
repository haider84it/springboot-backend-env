package com.example.pvbackend.controller;



import com.example.pvbackend.model.PhotovoltaikAnlage;
import com.example.pvbackend.model.ZaehlerAnlage;
import com.example.pvbackend.service.ZaehlerAnlageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/zaehleranlage")
@CrossOrigin(origins = {
        "https://envaris.cloudaxes.de",
        "https://wartung.envaris.de"
})
@RequiredArgsConstructor
public class ZaehlerAnlageController {

    private final ZaehlerAnlageService zaehlerAnlageService;

    @GetMapping
    public List<ZaehlerAnlage> getAllZaehlerAnlage () {
        return zaehlerAnlageService.getAllZaehlerAnlage();
    }

    @GetMapping("/{id}")
    public Optional<ZaehlerAnlage> getZaehlerAnlageById(@PathVariable Long id) {
        return zaehlerAnlageService.getZaehlerAnlageById(id);
    }

    @GetMapping("/anlage/{anlageId}")
    public ZaehlerAnlage getZaehlerAnlageByAnlageId(@PathVariable Long anlageId) {
        return zaehlerAnlageService.getZaehlerByAnlage(anlageId);
    }


    @PostMapping
    public ZaehlerAnlage createZaehlerAnlage(@RequestBody Map<String, Object> body) {

        Map anlageMap = (Map) body.get("anlage");
        Long anlageId = Long.valueOf(anlageMap.get("id").toString());

        ZaehlerAnlage z = new ZaehlerAnlage();
        z.setAnlage(new PhotovoltaikAnlage(anlageId));

        z.setZaehlernummer(body.get("zaehlernummer").toString());

        if (body.get("wandlerfaktor") != null) {
            z.setWandlerFaktor(new BigDecimal(body.get("wandlerfaktor").toString()));
        }

        return zaehlerAnlageService.saveZaehlerAnlage(z);
    }

    @PutMapping("/{id}")
    public ZaehlerAnlage updateZaehlerAnlage(@PathVariable Long id, @RequestBody ZaehlerAnlage updateZaehlerAnlage) {
        updateZaehlerAnlage.setId(id);
        return zaehlerAnlageService.saveZaehlerAnlage(updateZaehlerAnlage);
    }


    @DeleteMapping("/{id}")
    public void deleteZaehlerAnlage(@PathVariable Long id) {
        zaehlerAnlageService.deleteZaehlerAnlage(id);
    }

}
