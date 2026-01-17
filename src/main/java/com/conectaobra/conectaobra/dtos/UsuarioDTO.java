package com.conectaobra.conectaobra.dtos;

import com.conectaobra.conectaobra.models.Usuario;

public record UsuarioDTO(String nome, String email, String role, String senha, String funcao, String setor){

    public Usuario mapearParaUsuario(){
        return new Usuario(null, nome, senha, email, funcao, setor, role);
    }
}
