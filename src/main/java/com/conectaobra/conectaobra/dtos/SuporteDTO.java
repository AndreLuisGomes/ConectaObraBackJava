package com.conectaobra.conectaobra.dtos;

import com.conectaobra.conectaobra.enums.Status;
import com.conectaobra.conectaobra.models.Guia;
import com.conectaobra.conectaobra.models.Suporte;

import java.util.List;

public record SuporteDTO(String nome, String tag, Guia guia, Status status, List<SuporteHistorico> suporteHistorico) {

    public Suporte mapearParaSuporte(){
        Suporte suporte =  new Suporte();
        suporte.setName(nome);
        suporte.setTag(tag);
        suporte.setGuia(guia);
        suporte.setStatus(status);
        return suporte;
    }
}
