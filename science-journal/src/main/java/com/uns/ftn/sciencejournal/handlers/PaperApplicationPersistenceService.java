package com.uns.ftn.sciencejournal.handlers;

import com.uns.ftn.sciencejournal.model.common.PaperApplication;
import com.uns.ftn.sciencejournal.model.enums.PaperApplicationState;
import com.uns.ftn.sciencejournal.model.users.UserDetails;
import com.uns.ftn.sciencejournal.repository.common.MagazineRepository;
import com.uns.ftn.sciencejournal.repository.common.PaperApplicationRepository;
import com.uns.ftn.sciencejournal.repository.common.ScienceFieldRepository;
import com.uns.ftn.sciencejournal.repository.users.CredentialsRepository;
import com.uns.ftn.sciencejournal.repository.users.UserDetailsRepository;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PaperApplicationPersistenceService implements TaskListener {

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    CredentialsRepository credentialsRepository;

    @Autowired
    PaperApplicationRepository paperApplicationRepository;

    @Autowired
    ScienceFieldRepository scienceFieldRepository;

    @Autowired
    MagazineRepository magazineRepository;

    @Override
    public void notify(DelegateTask delegateTask) {

        DelegateExecution delegateExecution = delegateTask.getExecution();

        String issn = runtimeService.getVariable(delegateExecution.getId(), "magazine").toString();
        String author = runtimeService.getVariable(delegateExecution.getId(), "initiator").toString();
        String title = runtimeService.getVariable(delegateExecution.getId(), "title").toString();
        String coauthors = runtimeService.getVariable(delegateExecution.getId(), "coauthors").toString();
        String anAbstract = runtimeService.getVariable(delegateExecution.getId(), "abstract").toString();
        String field = runtimeService.getVariable(delegateExecution.getId(), "field").toString();
        String keyterms = runtimeService.getVariable(delegateExecution.getId(), "keyterms").toString();
        String pdf = runtimeService.getVariable(delegateExecution.getId(), "pdf").toString();

        PaperApplication paperApplication = new PaperApplication();
        paperApplication.setAuthor(credentialsRepository.getOne(author));
        paperApplication.setKeyTerms(keyterms);

        Set<UserDetails> coauthorsDb = Arrays.stream(coauthors.split(","))
                .map(coauthor -> credentialsRepository.getOne(coauthor).getUserDetails())
                .collect(Collectors.toSet());

        paperApplication.setCoauthors(coauthorsDb);

        paperApplication.setField(scienceFieldRepository.findByName(field).get(0));
        paperApplication.setMagazine(magazineRepository.getOne(issn));
        paperApplication.setPaperAbstract(anAbstract);
        paperApplication.setTitle(title);

        paperApplication.setTimestamp(LocalDate.now());
        paperApplication.setAccepted(null);
        paperApplication.setState(PaperApplicationState.THEME_REVISION);
        paperApplication.setFile(pdf);

        Long paperId = (Long) runtimeService.getVariable(delegateExecution.getId(), "applicationId");
        if(paperId != null) {
            PaperApplication existingApp = paperApplicationRepository.getOne(paperId);
            paperApplication.setVersion(existingApp.getVersion() + 1);
        } else {
            paperApplication.setVersion(0);
        }

        if(PaperApplicationState.MAJOR_PAPER_CORRECTION.equals(paperApplication.getState())) {
            paperApplication.setState(PaperApplicationState.REVIEW);
        }

        paperApplication = paperApplicationRepository.save(paperApplication);

        runtimeService.setVariable(delegateExecution.getId(), "applicationId", paperApplication.getId());
    }
}
