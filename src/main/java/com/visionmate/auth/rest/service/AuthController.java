package com.visionmate.auth.rest.service;

import com.visionmate.auth.domain.entity.AccessToken;
import com.visionmate.auth.domain.entity.RecoveryTicket;
import com.visionmate.auth.domain.service.RecoveryTicketService;
import com.visionmate.auth.domain.service.TokenFactory;
import com.visionmate.auth.rest.model.ApiAccessToken;
import com.visionmate.auth.rest.model.ApiRecoveryTicket;
import com.visionmate.auth.util.mapper.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/auth", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class AuthController {

    private final Mapper mapper;
    private final TokenFactory tokenFactory;
    private final RecoveryTicketService recoveryTicketService;

    @PostMapping
    @PreAuthorize("isAnonymous()")
    @ResponseStatus(CREATED)
    public ApiAccessToken createAccessToken(@RequestBody ApiAccessToken apiAccessToken) {
        AccessToken accessToken = tokenFactory.create(mapper.map(apiAccessToken, AccessToken.class));
        return mapper.map(accessToken, ApiAccessToken.class);
    }

    @PostMapping
    @PreAuthorize("isAnonymous()")
    @RequestMapping("/recoveryTicket")
    @ResponseStatus(CREATED)
    public ApiRecoveryTicket createRecoveryTicket(@RequestBody ApiRecoveryTicket apiRecoveryTicket) {
        RecoveryTicket ticket = mapper.map(apiRecoveryTicket, RecoveryTicket.class);
        RecoveryTicket recoveryTicket = recoveryTicketService.create(ticket);
        return mapper.map(recoveryTicket, ApiRecoveryTicket.class);
    }

    @PostMapping
    @RequestMapping("/recovery")
    @PreAuthorize("isAnonymous()")
    @ResponseStatus(CREATED)
    public ApiAccessToken confirmRecovery(@RequestBody ApiRecoveryTicket apiRecoveryTicket) {
        RecoveryTicket ticket = mapper.map(apiRecoveryTicket, RecoveryTicket.class);
        AccessToken accessToken = recoveryTicketService.confirmRecovery(ticket);
        return mapper.map(accessToken, ApiAccessToken.class);
    }
}
