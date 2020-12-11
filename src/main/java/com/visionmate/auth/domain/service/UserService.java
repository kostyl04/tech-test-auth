package com.visionmate.auth.domain.service;

import com.visionmate.auth.domain.entity.Role;
import com.visionmate.auth.domain.entity.User;
import com.visionmate.auth.domain.repository.UserRepository;
import com.visionmate.auth.util.exception.BadParameter;
import com.visionmate.auth.util.exception.EntityNotFound;
import com.visionmate.auth.util.mapper.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.hibernate.Hibernate.unproxy;
import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final Mapper mapper;
    private final PasswordEncoder passwordEncoder;

    public User save(User user) {
        return userRepository.save(user);
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @Transactional(isolation = SERIALIZABLE)
    public User update(User user) {
        if (!userRepository.existsById(user.getId())) {
            throw new EntityNotFound("user.not.found", user.getId());
        }
        checkUser(user);
        User oldUser = userRepository.getById(user.getId());
        if (nonNull(user.getRole())) {
            Role role = roleService.getRole(user.getRole().getId());
            if (isNull(role)) {
                throw new BadParameter("nonexistent.role", user.getRole().getId());
            }
            user.setRole(role);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User updatedUser = mapper.map(user, unproxy(oldUser, User.class));
        return userRepository.save(updatedUser);

    }

    public void checkUser(User user) {
        if (isEmpty(user.getName())) {
            throw new BadParameter("user.name.could.not.be.null");
        }
        if (nonNull(user.getId())) {
            if (userRepository.existsByNameAndIdNot(user.getName(), user.getId())) {
                throw new BadParameter("user.name.already.exists", user.getName());
            }
        } else {
            if (userRepository.existsByName(user.getName())) {
                throw new BadParameter("user.name.already.exists", user.getName());
            }
        }
        if (isEmpty(user.getPassword())) {
            throw new BadParameter("user.password.could.not.be.null");
        }
    }

    public void deleteById(Integer id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        }
    }
}
