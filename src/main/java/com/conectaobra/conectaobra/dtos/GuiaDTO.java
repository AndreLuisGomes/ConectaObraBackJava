package com.conectaobra.conectaobra.dtos;

import com.conectaobra.conectaobra.models.Guia;

import java.util.UUID;

public record GuiaDTO(String local, String nome, UUID clienteId) {

    public Guia mapearParaGuia(){
        Guia guia = new Guia();
        guia.setLocal(local);
        guia.setNome(nome);
        guia.setClienteId(clienteId);
        return guia;
    }
}
