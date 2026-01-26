package com.example.pvbackend.controller;

import com.example.pvbackend.model.Wartungsprotokoll;
import com.example.pvbackend.pdf.PdfService;
import com.example.pvbackend.service.WartungsprotokollService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/wartungsprotokoll")
@CrossOrigin(origins = {
        "https://envaris.cloudaxes.de",
        "https://wartung.envaris.de"
})
public class WartungsprotokollController {

    private final WartungsprotokollService service;
    private final PdfService pdfService;


    public WartungsprotokollController(WartungsprotokollService service, PdfService pdfService) {
        this.service = service;
        this.pdfService = pdfService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Map<String, Long>> create(@RequestBody Wartungsprotokoll protokoll) {
        Wartungsprotokoll saved = service.saveProtokoll(protokoll);
        return ResponseEntity.ok(Map.of("id", saved.getId()));
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<Wartungsprotokoll>> getAll() {
        List<Wartungsprotokoll> all = service.findAll();

        // DEBUG:
        System.out.println("DEBUG: Returning protocols:");
        all.forEach(p -> {
            System.out.println("ID: " + p.getId());
            if (p.getSeite1() != null) {
                System.out.println("  anlagenbezeichnung = " + p.getSeite1().getAnlagenbezeichnung());
            } else {
                System.out.println("  seite1 is NULL");
            }
        });

        return ResponseEntity.ok(all);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Wartungsprotokoll> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Wartungsprotokoll> update(
            @PathVariable Long id,
            @RequestBody Wartungsprotokoll updated
    ) {
        return ResponseEntity.ok(service.update(id, updated));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{id}/pdf")
    public ResponseEntity<byte[]> pdf(@PathVariable Long id) {
        byte[] bytes = pdfService.generate(id);

        return ResponseEntity
                .ok()
                .header("Content-Type", "application/pdf")
                .header("Content-Disposition", "attachment; filename=wartungsprotokoll.pdf")
                .body(bytes);
    }


}