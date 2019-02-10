package com.uns.ftn.sciencejournal.service.users;

import com.uns.ftn.sciencejournal.model.users.Credentials;
import com.uns.ftn.sciencejournal.repository.users.CredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    CredentialsRepository credentialsRepository;

    public Credentials login(Credentials credentials){
        Credentials dbCredentials = credentialsRepository.findFirstByUsername(credentials.getUsername());
        if(dbCredentials != null){
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            if(bCryptPasswordEncoder.matches(credentials.getPassword(), dbCredentials.getPassword())){
                return dbCredentials;
            }
        }

        return null;
    }
}
