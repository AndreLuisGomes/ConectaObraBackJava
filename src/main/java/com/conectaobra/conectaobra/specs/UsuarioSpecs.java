package com.conectaobra.conectaobra.specs;

import com.conectaobra.conectaobra.enums.Funcao;
import com.conectaobra.conectaobra.enums.Setor;
import com.conectaobra.conectaobra.models.Usuario;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class UsuarioSpecs{
    public static Specification<Usuario> obterPorNome(String nome){
        return (root, query, cb) -> nome == null ? cb.conjunction() : cb.like(root.get("nome"), nome);
    }

    public static Specification<Usuario> obterPorFuncao(Funcao funcao){
        return (root, query, cb) -> funcao == null ? cb.conjunction() : cb.equal(root.get("funcao"), funcao);
    }

    public static Specification<Usuario> obterPorSetor(Setor setor){
        return (root, query, cb) -> setor == null ? cb.conjunction() : cb.equal(root.get("setor"), setor);
    }
}
