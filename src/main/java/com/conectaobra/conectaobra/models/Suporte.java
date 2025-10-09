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

    private UUID guiaId;

    private Status status;

    private String descricao;

    private String localizacao;

    @OneToMany(mappedBy = "suporte", cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name = "status_lista")
    private List<Status> statusLista = new ArrayList<>();
}
