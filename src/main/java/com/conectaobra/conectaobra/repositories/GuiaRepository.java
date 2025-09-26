package com.conectaobra.conectaobra.repositories;

import com.conectaobra.conectaobra.models.Guia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface GuiaRepository extends JpaRepository<Guia, UUID> {

    public Optional<Guia> findById(UUID uuid);
}
