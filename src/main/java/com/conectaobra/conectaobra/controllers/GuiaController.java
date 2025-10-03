package com.conectaobra.conectaobra.controllers;

import com.conectaobra.conectaobra.dtos.GuiaDTO;
import com.conectaobra.conectaobra.models.Guia;
import com.conectaobra.conectaobra.services.GuiaService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/guias")
@Data
public class GuiaController {

    private final GuiaService guiaService;

    @GetMapping
    public List<Guia> obterGuias(){
        return guiaService.obterTodos();
    }

    @GetMapping("/{nome}")
    public Guia obterGuia(@RequestParam(required = false, name ="nome") String nome){
        return guiaService.obterGuiaPorNome(nome);
    }

    @PostMapping
    public ResponseEntity<Void> salvarGuia(@RequestBody GuiaDTO guiaDTO){

        Guia guia = guiaDTO.mapearParaGuia();
        guiaService.salvarGuia(guia);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(guia.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

}
