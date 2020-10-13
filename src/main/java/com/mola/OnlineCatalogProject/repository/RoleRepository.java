package com.mola.OnlineCatalogProject.repository;

import com.mola.OnlineCatalogProject.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByRoleName(String role);
}
