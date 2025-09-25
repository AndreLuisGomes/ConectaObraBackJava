package com.conectaobra.conectaobra.enums;

import lombok.Getter;

@Getter
public enum Setor {

    ENGENHARIA("Engenharia"),
    PREPARACAO("Preparação"),
    FABRICACAO("Fabricação"),
    PINTURA("Pintura"),
    QUALIDADE("Qualidade"),
    PLANEJAMENTO("PLANEJAMENTO");

    private final String descricao;

    Setor(String descricao) {
        this.descricao = descricao;
    }
}
