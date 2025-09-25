package com.conectaobra.conectaobra.enums;

public enum Funcao {

    CALDEIREIRO("Caldeireiro"),
    SOLDADOR("Soldador"),
    MOTORISTA("Motorista"),
    LIBERADOR("Liberador"),
    DESENHISTA("Desenhista"),
    ENGENHEIRO("Engenheiro"),
    INSPETOR_CALDEIRARIA("Inspetor de Caldeiraria"),
    INSPETOR_SOLDA("Inspetor de Solda"),
    INSPETOR_TUBULACAO("Inspetor de Tubulação"),
    ENCARREGADO_SOLDA("Encarregado de Solda"),
    ENCARREGADO_TUBULACAO("Encarregado de Tubulação"),
    ENCARREGADO_FABRICACAO("Encarregado de Fabricação"),
    SUPERVISOR_FABRICACAO("Supervisor de Fabricação"),
    SUPERVISOR_LIBERACAO("Supervisor de Liberação");

    private final String funcao;

    Funcao(String funcao){
        this.funcao = funcao;
    }
}
