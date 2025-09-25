package com.conectaobra.conectaobra.enums;

import lombok.Getter;

@Getter
public enum Status {

    PROJETANDO("Em projeção"),
    EM_PREPARACAO("Em preparação"),
    FABRICANDO("Fabricando"),
    PINTURA("Pintando"),
    FINALIZADA("Finalizada"),
    AGUARDANDO("Aguardando");

    private final String descricao;

    Status(String descricao) {
        this.descricao = descricao;
    }
}
