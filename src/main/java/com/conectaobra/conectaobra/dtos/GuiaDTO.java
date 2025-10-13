package com.conectaobra.conectaobra.dtos;

import com.conectaobra.conectaobra.models.Guia;
import com.conectaobra.conectaobra.models.Status;

import java.util.UUID;

public record GuiaDTO(String local, String nome, Status status, UUID clienteId) {

    public Guia mapearParaGuia(){
        Guia guia = new Guia();
        guia.setLocal(local);
        guia.setStatus(status);
        guia.setNome(nome);
        guia.setClienteId(clienteId);
        return guia;
    }
}
