package br.com.AdOptimize.api.controller;


import br.com.AdOptimize.api.dto.AnuncioRequest;
import br.com.AdOptimize.api.dto.AnuncioResponse;
import br.com.AdOptimize.api.service.AnuncioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/anuncios")
public class AnuncioController {

    private final AnuncioService anuncioService;
    public AnuncioController(AnuncioService anuncioService){
        this.anuncioService = anuncioService;
    }

    @GetMapping
    public List<AnuncioResponse> getAllAnuncios() {
        return anuncioService.getAllAnuncio();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnuncioResponse> getAnuncioById(@PathVariable Long id) {
        return ResponseEntity.ok(anuncioService.getAnuncioById(id));
    }

    @PostMapping
    public ResponseEntity<AnuncioResponse> createAluno(@Valid @RequestBody AnuncioRequest anuncioRequest) {
        return ResponseEntity.ok(anuncioService.createAnuncio(anuncioRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnuncioResponse> updateAluno(@PathVariable Long id, @Valid @RequestBody AnuncioRequest anuncioRequest) {
        return ResponseEntity.ok(anuncioService.updateAnuncio(id, anuncioRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAluno(@PathVariable Long id) {
        anuncioService.deleteAnuncio(id);
        return ResponseEntity.noContent().build();
    }

}
