package com.uns.ftn.sciencejournal.handlers;

import com.uns.ftn.sciencejournal.model.users.UserDetails;
import com.uns.ftn.sciencejournal.repository.users.CredentialsRepository;
import com.uns.ftn.sciencejournal.service.utils.MailService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class SendEmailStructureBadService implements JavaDelegate {

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    MailService mailService;

    @Autowired
    CredentialsRepository credentialsRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        String subject = "Paper structure revision";

        Long paperId = (Long) runtimeService.getVariable(delegateExecution.getId(), "applicationId");
        OffsetDateTime deadline =  OffsetDateTime.parse(runtimeService.getVariable(delegateExecution.getId(), "structure_deadline").toString());
        String sendTo = runtimeService.getVariable(delegateExecution.getId(), "initiator").toString();

        String text = "Your application with id " + paperId + " has been reviewed. It was found that the structure is bad." +
                "Please update your application until " + deadline + ".";

        UserDetails recepient = credentialsRepository.findFirstByUsername(sendTo).getUserDetails();
        mailService.sendEmail(recepient.getEmail(), subject, text);
    }
}
