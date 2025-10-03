package com.conectaobra.conectaobra.enums;

public enum GuiaStatus {

    EM_ESPERA("Em espera"),
    PROJETANDO("Em Projeção"),
    CORTANDO("Em corte"),
    FABRICANDO("Fabricando"),
    FINALIZADA("Finalizada"),
    TRANSPORTANDO("Transportando");

    private final String guiaStatus;

    GuiaStatus(String descricao) {
        this.guiaStatus = descricao;
    }
}
