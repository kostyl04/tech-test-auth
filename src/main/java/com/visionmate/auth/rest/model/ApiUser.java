package com.visionmate.auth.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

@Getter
@Setter
public class ApiUser {

    private Integer id;
    private String username;
    @JsonProperty(access = WRITE_ONLY)
    private String password;
    private ApiRole role;
}
