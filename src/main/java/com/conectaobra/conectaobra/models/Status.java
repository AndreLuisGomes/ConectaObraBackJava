package com.conectaobra.conectaobra.models;


import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Embeddable
@Data
public class Status {

    private String status;

    @Column(name = "descricao_status")
    private String descricaoStatus;

    @Column(name = "usuario_id")
    private UUID usuarioId;
}
