package com.conectaobra.conectaobra.enums;

import lombok.Getter;

@Getter
public enum GuiaStatus {

    EM_ESPERA("Em espera"),
    PROJETANDO("Projetando"),
    FABRICANDO("Fabricando"),
    FINALIZADA("Finalizada"),
    TRANSPORTANDO("Transportando");

    private final String descricao;

    GuiaStatus(String descricao) {
        this.descricao = descricao;
    }
}
