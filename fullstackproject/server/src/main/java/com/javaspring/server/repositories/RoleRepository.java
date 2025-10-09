package com.javaspring.server.repositories;

import com.javaspring.server.model.AppRole;
import com.javaspring.server.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByRoleName(AppRole appRole);
}
