package com.conectaobra.repositories.specs;

import com.conectaobra.models.Cliente;
import jakarta.annotation.Nullable;
import org.springframework.data.jpa.domain.Specification;

public class ClienteSpecs {
    public static Specification<Cliente> nomeLike(@Nullable String nome){
        return (root, query, cb) -> cb.like( cb.upper(root.get("nome")), "%" + nome.toUpperCase() + "%");
    }

    public static Specification<Cliente> contatoLike(@Nullable String contato){
        return (root, query, cb) -> cb.like(cb.upper(root.get("contato")), "%" + contato.toUpperCase() + "%");
    }
    public static Specification<Cliente> localizacaoLike(@Nullable String localizacao){
        return (root, query, cb) -> cb.like(cb.upper(root.get("localizacaoSede")), "%" + localizacao.toUpperCase() + "%");
    }
}
