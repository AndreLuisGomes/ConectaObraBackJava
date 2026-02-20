package com.conectaobra.repositories.specs;

import com.conectaobra.enums.Funcao;
import com.conectaobra.models.Usuario;
import org.springframework.data.jpa.domain.Specification;

public class UsuarioSpecs{
    public static Specification<Usuario> nomeLike(String nome){
        return (root, query, cb) -> nome == null ? cb.conjunction() : cb.like(root.get("nome"), nome);
    }

    public static Specification<Usuario> funcaoLike(Funcao funcao){
        return (root, query, cb) -> funcao == null ? cb.conjunction() : cb.equal(root.get("funcao"), funcao);
    }

    public static Specification<Usuario> setorLike(String setor){
        return (root, query, cb) -> setor == null ? cb.conjunction() : cb.equal(root.get("setor"), setor);
    }
}
