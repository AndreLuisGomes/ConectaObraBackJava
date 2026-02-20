package com.conectaobra.controllers;

import com.conectaobra.dtos.UsuarioDTO;
import com.conectaobra.dtos.UsuarioLoginDTO;
import com.conectaobra.models.Usuario;
import com.conectaobra.services.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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
    private final PasswordEncoder passwordEncoder;

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
    public ResponseEntity<String> criarUsuario(@RequestBody UsuarioDTO usuarioDTO){
        Optional<Usuario> usuario2 = this.usuarioService.obterPorNome(usuarioDTO.nome());
        if(usuario2.isEmpty()){
            Usuario usuario = usuarioDTO.mapearParaUsuario();
            usuarioService.salvarUsuario(usuario);
            URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("{/id}")
                    .buildAndExpand(usuario.getId())
                    .toUri();
            return ResponseEntity.created(uri).build();
        }
        return ResponseEntity.status(409).body("Email já registrado!");
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

    @PostMapping("/logar")
    public ResponseEntity<String> logarUsuario(@RequestBody UsuarioLoginDTO usuarioLoginDTO, HttpServletRequest http, InMemoryUserDetailsManager manager){
        UserDetails user = manager.loadUserByUsername("admin");
        if (user != null) {
            try {
                http.authenticate((HttpServletResponse) user);
                return ResponseEntity.ok().build();
            }catch (Exception e){

            }
        }
        return ResponseEntity.badRequest().body("Nome ou senha incorretos!");
    }
}
