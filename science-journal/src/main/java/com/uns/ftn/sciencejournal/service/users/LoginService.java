package com.uns.ftn.sciencejournal.service.users;

import org.springframework.stereotype.Service;

@Service
public class LoginService {

    /*@Autowired
    CredentialsRepository credentialsRepository;*/

    public /*Credentials*/String login(/*Credentials*/String credentials) {
        /*Credentials dbCredentials = credentialsRepository.findFirstByUsername(credentials.getUsername());
        if (dbCredentials != null) {
            //BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            //if(bCryptPasswordEncoder.matches(credentials.getPassword(), dbCredentials.getPassword())){
            return dbCredentials;
            //}
        }*/

        return null;
    }
}
