package com.conectaobra.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_role")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @Column(unique = true,name = "nome")
    private String nome;
}
