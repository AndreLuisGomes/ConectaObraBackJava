package com.conectaobra.conectaobra.dtos;

import com.conectaobra.conectaobra.models.Cliente;

public record ClienteDTO(String nome, String contato, String localizacaoSede) {

    public Cliente mapearParaCliente(){
        Cliente cliente = new Cliente();
        cliente.setNome(this.nome);
        cliente.setContato(this.contato);
        cliente.setLocalizacaoSede(this.localizacaoSede);
        return cliente;
    }
}
