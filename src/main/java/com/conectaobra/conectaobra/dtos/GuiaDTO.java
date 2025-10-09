package com.conectaobra.conectaobra.dtos;

import com.conectaobra.conectaobra.enums.GuiaStatus;
import com.conectaobra.conectaobra.models.Cliente;
import com.conectaobra.conectaobra.models.Guia;

public record GuiaDTO(String local, String nome, GuiaStatus guiaStatus, String nomeCliente) {

    public Guia mapearParaGuia(){
        Guia guia = new Guia();
        guia.setLocal(local);
        guia.setGuiaStatus(guiaStatus);
        guia.setNome(nome);
        guia.setNomeCliente(nomeCliente);
        return guia;
    }
}
