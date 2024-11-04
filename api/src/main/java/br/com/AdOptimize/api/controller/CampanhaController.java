package br.com.AdOptimize.api.controller;

import br.com.AdOptimize.api.dto.CampanhaRequest;
import br.com.AdOptimize.api.dto.CampanhaResponse;
import br.com.AdOptimize.api.service.CampanhaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/campanhas")
@RequiredArgsConstructor
public class CampanhaController {

    private final CampanhaService campanhaService;

    @GetMapping
    public List<CampanhaResponse> getAllCampanhas() {
        return campanhaService.getAllCampanhas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CampanhaResponse> getCampanhaById(@PathVariable Long id) {
        return ResponseEntity.ok(campanhaService.getCampanhaById(id));
    }

    @PostMapping
    public ResponseEntity<CampanhaResponse> createCampanha(@Valid @RequestBody CampanhaRequest campanhaRequest) {
        return ResponseEntity.ok(campanhaService.createCampanha(campanhaRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CampanhaResponse> updateCampanha(@PathVariable Long id, @Valid @RequestBody CampanhaRequest campanhaRequest) {
        return ResponseEntity.ok(campanhaService.updateCampanha(id, campanhaRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCampanha(@PathVariable Long id) {
        campanhaService.deleteCampanha(id);
        return ResponseEntity.noContent().build();
    }

}
