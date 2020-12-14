package com.visionmate.auth.domain.repository;

import com.visionmate.auth.domain.entity.Client;
import org.springframework.data.repository.Repository;

public interface ClientRepository extends Repository<Client, String> {

    Client getById(String id);
}
