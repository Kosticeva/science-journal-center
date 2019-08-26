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
public class SendEmailEditorChosenService implements JavaDelegate {

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    MailService mailService;

    @Autowired
    CredentialsRepository credentialsRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        Boolean sentToEditor = (Boolean) runtimeService.getVariable(delegateExecution.getId(), "sent_to_editor");
        String sendTo;
        String subject = "Paper applied for revision";
        String text;

        Long paperId = (Long) runtimeService.getVariable(delegateExecution.getId(), "applicationId");
        if (sentToEditor != null && sentToEditor) {
            sendTo = runtimeService.getVariable(delegateExecution.getId(), "initiator").toString();
            text = "Your application with id " + paperId + " was received and an editor was assigned to review it. " +
                    "Please stand by for the response.";
            runtimeService.removeVariable(delegateExecution.getId(), "sent_to_editor");
        } else {
            sendTo = runtimeService.getVariable(delegateExecution.getId(), "magazine_editor").toString();
            text = "An application with id " + paperId + " was received and you have been assigned to review it.";
            runtimeService.setVariable(delegateExecution.getId(), "sent_to_editor", true);
        }

        UserDetails recepient = credentialsRepository.findFirstByUsername(sendTo).getUserDetails();
        mailService.sendEmail(recepient.getEmail(), subject, text);
    }
}
