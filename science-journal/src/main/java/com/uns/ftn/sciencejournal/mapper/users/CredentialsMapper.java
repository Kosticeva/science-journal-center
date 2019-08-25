package com.uns.ftn.sciencejournal.mapper.users;

import com.uns.ftn.sciencejournal.dto.users.CredentialsDTO;
import com.uns.ftn.sciencejournal.model.users.Credentials;
import com.uns.ftn.sciencejournal.repository.users.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CredentialsMapper {

    @Autowired
    UserDetailsRepository userRepository;

    public Credentials mapFromDTO(CredentialsDTO dto) {
        Credentials credentials = new Credentials();

        credentials.setPassword(dto.getPassword());
        credentials.setUsername(dto.getUsername());
        if (dto.getUserDetails() != null) credentials.setUserDetails(userRepository.getOne(dto.getUserDetails()));

        return credentials;
    }

    public CredentialsDTO mapToDTO(Credentials credentials) {
        CredentialsDTO dto = new CredentialsDTO();

        dto.setPassword(credentials.getPassword());
        dto.setUsername(credentials.getUsername());
        if (credentials.getUserDetails() != null) dto.setUserDetails(credentials.getUserDetails().getUserId());

        return dto;
    }

    public List<Credentials> mapManyFromDTO(List<CredentialsDTO> credentialsDTOs) {
        List<Credentials> credentials = new ArrayList<>();
        for (CredentialsDTO credentialsDTO : credentialsDTOs) {
            credentials.add(mapFromDTO(credentialsDTO));
        }

        return credentials;
    }

    public List<CredentialsDTO> mapManyToDTO(List<Credentials> credentialss) {
        List<CredentialsDTO> credentialsDTOs = new ArrayList<>();
        for (Credentials credentials : credentialss) {
            credentialsDTOs.add(mapToDTO(credentials));
        }

        return credentialsDTOs;
    }
}