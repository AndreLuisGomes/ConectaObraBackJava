package com.conectaobra.conectaobra.repositories.specs;

import com.conectaobra.conectaobra.models.Guia;
import jakarta.annotation.Nullable;
import org.springframework.data.jpa.domain.Specification;

public class GuiaSpecs {
    public static Specification<Guia> localLike(@Nullable String local){
        return (root, query, cb) -> cb.like(cb.upper(root.get("local")), "%" + local.toUpperCase() + "%");
    }

    public static Specification<Guia> nomeLike(@Nullable String nome) {
        return (root, query, cb) -> cb.like(cb.upper(root.get("nome")), "%" + nome.toUpperCase() + "%");
    }

    public static Specification<Guia> guiaStatusEqual(@Nullable String guiaStatus){
        return (root, query, cb) -> cb.equal(root.get("guiaStatus"), guiaStatus);
    }

    public static Specification<Guia> nomeClienteLike(@Nullable String nomeCliente){
        return (root, query, cb) -> cb.equal(cb.upper(root.get("nomeCliente")), "%" + nomeCliente.toUpperCase() + "%");
    }
}
