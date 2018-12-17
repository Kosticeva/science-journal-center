package com.uns.ftn.sciencejournal.service.users;

import com.uns.ftn.sciencejournal.model.users.Credentials;
import com.uns.ftn.sciencejournal.repository.users.CredentialsRepository;
import com.uns.ftn.sciencejournal.repository.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialsService {

    @Autowired
    CredentialsRepository credentialsRepository;

    @Autowired
    UserRepository userRepository;

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

        if (!checkCredentialsValidity(credentials)) {
            return null;
        }

        return credentialsRepository.save(credentials);
    }

    public Credentials updateCredentials(Credentials newCredentials, String id) {

        if (id == null) {
            return null;
        }

        Credentials credentials = getById(id);
        if (credentials == null) {
            return null;
        }

        if (!checkCredentialsValidity(newCredentials)) {
            return null;
        }

        credentials.setPassword(newCredentials.getPassword());
        credentials.setUserDetails(newCredentials.getUserDetails());

        return credentialsRepository.save(credentials);
    }

    private boolean checkCredentialsValidity(Credentials credentials) {
        if (credentials.getPassword() == null || credentials.getPassword().equals("")) {
            return false;
        }

        if (credentials.getUserDetails() == null || credentials.getUserDetails().getUserId() == null) {
            return false;
        }

        if (userRepository.getOne(credentials.getUserDetails().getUserId()) == null) {
            return false;
        }

        return true;
    }

    public void deleteCredentials(String id) {
        if (id != null) {
            credentialsRepository.deleteById(id);
        }
    }


}
