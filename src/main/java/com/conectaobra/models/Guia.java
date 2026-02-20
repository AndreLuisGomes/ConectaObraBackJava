package com.conectaobra.models;

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

    private String status;

    private String nome;

    private UUID clienteId;
}
