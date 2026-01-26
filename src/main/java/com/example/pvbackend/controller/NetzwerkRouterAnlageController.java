package com.example.pvbackend.controller;



import com.example.pvbackend.model.NetzwerkRouterAnlage;
import com.example.pvbackend.service.NetzwerkRouterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/netzwerkrouter")
@CrossOrigin(origins = {
        "https://envaris.cloudaxes.de",
        "https://wartung.envaris.de"
})
public class NetzwerkRouterAnlageController {

    private final NetzwerkRouterService netzwerkRouterService;


    public NetzwerkRouterAnlageController(NetzwerkRouterService netzwerkRouterService) {
        this.netzwerkRouterService = netzwerkRouterService;
    }

    @GetMapping
    public List<NetzwerkRouterAnlage> getAllNetzwerkRouter() {
        return netzwerkRouterService.getAllNetzwerkRouter();
    }

    @GetMapping("/{id}")
    public Optional<NetzwerkRouterAnlage> getNetzwerkRouterById(@PathVariable Long id) {
        return netzwerkRouterService.getNetzwerkRouterById(id);
    }

    @GetMapping("/anlage/{anlageId}")
    public NetzwerkRouterAnlage getNetzwerkRouterByAnlage(@PathVariable Long anlageId) {
        return netzwerkRouterService.getNetzwerkRouterByAnlage(anlageId);
    }

    @PostMapping
    public NetzwerkRouterAnlage createNetzwerkRouter(@RequestBody NetzwerkRouterAnlage netzwerkRouterAnlage) {
        return netzwerkRouterService.saveNetzwerkRouter(netzwerkRouterAnlage);
    }

    @PutMapping("/{id}")
    public NetzwerkRouterAnlage updateNetzwerkRouter(@PathVariable Long id, @RequestBody NetzwerkRouterAnlage updateNetzwerkRouter) {
        updateNetzwerkRouter.setId(id);
        return netzwerkRouterService.saveNetzwerkRouter(updateNetzwerkRouter);
    }


    @DeleteMapping("/{id}")
    public void deleteNetzwerkRouter(@PathVariable Long id) {
        netzwerkRouterService.deleteNetzwerkRouter(id);
    }


}
