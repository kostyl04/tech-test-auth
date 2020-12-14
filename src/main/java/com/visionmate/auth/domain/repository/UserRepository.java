package com.visionmate.auth.domain.repository;

import com.visionmate.auth.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User getByName(String name);

    boolean existsByNameOrEmail(String name, String email);

    boolean existsByIdNotAndNameOrEmail(Integer id, String name, String email);

    User getById(Integer id);

    User getByEmail(String email);
}
