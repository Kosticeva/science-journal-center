package com.uns.ftn.sciencejournal.handlers;

import com.uns.ftn.sciencejournal.model.users.UserDetails;
import com.uns.ftn.sciencejournal.repository.users.CredentialsRepository;
import com.uns.ftn.sciencejournal.service.utils.MailService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SendEmailApplicationRejectedService implements JavaDelegate {

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    MailService mailService;

    @Autowired
    CredentialsRepository credentialsRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        String subject = "Paper rejected";

        Long paperId = (Long) runtimeService.getVariable(delegateExecution.getId(), "applicationId");
        String sendTo = runtimeService.getVariable(delegateExecution.getId(), "initiator").toString();

        String text = "Your application with id " + paperId + " has been rejected.";

        UserDetails recepient = credentialsRepository.findFirstByUsername(sendTo).getUserDetails();
        mailService.sendEmail(recepient.getEmail(), subject, text);
    }

}
