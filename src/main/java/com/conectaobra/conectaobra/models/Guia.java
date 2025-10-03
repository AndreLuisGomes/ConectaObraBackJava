package com.conectaobra.conectaobra.models;

import com.conectaobra.conectaobra.enums.GuiaStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "guias")
@Data
public class Guia {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String local;

    private String nome;

    @Enumerated(EnumType.STRING)
    private GuiaStatus guiaStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    private Cliente cliente;
}
