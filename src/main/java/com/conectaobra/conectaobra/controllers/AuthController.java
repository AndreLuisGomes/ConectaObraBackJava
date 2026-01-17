package com.conectaobra.conectaobra.controllers;

import com.conectaobra.conectaobra.dtos.UsuarioDTO;
import com.conectaobra.conectaobra.dtos.UsuarioLoginDTO;
import com.conectaobra.conectaobra.models.Usuario;
import com.conectaobra.conectaobra.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final AuthenticationManager authManager;

    @PostMapping("logar")
    ResponseEntity logar(@RequestBody UsuarioLoginDTO usuarioLoginDTO, UserDetailsService service){
        if(usuarioLoginDTO == null){
            return ResponseEntity.badRequest().body("Requisição inválida!");
        }
        if(usuarioLoginDTO.senha().isBlank() && usuarioLoginDTO.nome().isBlank()){
            return ResponseEntity.badRequest().body("Nome de usuário ou senha estão vazios!");
        }else{
            definirAutenticacao(usuarioLoginDTO);
            return ResponseEntity.ok().body(usuarioLoginDTO);
        }
    }

    private void definirAutenticacao(UsuarioLoginDTO usuarioLoginDTO){
        UsernamePasswordAuthenticationToken nomeSenha = new UsernamePasswordAuthenticationToken(usuarioLoginDTO.nome(), usuarioLoginDTO.senha());
        Authentication auth = this.authManager.authenticate(nomeSenha);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    @PostMapping("registrar")
    ResponseEntity registrar(@RequestBody UsuarioDTO usuarioDTO){
        Optional<Usuario> usuario = this.usuarioRepository.findByEmail(usuarioDTO.email());
        if(usuario.isEmpty()){
            Usuario novoUsuario = usuarioDTO.mapearParaUsuario();
            this.usuarioRepository.save(novoUsuario);
            UsuarioLoginDTO usuarioLoginDTO = new UsuarioLoginDTO(novoUsuario.getNome(), novoUsuario.getSenha());
            definirAutenticacao(usuarioLoginDTO);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(409).body("Email já está em uso!");
    }
}
