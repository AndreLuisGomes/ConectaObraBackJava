package com.conectaobra.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "suportes")
@Data
public class Suporte {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String tag;

    @Column(name = "guia_id")
    private UUID guiaId;

    @Column(name = "descricao")
    private String descricao;

    private String status;

    private String localizacao;
}
