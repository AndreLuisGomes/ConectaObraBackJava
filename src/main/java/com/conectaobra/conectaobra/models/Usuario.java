package com.conectaobra.conectaobra.models;

import com.conectaobra.conectaobra.enums.Funcao;
import com.conectaobra.conectaobra.enums.Setor;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "usuarios")
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nome;

    private String senha;

    private String email;

    private String funcao;

    private String setor;
}
