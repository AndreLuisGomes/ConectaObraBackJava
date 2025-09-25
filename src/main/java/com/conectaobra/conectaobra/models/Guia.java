package com.conectaobra.conectaobra.models;

import com.conectaobra.conectaobra.enums.GuiaStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "guias")
@AllArgsConstructor
@NoArgsConstructor
public class Guia {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String local;

    private String nome;

    @Enumerated
    private GuiaStatus status;

    @ManyToOne
    private Cliente cliente;
}
