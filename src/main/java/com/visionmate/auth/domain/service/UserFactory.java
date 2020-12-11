package com.visionmate.auth.domain.service;

import com.visionmate.auth.domain.entity.Role;
import com.visionmate.auth.domain.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Objects.nonNull;
import static org.hibernate.Hibernate.unproxy;
import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;

@Service
@AllArgsConstructor
public class UserFactory {

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Transactional(isolation = SERIALIZABLE)
    public User create(User user) {
        userService.checkUser(user);
        if (nonNull(user.getRole())) {
            Role role = roleService.getRole(user.getRole().getId());
            if (nonNull(role)) {
                user.setRole(unproxy(role, Role.class));
            }
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userService.save(user);
    }
}
