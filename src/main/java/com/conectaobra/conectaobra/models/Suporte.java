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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guia_id")
    private Guia guia;

    private String descricao;

    @Enumerated
    private Status status;

    @OneToMany(mappedBy = "suporte", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SuporteHistorico> suporteHistorico = new ArrayList<>();
}
