package com.visionmate.auth.rest.service;

import com.visionmate.auth.domain.entity.User;
import com.visionmate.auth.domain.service.UserFactory;
import com.visionmate.auth.domain.service.UserService;
import com.visionmate.auth.rest.model.ApiUser;
import com.visionmate.auth.util.mapper.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/users", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class UserController {

    private final Mapper mapper;
    private final UserFactory userFactory;
    private final UserService userService;

    @PostMapping
    @ResponseStatus(CREATED)
    public ApiUser create(@RequestBody ApiUser apiUser) {
        User user = mapper.map(apiUser, User.class);
        user.setId(null);
        user = userFactory.create(user);
        return mapper.map(user, ApiUser.class);
    }

    @GetMapping
    @ResponseStatus(OK)
    public List<ApiUser> getAll() {
        List<User> users = userService.getUsers();
        return mapper.mapToList(users, ApiUser.class);
    }

    @PutMapping("/{id}")
    @ResponseStatus(OK)
    public ApiUser update(@RequestBody ApiUser apiUser, @PathVariable Integer id) {
        User user = mapper.map(apiUser, User.class);
        user.setId(id);
        user = userService.update(user);
        return mapper.map(user, ApiUser.class);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        userService.deleteById(id);
    }

}
