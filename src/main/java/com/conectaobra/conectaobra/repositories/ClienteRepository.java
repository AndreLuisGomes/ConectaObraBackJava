package com.conectaobra.conectaobra.repositories;

import com.conectaobra.conectaobra.models.Cliente;
import com.conectaobra.conectaobra.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID>, JpaSpecificationExecutor<Cliente> {

    @Override
    void deleteById(UUID uuid);

    List<Cliente> findByNomeContainingIgnoreCase(String nome);


}
