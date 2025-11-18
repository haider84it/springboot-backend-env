package com.example.pvbackend.controller;

import com.example.pvbackend.model.ServicePartner;
import com.example.pvbackend.service.ServicePartnerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service-partner")
@CrossOrigin(origins = "https://envaris.cloudaxes.de")
public class ServicePartnerController {

    private final ServicePartnerService service;

    public ServicePartnerController(ServicePartnerService service) {
        this.service = service;
    }

    @GetMapping
    public List<ServicePartner> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ServicePartner getById(@PathVariable Long id) {
        return service.getById(id)
                .orElseThrow(() -> new RuntimeException("ServicePartner not found with id " + id));
    }

    @PostMapping
    public ServicePartner create(@RequestBody ServicePartner sp) {
        return service.create(sp);
    }

    @PutMapping("/{id}")
    public ServicePartner update(@PathVariable Long id, @RequestBody ServicePartner sp) {
        return service.update(id, sp);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }


}
