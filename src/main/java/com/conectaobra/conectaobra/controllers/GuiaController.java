package com.conectaobra.conectaobra.controllers;

import com.conectaobra.conectaobra.dtos.GuiaDTO;
import com.conectaobra.conectaobra.models.Guia;
import com.conectaobra.conectaobra.models.Status;
import com.conectaobra.conectaobra.services.GuiaService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/guias")
@Data
public class GuiaController {

    private final GuiaService guiaService;

    @GetMapping
    public List<Guia> obterGuias(
            @RequestParam(value = "local", required = false) String local,
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "guiaStatus", required = false) Status status,
            @RequestParam(value = "nomeCliente", required = false) UUID nomeCliente
            ){
        GuiaDTO guiaDTO = new GuiaDTO(local, nome, status, nomeCliente);
        return guiaService.obterGuiaPorParametros(guiaDTO);
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
