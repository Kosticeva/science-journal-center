package com.uns.ftn.sciencejournal.handlers;

import com.uns.ftn.sciencejournal.model.users.Credentials;
import com.uns.ftn.sciencejournal.model.users.UserDetails;
import com.uns.ftn.sciencejournal.repository.users.CredentialsRepository;
import com.uns.ftn.sciencejournal.repository.users.UserDetailsRepository;
import com.uns.ftn.sciencejournal.service.users.CredentialsService;
import com.uns.ftn.sciencejournal.service.users.UserDetailsService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserPersistenceService implements JavaDelegate {

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    CredentialsService credentialsService;

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        Map<String, Object> formVariables = execution.getVariables();
        UserDetails userDetails = new UserDetails();
        userDetails.setEmail(formVariables.get("email").toString());
        userDetails.setfName(formVariables.get("firstName").toString());
        userDetails.setlName(formVariables.get("lastName").toString());
        userDetails.setCity(formVariables.get("city").toString());
        userDetails.setCountry(formVariables.get("country").toString());

        userDetails = userDetailsService.createUser(userDetails);

        Credentials credentials = new Credentials();
        credentials.setUsername(formVariables.get("id").toString());
        credentials.setPassword(formVariables.get("password").toString());
        credentials.setUserDetails(userDetails);

        credentials = credentialsService.createCredentials(credentials);
        runtimeService.removeVariables(execution.getId(), formVariables.keySet());
    }
}

