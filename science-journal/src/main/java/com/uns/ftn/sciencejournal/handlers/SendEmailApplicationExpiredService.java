package com.uns.ftn.sciencejournal.handlers;

import com.uns.ftn.sciencejournal.model.common.PaperApplication;
import com.uns.ftn.sciencejournal.model.users.UserDetails;
import com.uns.ftn.sciencejournal.repository.common.PaperApplicationRepository;
import com.uns.ftn.sciencejournal.repository.users.CredentialsRepository;
import com.uns.ftn.sciencejournal.service.utils.MailService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SendEmailApplicationExpiredService implements JavaDelegate {

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    PaperApplicationRepository paperApplicationRepository;

    @Autowired
    MailService mailService;

    @Autowired
    CredentialsRepository credentialsRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        String subject = "Paper structure revision";

        Long paperId = (Long) runtimeService.getVariable(delegateExecution.getId(), "applicationId");
        String sendTo = runtimeService.getVariable(delegateExecution.getId(), "initiator").toString();

        String text = "Your application with id " + paperId + " has not been updated before given deadline." +
                "It has now been automatically rejected.";

        PaperApplication app = paperApplicationRepository.getOne(paperId);
        app.setAccepted(false);
        paperApplicationRepository.save(app);

        UserDetails recepient = credentialsRepository.findFirstByUsername(sendTo).getUserDetails();
        mailService.sendEmail(recepient.getEmail(), subject, text);
    }
}
