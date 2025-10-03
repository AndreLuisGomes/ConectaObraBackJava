package com.conectaobra.conectaobra.controllers;

import com.conectaobra.conectaobra.dtos.UsuarioDTO;
import com.conectaobra.conectaobra.models.Usuario;
import com.conectaobra.conectaobra.services.UsuarioService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@Data
@RequestMapping("/usuarios")
public class UsuarioController {

    // Injeção de dependências //

    private final UsuarioService usuarioService;

    // Métodos GETs //

    @GetMapping
    public List<Usuario> obterTodos(){
        return usuarioService.obterTodos();
    }

    @GetMapping("/{id}")
    public Optional<Usuario> obterPorNome(@PathVariable(value = "id") UUID uuid){
        return usuarioService.obterPorId(uuid);
    }

    @PostMapping
    public ResponseEntity<Void> criarUsuario(@RequestBody UsuarioDTO usuarioDTO){
        Usuario usuario = usuarioDTO.mapearParaUsuario();
        usuarioService.criarUsuario(usuario);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{/id}")
                .buildAndExpand(usuario.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    // Métodos DELETEs //

    @DeleteMapping
    public ResponseEntity<Void> deletarUsuario(@RequestParam UUID uuid){
        if(usuarioService.obterPorId(uuid).isPresent()){
            usuarioService.deletarUsuario(uuid);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
