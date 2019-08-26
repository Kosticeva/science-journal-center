package com.uns.ftn.sciencejournal.handlers;

import com.uns.ftn.sciencejournal.model.common.PaperApplication;
import com.uns.ftn.sciencejournal.model.enums.PaperApplicationState;
import com.uns.ftn.sciencejournal.repository.common.PaperApplicationRepository;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaperApplicationReviewEndProposalEndListener implements TaskListener {

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    PaperApplicationRepository paperApplicationRepository;

    @Override
    public void notify(DelegateTask delegateTask) {

        //assign tasks to reviewers

        DelegateExecution delegateExecution = delegateTask.getExecution();

        Long paperId = (Long) runtimeService.getVariable(delegateExecution.getId(), "applicationId");
        PaperApplication app = paperApplicationRepository.getOne(paperId);

        String decision = runtimeService.getVariable(delegateExecution.getId(), "decision").toString();

        switch (decision) {
            case "yes":
                app.setAccepted(true);
                break;
            case "minor":
                app.setState(PaperApplicationState.MINOR_PAPER_CORRECTION);
                break;
            case "major":
                app.setState(PaperApplicationState.MAJOR_PAPER_CORRECTION);
                break;
            default:
                app.setAccepted(false);
        }

        paperApplicationRepository.save(app);
    }
}