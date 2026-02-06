package com.conectaobra.conectaobra.services;

import com.conectaobra.conectaobra.dtos.UsuarioLoginDTO;
import com.conectaobra.conectaobra.models.Usuario;
import com.conectaobra.conectaobra.repositories.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UsuarioService {

    // Injeção de dependências //

    private final UsuarioRepository usuarioRepository;

    // Métodos de verificação //

    public boolean verificarUsuario(Usuario usuario){
        return usuarioRepository.existsById(usuario.getId());
    }

    public boolean loginEstaCorreto(UsuarioLoginDTO usuarioLoginDTO, PasswordEncoder passwordEncoder){
        Optional<Usuario> usuarioDoBanco = this.usuarioRepository.findByNome(usuarioLoginDTO.nome());
        return usuarioDoBanco.filter(usuario -> passwordEncoder.matches(usuarioLoginDTO.senha(), usuario.getSenha())).isPresent();
    }


    // Métodos de obtenção //

    public List<Usuario> obterTodos(){
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> obterPorId(UUID uuid){
        return usuarioRepository.findById(uuid);
    }

    public Optional<Usuario> obterPorNome(String nome) { return usuarioRepository.findByNome(nome); }

    // Métodos de criação //

    public void criarUsuario(Usuario usuario){
        usuarioRepository.save(usuario);
    }

    // Métodos de atualização //

    public Optional<Usuario> atualizarUsuario(Usuario usuario){
        return usuarioRepository.findById(usuario.getId()).map(u -> {
           u.setNome(usuario.getNome());
           u.setSenha(usuario.getSenha());
           return usuarioRepository.save(u);
        });
    }


    // Métodos de deleção //

    public void deletarUsuario(UUID usuarioId){
        usuarioRepository.deleteById(usuarioId);
    }
}
