package com.conectaobra.conectaobra.controllers;

import com.conectaobra.conectaobra.dtos.AuthResponse;
import com.conectaobra.conectaobra.dtos.UsuarioDTO;
import com.conectaobra.conectaobra.dtos.UsuarioLoginDTO;
import com.conectaobra.conectaobra.models.Role;
import com.conectaobra.conectaobra.models.Usuario;
import com.conectaobra.conectaobra.repositories.RoleRepository;
import com.conectaobra.conectaobra.repositories.UsuarioRepository;
import com.conectaobra.conectaobra.services.UsuarioService;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import lombok.RequiredArgsConstructor;
import org.hibernate.mapping.Any;
import org.springframework.data.util.Optionals;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
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
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
@EnableMethodSecurity
public class AuthController {


    public final String apiName;

    private final JwtEncoder jwtEncoder;

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioService usuarioService;
    private final RoleRepository roleRepository;

    @PostMapping("logar")
    ResponseEntity<Object> logar(@RequestBody UsuarioLoginDTO usuarioLoginDTO){

        if(usuarioLoginDTO.nome().isBlank() || usuarioLoginDTO.senha().isBlank()){
            return ResponseEntity.badRequest().body("Digite uma requisição válida!");
        }
        Optional<Usuario> usuarioDoBanco = this.usuarioRepository.findByNome(usuarioLoginDTO.nome());

        if(usuarioDoBanco.isEmpty() || !this.usuarioService.loginEstaCorreto(usuarioLoginDTO, passwordEncoder)){
            return ResponseEntity.status(401).body("Usuário ou senha inválidos!");
        }else{

            return ResponseEntity.ok().body(
                    new AuthResponse(usuarioLoginDTO.nome(),
                            gerarJWT(usuarioDoBanco.get()).getTokenValue(),
                            usuarioDoBanco.get().getRole().getNome()));
        }
    }

    private Jwt gerarJWT(Usuario usuario){
        var now = Instant.now();
        var exp = 300L;

        var claims = JwtClaimsSet.builder()
                .issuer(this.apiName)
                .subject(usuario.getId().toString())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(exp))
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims));
    }


//    private void definirAutenticacao(UsuarioLoginDTO usuarioLoginDTO){
//        UsernamePasswordAuthenticationToken nomeSenha = new UsernamePasswordAuthenticationToken(usuarioLoginDTO.nome(), usuarioLoginDTO.senha());
//        Authentication auth = this.authManager.authenticate(nomeSenha);
//        SecurityContextHolder.getContext().setAuthentication(auth);
//    }

    @PostMapping("registrar")
    ResponseEntity<Object> registrar(@RequestBody UsuarioDTO usuarioDTO){
        Optional<Usuario> usuario = this.usuarioRepository.findByNome(usuarioDTO.nome());
        Optional<Role> role = this.roleRepository.findById(usuarioDTO.role().getId());
        if(role.isEmpty()){
            return ResponseEntity.status(400).body("Role inválida ou não existe!");
        }
        if(usuario.isEmpty()){
            Usuario novoUsuario = usuarioDTO.mapearParaUsuario();
            novoUsuario.setRole(role.get());
            novoUsuario.setSenha(this.passwordEncoder.encode(novoUsuario.getSenha()));
            this.usuarioRepository.save(novoUsuario);
            UsuarioLoginDTO usuarioLoginDTO = new UsuarioLoginDTO(novoUsuario.getNome(), novoUsuario.getSenha());
            Usuario retornoUsuario = this.usuarioRepository.findByNome(novoUsuario.getNome()).get();
            return ResponseEntity.ok().body(new AuthResponse(retornoUsuario.getNome(), this.gerarJWT(retornoUsuario).getTokenValue(), retornoUsuario.getRole().getNome()));
        }
        return ResponseEntity.status(409).body("Nome já está em uso!");
    }
}
