package com.conectaobra.dtos;

import com.conectaobra.models.Role;
import com.conectaobra.models.Usuario;

public record UsuarioDTO(String nome, String senha, Role role){

    public Usuario mapearParaUsuario(){
        return new Usuario(null, nome, senha, role);
    }
}
