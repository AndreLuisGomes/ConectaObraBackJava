package com.conectaobra.conectaobra.dtos;

import com.conectaobra.conectaobra.models.Usuario;

public record UsuarioDTO(String nome, String senha, String funcao, String setor){

    public Usuario mapearParaUsuario(){
        Usuario usuario = new Usuario();
        usuario.setSenha(senha);
        usuario.setSetor(setor);
        usuario.setNome(nome);
        usuario.setFuncao(funcao);
        return usuario;
    }
}
