package com.conectaobra.conectaobra.enums;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public enum Material {
    ACO_INOX("Aço Inox"),
    ACO_CARBONO("Aço Carbono"),
    AÇO_CARBONO_GALVANIZADO("Galvanizado"),
    ALUMINIO("Alumínio");

    private final String material;

    Material(String material){
        this.material = material;
    }
}
