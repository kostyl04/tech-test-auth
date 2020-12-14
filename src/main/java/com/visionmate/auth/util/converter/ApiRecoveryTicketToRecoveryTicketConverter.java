package com.visionmate.auth.util.converter;

import com.visionmate.auth.domain.entity.RecoveryTicket;
import com.visionmate.auth.rest.model.ApiRecoveryTicket;
import com.visionmate.auth.util.mapper.BaseConverter;
import org.springframework.stereotype.Component;

@Component
public class ApiRecoveryTicketToRecoveryTicketConverter extends BaseConverter<ApiRecoveryTicket, RecoveryTicket> {

    @Override
    public RecoveryTicket convert(ApiRecoveryTicket from, RecoveryTicket to) {
        to.setSecret(from.getSecret());
        to.setEmail(from.getEmail());
        to.setNewPassword(from.getNewPassword());
        return to;
    }
}
