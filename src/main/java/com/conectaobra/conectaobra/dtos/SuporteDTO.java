package com.conectaobra.conectaobra.dtos;

import com.conectaobra.conectaobra.models.Status;
import com.conectaobra.conectaobra.models.Suporte;

import java.util.UUID;

public record SuporteDTO(String nome, String tag, UUID guiaId, Status status) {

    public Suporte mapearParaSuporte(){
        Suporte suporte =  new Suporte();
        suporte.setName(nome);
        suporte.setTag(tag);
        suporte.setGuiaId(guiaId);
        suporte.setStatus(status);
        return suporte;
    }
}
