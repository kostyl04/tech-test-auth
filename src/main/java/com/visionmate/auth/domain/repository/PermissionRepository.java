package com.visionmate.auth.domain.repository;

import com.visionmate.auth.domain.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {

    Set<Permission> getByIdIn(List<Integer> ids);
}
