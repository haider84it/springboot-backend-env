package com.example.pvbackend.controller;



import com.example.pvbackend.model.DatenloggerAnlage;
import com.example.pvbackend.service.DatenloggerAnlageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/datenlogger")
@CrossOrigin(origins = {
        "https://envaris.cloudaxes.de",
        "https://wartung.envaris.de"
})
public class DatenloggerAnlageController {

   private final DatenloggerAnlageService datenloggerAnlageService;


   public DatenloggerAnlageController(DatenloggerAnlageService datenloggerAnlageService) {
       this.datenloggerAnlageService = datenloggerAnlageService;
   }


   @GetMapping
    public List<DatenloggerAnlage> getAllDatenloggerAnlage() {
       return datenloggerAnlageService.getAllDatenlogger();
   }



   @GetMapping({"/{id}"})
    public Optional<DatenloggerAnlage> getDatenloggereById(@PathVariable Long id) {
                return datenloggerAnlageService.getDatenloggerById(id);
   }

   @GetMapping("/anlage/{anlageId}")
   public List<DatenloggerAnlage> getDatenloggerByAnlage(@PathVariable Long anlageId) {
       return datenloggerAnlageService.getDatenloggerByAnlage(anlageId);
   }


   @PostMapping
   public DatenloggerAnlage createDatenlogger(@RequestBody DatenloggerAnlage datenloggerAnlage) {
        return datenloggerAnlageService.saveDatenlogger(datenloggerAnlage);
   }


   @PutMapping("/{id}")
   public DatenloggerAnlage updateDatenlogger(@PathVariable Long id, @RequestBody DatenloggerAnlage updateDatenlogger) {
       updateDatenlogger.setId(id);
       return datenloggerAnlageService.saveDatenlogger(updateDatenlogger);
   }

   @DeleteMapping("/{id}")
    public void deleteDatenlogger(@PathVariable Long id) {
       datenloggerAnlageService.deleteDatenlogger(id);
   }


}
