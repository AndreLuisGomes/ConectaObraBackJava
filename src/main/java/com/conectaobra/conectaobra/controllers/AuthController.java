package com.conectaobra.conectaobra.controllers;

import com.conectaobra.conectaobra.dtos.AuthResponse;
import com.conectaobra.conectaobra.dtos.UsuarioDTO;
import com.conectaobra.conectaobra.dtos.UsuarioLoginDTO;
import com.conectaobra.conectaobra.models.Role;
import com.conectaobra.conectaobra.models.Usuario;
import com.conectaobra.conectaobra.services.RoleService;
import com.conectaobra.conectaobra.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
@EnableMethodSecurity
public class AuthController {

    // Dependências \\
    public final String apiName;

    private final JwtEncoder jwtEncoder;
    private final PasswordEncoder passwordEncoder;

    private final UsuarioService usuarioService;
    private final RoleService roleService;

    // Método de login \\

    @PostMapping("logar")
    ResponseEntity<Object> logar(@RequestBody UsuarioLoginDTO usuarioLoginDTO){

        // Verifica e valida se o login e a senha estão preenchidas e não são vazias \\
        if(usuarioLoginDTO.nome().isBlank() || usuarioLoginDTO.senha().isBlank()){
            return ResponseEntity.badRequest().body("Envie uma requisição válida!");
        }
        Optional<Usuario> usuarioDoBanco = this.usuarioService.obterPorNome(usuarioLoginDTO.nome());

        if(usuarioDoBanco.isEmpty() || !this.usuarioService.loginEstaCorreto(usuarioLoginDTO, passwordEncoder)){
            return ResponseEntity.status(401).body("Usuário ou senha inválidos!");
        }else{

            return ResponseEntity.ok().body(
                    new AuthResponse(usuarioLoginDTO.nome(),
                            gerarJWT(usuarioDoBanco.get()).getTokenValue(),
                            usuarioDoBanco.get().getRole().getNome()));
        }
    }

    // Método de registrar \\

    @PostMapping("registrar")
    ResponseEntity<Object> registrar(@RequestBody UsuarioDTO usuarioDTO){
        Optional<Usuario> usuario = this.usuarioService.obterPorNome(usuarioDTO.nome());
        Optional<Role> role = this.roleService.obterPorId(usuarioDTO.role().getId());
        if(role.isEmpty()){
            return ResponseEntity.status(400).body("Role inválida ou não existe!");
        }
        if(usuario.isEmpty()){
            Usuario novoUsuario = usuarioDTO.mapearParaUsuario();
            novoUsuario.setRole(role.get());
            novoUsuario.setSenha(this.passwordEncoder.encode(novoUsuario.getSenha()));
            this.usuarioService.salvarUsuario(novoUsuario);
            Usuario retornoUsuario = this.usuarioService.obterPorNome(novoUsuario.getNome()).get();
            return ResponseEntity.ok().body(new AuthResponse(retornoUsuario.getNome(), this.gerarJWT(retornoUsuario).getTokenValue(), retornoUsuario.getRole().getNome()));
        }
        return ResponseEntity.status(409).body("Nome já está em uso!");
    }

    // Método para gerar JWT \\

    private Jwt gerarJWT(Usuario usuario){
        var now = Instant.now();
        var exp = 300L;

        Optional<Role> role = this.roleService.obterPorId(usuario.getRole().getId());

        // Claims: são os "componentes" que o JwtEncoder aceita para sua construção \\
        var claims = JwtClaimsSet.builder()
                .issuer(this.apiName)
                .subject(usuario.getId().toString())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(exp))
//                .claims.put("role", List.of("ROLE_" + usuario.getRole().getNome()))
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims));
    }
}
