package com.conectaobra.conectaobra.controllers;

import com.conectaobra.conectaobra.models.Suporte;
import com.conectaobra.conectaobra.services.SuporteService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pecas")
@Data
public class SuporteController {

    private final SuporteService suporteService;

    @GetMapping
    public List<Suporte> obterTodos(){
        return suporteService.getSuporteRepository().findAll();
    }

    @GetMapping("/{tag}")
    public List<Suporte> obterPorTag(@PathVariable("tag") String tag){
        return suporteService.obterPorTag(tag);
    }

    // Métodos POSTs

    @PostMapping
    public ResponseEntity<Void> salvarSuporte(@RequestBody Suporte suporte){
        suporteService.salvarSuporte(suporte);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{/id}")
                .buildAndExpand(suporte.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }
}
