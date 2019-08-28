package com.uns.ftn.sciencejournal.handlers;

import com.uns.ftn.sciencejournal.model.common.Magazine;
import com.uns.ftn.sciencejournal.model.common.PaperApplication;
import com.uns.ftn.sciencejournal.model.common.ScienceField;
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
import java.util.List;
import java.util.Optional;
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

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Override
    public void notify(DelegateTask delegateTask) {

        DelegateExecution delegateExecution = delegateTask.getExecution();

        String issn = runtimeService.getVariable(delegateExecution.getId(), "magazine").toString();
        String author = runtimeService.getVariable(delegateExecution.getId(), "initiator").toString();
        String title = runtimeService.getVariable(delegateExecution.getId(), "title").toString();
        String coauthors = runtimeService.getVariable(delegateExecution.getId(), "coauthors").toString();
        String anAbstract = runtimeService.getVariable(delegateExecution.getId(), "abstract").toString();
        String field = runtimeService.getVariable(delegateExecution.getId(), "field").toString();
        String keyterms = runtimeService.getVariable(delegateExecution.getId(), "keywords").toString();
        String pdf = runtimeService.getVariable(delegateExecution.getId(), "pdf").toString();

        PaperApplication paperApplication = new PaperApplication();
        paperApplication.setAuthor(credentialsRepository.getOne(author));
        paperApplication.setKeyTerms(keyterms);
        paperApplication.setPaperAbstract(anAbstract);
        paperApplication.setTitle(title);

        Set<UserDetails> coauthorsDb = Arrays.stream(coauthors.split(","))
                .map(coauthor -> {
                    Optional<UserDetails> dbCoauthor = userDetailsRepository.findFirstByEmail(coauthor);

                    UserDetails returnVal = null;
                    if (dbCoauthor.isPresent()) {
                        returnVal = dbCoauthor.get();
                    } else {
                        returnVal = new UserDetails(null, "", "", "", "", coauthor, .0, .0);
                        returnVal = userDetailsRepository.save(returnVal);
                    }

                    return returnVal;
                })
                .collect(Collectors.toSet());

        paperApplication.setCoauthors(coauthorsDb);

        String errorMsg = "";

        Magazine magazine = magazineRepository.getOne(issn);
        paperApplication.setMagazine(magazine);
        List<ScienceField> fields = scienceFieldRepository.findByName(field);

        if (fields.isEmpty()) {
            errorMsg = "Nepostojeca oblast. Moguce oblasti su: " + magazine.getFields().stream().map(ScienceField::getName).collect(Collectors.joining(","));
        } else {
            paperApplication.setField(scienceFieldRepository.findByName(field).get(0));

            paperApplication.setTimestamp(LocalDate.now());
            paperApplication.setAccepted(null);
            paperApplication.setState(PaperApplicationState.THEME_REVISION);
            paperApplication.setFile(pdf);

            Long paperId = (Long) runtimeService.getVariable(delegateExecution.getId(), "applicationId");
            if (paperId != null) {
                PaperApplication existingApp = paperApplicationRepository.getOne(paperId);
                paperApplication.setVersion(existingApp.getVersion() + 1);
            } else {
                paperApplication.setVersion(0);
            }

            if (PaperApplicationState.MAJOR_PAPER_CORRECTION.equals(paperApplication.getState())) {
                paperApplication.setState(PaperApplicationState.REVIEW);
            }

            paperApplication = paperApplicationRepository.save(paperApplication);

            runtimeService.setVariable(delegateExecution.getId(), "applicationId", paperApplication.getId());
        }

        runtimeService.setVariable(delegateExecution.getId(), "save_app_error", errorMsg);

        if (!"".equals(errorMsg)) {
            throw new ValidationException(errorMsg);
        }
    }
}
