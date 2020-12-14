package com.visionmate.auth.domain.service;

import com.visionmate.auth.domain.entity.Client;
import com.visionmate.auth.domain.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public Client getClient(String id){
        return clientRepository.getById(id);
    }
}
