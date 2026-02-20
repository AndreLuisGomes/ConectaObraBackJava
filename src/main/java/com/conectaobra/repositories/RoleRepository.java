package com.conectaobra.repositories;

import com.conectaobra.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findById(Long i);

    Optional<Role> findByNome(String nome);
}
