package com.conectaobra.conectaobra.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "suporte")
@Data
public class Suporte {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String tag;

    @Column(name = "guia_id")
    private UUID guiaId;

    private Status status;

    @Column(name = "descricao")
    private String descricao;

    private String localizacao;
}
