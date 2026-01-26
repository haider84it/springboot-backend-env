package com.example.pvbackend.controller;

import com.example.pvbackend.model.AufstellungsortAnlage;
import com.example.pvbackend.model.PhotovoltaikAnlage;
import com.example.pvbackend.service.AufstellungsortService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/aufstellungsort")
@CrossOrigin(origins = {
        "https://envaris.cloudaxes.de",
        "https://wartung.envaris.de"
})
@RequiredArgsConstructor
public class AufstellungsortController {

    private final AufstellungsortService aufstellungsortService;


    @GetMapping
    public List<AufstellungsortAnlage> getAllAufstellungsorte() {
        return aufstellungsortService.getAllAufstellungsorte();
    }

    @GetMapping("/{id}")
    public Optional<AufstellungsortAnlage> getAufstellungsortById(@PathVariable Long id) {
        return aufstellungsortService.getAufstellungsortById(id);
    }


    @GetMapping("/anlage/{analgeId}")
    public AufstellungsortAnlage getAufstellungsortByAnlage(@PathVariable Long anlageId) {
       return aufstellungsortService.getAufstellungsortByAnlage(anlageId);
    }

    @PostMapping
    public AufstellungsortAnlage createAufstellungsort(@RequestBody Map<String, Object> body) {

        List<String> orte = (List<String>) body.get("orte");
        Map anlageMap = (Map) body.get("anlage");
        Long anlageId = Long.valueOf(anlageMap.get("id").toString());

        AufstellungsortAnlage ort = new AufstellungsortAnlage();
        ort.setAnlage(new PhotovoltaikAnlage(anlageId));

        ort.setWohngebaeude(orte.contains("Wohngebäude"));
        ort.setGarage(orte.contains("Garage"));
        ort.setLandwirtschaftsgebaeude(orte.contains("landw. Gebäude"));
        ort.setGewerblicheHalle(orte.contains("gewerbl. Halle"));
        ort.setFreiland(orte.contains("Freiland"));
        ort.setAndere(orte.contains("andere"));

        return aufstellungsortService.saveAufstellungsort(ort);
    }

    @PutMapping("/{id}")
    public AufstellungsortAnlage updateAufstellungsort(@PathVariable Long id, @RequestBody AufstellungsortAnlage updateAufstellungsort) {
        updateAufstellungsort.setId(id);
        return aufstellungsortService.saveAufstellungsort(updateAufstellungsort);
    }



    @DeleteMapping("/{id}")
    public void deleteAufstellungsort(@PathVariable Long id) {
        aufstellungsortService.deleteAufstellungsort(id);
    }


}
