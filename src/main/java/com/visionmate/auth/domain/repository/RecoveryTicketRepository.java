package com.visionmate.auth.domain.repository;

import com.visionmate.auth.domain.entity.RecoveryTicket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecoveryTicketRepository extends JpaRepository<RecoveryTicket, Integer> {

    RecoveryTicket findBySecret(String secret);
}
