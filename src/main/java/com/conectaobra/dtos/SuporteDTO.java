package com.conectaobra.dtos;

import com.conectaobra.models.Suporte;

import java.util.UUID;

public record SuporteDTO(String nome, String tag, UUID guiaId) {

    public Suporte mapearParaSuporte(){
        Suporte suporte =  new Suporte();
        suporte.setName(nome);
        suporte.setTag(tag);
        suporte.setGuiaId(guiaId);
        return suporte;
    }
}
