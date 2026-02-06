package com.conectaobra.conectaobra.repositories;

import com.conectaobra.conectaobra.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findById(Long i);
}
