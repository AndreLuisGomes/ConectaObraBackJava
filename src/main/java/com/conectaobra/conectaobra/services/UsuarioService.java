package com.conectaobra.conectaobra.services;

import com.conectaobra.conectaobra.enums.Funcao;
import com.conectaobra.conectaobra.enums.Setor;
import com.conectaobra.conectaobra.specs.UsuarioSpecs;
import lombok.Data;
import com.conectaobra.conectaobra.models.Usuario;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.conectaobra.conectaobra.repositories.UsuarioRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Data
public class UsuarioService {

    // Injeção de dependências //

    private final UsuarioRepository usuarioRepository;

    // Métodos de verificação //

    public boolean verificarUsuario(Usuario usuario){
        if(usuarioRepository.existsById(usuario.getId())){
            return true;
        }
        return false;
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
