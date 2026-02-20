package com.conectaobra.conectaobra.dtos;

import com.conectaobra.conectaobra.models.Role;
import com.conectaobra.conectaobra.models.Usuario;

public record UsuarioDTO(String nome, String senha, Role role){

    public Usuario mapearParaUsuario(){
        return new Usuario(null, nome, senha, role);
    }
}
