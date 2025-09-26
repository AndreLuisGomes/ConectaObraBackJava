package com.conectaobra.conectaobra.repositories;

import com.conectaobra.conectaobra.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
}
