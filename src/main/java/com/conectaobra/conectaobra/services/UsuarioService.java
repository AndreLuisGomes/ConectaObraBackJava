package com.conectaobra.conectaobra.services;

import com.conectaobra.conectaobra.models.Usuario;
import com.conectaobra.conectaobra.repositories.UsuarioRepository;
import lombok.AllArgsConstructor;
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

    // Métodos de obtenção //

    public List<Usuario> obterTodos(){
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> obterPorId(UUID uuid){
        return usuarioRepository.findById(uuid);
    }

    // Métodos de criação //

    public Usuario criarUsuario(Usuario usuario){
        usuarioRepository.save(usuario);
        return usuario;
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
