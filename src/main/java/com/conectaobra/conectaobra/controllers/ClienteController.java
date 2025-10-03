package com.conectaobra.conectaobra.controllers;

import com.conectaobra.conectaobra.dtos.ClienteDTO;
import com.conectaobra.conectaobra.models.Cliente;
import com.conectaobra.conectaobra.services.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clientes")
@AllArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public List<Cliente> obterClientes(){
        return clienteService.obterClientes();
    }

    @PostMapping
    public ResponseEntity<Void> salvarCliente(@RequestBody ClienteDTO clienteDTO){
        Cliente cliente = clienteDTO.mapearParaCliente();
        clienteService.salvarCliente(cliente);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{/id}")
                .buildAndExpand(cliente.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }
}
