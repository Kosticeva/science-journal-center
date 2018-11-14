package com.uns.ftn.sciencejournal.mapper.users;

import com.uns.ftn.sciencejournal.dto.users.CredentialsDTO;
import com.uns.ftn.sciencejournal.model.users.Credentials;
import com.uns.ftn.sciencejournal.repository.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CredentialsMapper {

    @Autowired
    UserRepository userRepository;

    public Credentials mapFromDTO(CredentialsDTO dto) {
        Credentials credentials = new Credentials();

        credentials.setPassword(dto.getPassword());
        credentials.setUsername(dto.getUsername());
        credentials.setUserDetails(userRepository.getOne(dto.getUserDetails()));

        return credentials;
    }

    public CredentialsDTO mapToDTO(Credentials credentials) {
        CredentialsDTO dto = new CredentialsDTO();

        dto.setPassword(credentials.getPassword());
        dto.setUserDetails(credentials.getUserDetails().getUserId());
        dto.setUsername(credentials.getUsername());

        return dto;
    }
}
