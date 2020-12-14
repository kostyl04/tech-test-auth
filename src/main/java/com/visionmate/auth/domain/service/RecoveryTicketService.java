package com.visionmate.auth.domain.service;

import com.visionmate.auth.domain.entity.AccessToken;
import com.visionmate.auth.domain.entity.Client;
import com.visionmate.auth.domain.entity.RecoveryTicket;
import com.visionmate.auth.domain.entity.User;
import com.visionmate.auth.domain.repository.RecoveryTicketRepository;
import com.visionmate.auth.util.exception.BadParameterException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

@Service
@AllArgsConstructor
public class RecoveryTicketService {

    private final TokenFactory tokenFactory;
    private final RecoveryTicketRepository recoveryTicketRepository;
    private final UserService userService;
    private final ClientService clientService;

    @Transactional
    public RecoveryTicket create(RecoveryTicket ticket) {
        User user = userService.getByEmail(ticket.getEmail());
        if (isNull(user)) {
            throw new BadParameterException("user.not.found", ticket.getEmail());
        }
        ticket.setSecret(randomAlphanumeric(36));
        ticket.setUser(user);
        return recoveryTicketRepository.save(ticket);
    }

    //TODO client credentials auth
    @Transactional
    public AccessToken confirmRecovery(RecoveryTicket ticket) {
        RecoveryTicket foundedTicket = recoveryTicketRepository.findBySecret(ticket.getSecret());
        if (isNull(foundedTicket)) {
            throw new BadParameterException("ticket.not.found");
        }
        foundedTicket.setNewPassword(ticket.getNewPassword());
        User user = foundedTicket.getUser();
        user.setPassword(foundedTicket.getNewPassword());
        user = userService.updatePassword(user);
        Client client = clientService.getClient("59489bdb-b586-403e-8789-d014cd5f5906");
        recoveryTicketRepository.delete(foundedTicket);
        return tokenFactory.create(user, client);
    }

    //TODO create email sender and send email with confirmation BFF link
    private void sendEmail(RecoveryTicket ticket) {

    }

}
