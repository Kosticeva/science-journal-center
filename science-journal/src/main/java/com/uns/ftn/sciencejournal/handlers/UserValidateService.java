package com.uns.ftn.sciencejournal.handlers;

import com.uns.ftn.sciencejournal.model.users.Credentials;
import com.uns.ftn.sciencejournal.model.users.UserDetails;
import com.uns.ftn.sciencejournal.service.users.CredentialsService;
import com.uns.ftn.sciencejournal.service.users.UserDetailsService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserValidateService implements JavaDelegate {

    @Autowired
    RuntimeService runtimeService;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private CredentialsService credentialsService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        Map<String, Object> formVariables = delegateExecution.getVariables();
        UserDetails userDetails = new UserDetails();
        userDetails.setEmail(formVariables.get("email").toString());
        userDetails.setfName(formVariables.get("firstName").toString());
        userDetails.setlName(formVariables.get("lastName").toString());
        userDetails.setCity(formVariables.get("city").toString());
        userDetails.setCountry(formVariables.get("country").toString());

        Credentials credentials = new Credentials();
        credentials.setUsername(formVariables.get("id").toString());
        credentials.setPassword(formVariables.get("password").toString());
        credentials.setUserDetails(userDetails);

        runtimeService.setVariable(delegateExecution.getId(), "valid", checkValidity(credentials));
    }

    private boolean checkValidity(Credentials credentials) {

        if (credentials.getUsername() == null || credentials.getUsername().trim().equals("")) {
            return false;
        }

        if (credentialsService.getById(credentials.getUsername()) != null) {
            return false;
        }

        if (credentials.getPassword() == null || credentials.getPassword().trim().equals("")) {
            return false;
        }

        UserDetails userDetails = credentials.getUserDetails();

        if (userDetails.getfName() == null || userDetails.getfName().trim().equals("")) {
            return false;
        }

        if (userDetails.getlName() == null || userDetails.getlName().trim().equals("")) {
            return false;
        }

        if (userDetails.getCity() == null || userDetails.getCity().trim().equals("")) {
            return false;
        }

        if (userDetails.getCountry() == null || userDetails.getCountry().trim().equals("")) {
            return false;
        }

        if (userDetails.getEmail() == null || userDetails.getEmail().trim().equals("")) {
            return false;
        }

        return true;
    }
}
