package com.conectaobra.conectaobra.models;

import com.conectaobra.conectaobra.enums.Status;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table
@Data
public class Suporte {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String tag;

    @ManyToOne
    private Guia guia;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany
    private List<SuporteHistorico> historico = new ArrayList<SuporteHistorico>();
}
