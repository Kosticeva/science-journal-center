package com.uns.ftn.sciencejournal.service.users;

import com.uns.ftn.sciencejournal.model.users.Credentials;
import com.uns.ftn.sciencejournal.repository.users.CredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialsService {

    @Autowired
    CredentialsRepository credentialsRepository;

    public Credentials getById(String id) {
        return credentialsRepository.findById(id).orElse(null);
    }

    public List<Credentials> getAll() {
        return credentialsRepository.findAll();
    }

    public Credentials createCredentials(Credentials credentials) {

        if (credentials.getUsername() != null) {
            return null;
        }

        return credentialsRepository.save(credentials);
    }

    public Credentials updateCredentials(Credentials newCredentials, String id) {

        if (id == null) {
            return null;
        }

        Credentials credentials = getById(id);
        if (credentials != null) {

            return credentialsRepository.save(credentials);
        }

        return null;
    }

    public void deleteCredentials(String id) {
        if (id != null) {
            credentialsRepository.deleteById(id);
        }
    }


}
