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
import java.util.UUID;

@RestController
@RequestMapping("/clientes")
@AllArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public List<Cliente> obterClientesPorParametros(
                @RequestParam(value = "nome", required = false) String nome,
                @RequestParam(value = "contato", required = false) String contato,
                @RequestParam(value = "localizacaoSede", required = false) String localizacaoSede){
        ClienteDTO clienteDTO = new ClienteDTO(nome, contato, localizacaoSede);
        return clienteService.obterClientesPorParametros(clienteDTO);
    }

    @PostMapping
    public ResponseEntity<Cliente> salvarCliente(@RequestBody ClienteDTO clienteDTO){
        Cliente cliente = clienteDTO.mapearParaCliente();
        clienteService.salvarCliente(cliente);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{/id}")
                .buildAndExpand(cliente.getId())
                .toUri();
        return ResponseEntity.created(uri).body(cliente);
    }

    @PutMapping
    public ResponseEntity<Cliente> atualizarCliente(@RequestBody Cliente cliente){
        clienteService.salvarCliente(cliente);
        return ResponseEntity.ok().body(cliente);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable(value = "id") UUID clienteId){
        clienteService.deletarClientePorId(clienteId);
        return ResponseEntity.ok().build();
    }
}
