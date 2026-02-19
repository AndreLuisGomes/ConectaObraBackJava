package com.conectaobra.conectaobra.services;

import com.conectaobra.conectaobra.dtos.UsuarioDTO;
import com.conectaobra.conectaobra.dtos.UsuarioLoginDTO;
import com.conectaobra.conectaobra.models.Usuario;
import com.conectaobra.conectaobra.repositories.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UsuarioService {

    // Dependências \\

    private final UsuarioRepository usuarioRepository;

    // Métodos para verificação \\

    public boolean verificarUsuario(Usuario usuario){
        return usuarioRepository.existsById(usuario.getId());
    }

    public boolean loginEstaCorreto(UsuarioLoginDTO usuarioLoginDTO, PasswordEncoder passwordEncoder){
        Optional<Usuario> usuarioDoBanco = this.usuarioRepository.findByNome(usuarioLoginDTO.nome());
        return usuarioDoBanco.filter(usuario -> passwordEncoder.matches(usuarioLoginDTO.senha(), usuario.getSenha())).isPresent();
    }

    public boolean camposLoginCorretos(UsuarioLoginDTO usuarioLoginDTO){
        if(usuarioLoginDTO.senha() == null || usuarioLoginDTO.nome() == null){
            return false;
        }else return !usuarioLoginDTO.senha().isBlank() &&
                !usuarioLoginDTO.nome().isBlank();
    }

//    public boolean camposRegistroCorretos(Usuario usuarioDTO){
//        if(camposLoginCorretos(new UsuarioLoginDTO(usuarioDTO.getNome(), usuarioDTO.getSenha()))){
//
//        }
//    }

    // Métodos para obter \\

    public List<Usuario> obterTodos(){
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> obterPorId(UUID uuid){
        return usuarioRepository.findById(uuid);
    }

    public Optional<Usuario> obterPorNome(String nome) { return usuarioRepository.findByNome(nome); }

    // Métodos para salvar \\

    @Transactional
    public void salvarUsuario(Usuario usuario){
        usuarioRepository.save(usuario);
    }

    // Métodos para atualizar \\

    @Transactional
    public Optional<Usuario> atualizarUsuario(Usuario usuario){
        return usuarioRepository.findById(usuario.getId()).map(u -> {
           u.setNome(usuario.getNome());
           u.setSenha(usuario.getSenha());
           return usuarioRepository.save(u);
        });
    }

    // Métodos para deletar \\

    @Transactional
    public void deletarUsuario(UUID usuarioId){
        usuarioRepository.deleteById(usuarioId);
    }
}
