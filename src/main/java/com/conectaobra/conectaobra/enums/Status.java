package com.conectaobra.conectaobra.enums;

import lombok.Getter;

@Getter
public enum Status {

    EM_DESENHO("Em projeção"),
    EM_PREPARACAO("Em preparação"),
    FABRICANDO("Fabricando"),
    PINTURA("Pintando"),
    AGUARDANDO("Aguardando");

    private final String descricao;

    Status(String descricao) {
        this.descricao = descricao;
    }
}
