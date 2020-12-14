package com.visionmate.auth.util.converter;

import com.visionmate.auth.domain.entity.RecoveryTicket;
import com.visionmate.auth.rest.model.ApiRecoveryTicket;
import com.visionmate.auth.util.mapper.BaseConverter;
import org.springframework.stereotype.Component;

@Component
public class RecoveryTicketToApiRecoveryTicketConverter extends BaseConverter<RecoveryTicket, ApiRecoveryTicket> {

    @Override
    public ApiRecoveryTicket convert(RecoveryTicket from, ApiRecoveryTicket to) {
        to.setEmail(from.getEmail());
        to.setId(from.getId());
        return to;
    }
}
