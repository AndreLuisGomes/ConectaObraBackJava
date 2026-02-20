package com.conectaobra.repositories;

import com.conectaobra.models.Suporte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SuporteRepository extends JpaRepository<Suporte, UUID> {

    List<Suporte> findByTag(String tag);
}
