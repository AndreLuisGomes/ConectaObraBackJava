package com.conectaobra.conectaobra.controllers;

import com.conectaobra.conectaobra.dtos.UsuarioDTO;
import com.conectaobra.conectaobra.dtos.UsuarioLoginDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
public class AuthController {


    @PostMapping("/logar")
    ResponseEntity logar(@RequestBody UsuarioLoginDTO usuarioLoginDTO, UserDetailsService service, AuthenticationManager authManager){
        if(usuarioLoginDTO == null){
            return ResponseEntity.badRequest().body("Requisição inválida!");
        }
        if(usuarioLoginDTO.senha().replaceAll(" ", "").matches("") && usuarioLoginDTO.nome().replaceAll(" ", "").matches("")){
            return ResponseEntity.badRequest().body("Nome de usuário ou senha estão vazios!");
        }else{
            UsernamePasswordAuthenticationToken nomeSenha = new UsernamePasswordAuthenticationToken(usuarioLoginDTO.nome(), usuarioLoginDTO.senha());
            Authentication auth = authManager.authenticate(nomeSenha);
            SecurityContextHolder.getContext().setAuthentication(auth);
            return ResponseEntity.ok().body(usuarioLoginDTO);
        }
    }

    @PostMapping("/registrar")
    String registrar(@RequestBody UsuarioDTO usuarioDTO){
        return "Sucesso";
    }

}
