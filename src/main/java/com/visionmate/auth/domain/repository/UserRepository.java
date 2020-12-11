package com.visionmate.auth.domain.repository;

import com.visionmate.auth.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Integer id);

    User getById(Integer id);
}
