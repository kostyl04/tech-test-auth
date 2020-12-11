package com.visionmate.auth.domain.repository;

import com.visionmate.auth.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Integer id);

    Role getById(Integer id);
}
