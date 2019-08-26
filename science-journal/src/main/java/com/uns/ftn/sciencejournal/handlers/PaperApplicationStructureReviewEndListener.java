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
public class PaperApplicationStructureReviewEndListener implements TaskListener {

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    PaperApplicationRepository paperApplicationRepository;

    @Override
    public void notify(DelegateTask delegateTask) {

        DelegateExecution delegateExecution = delegateTask.getExecution();

        Long paperId = (Long) runtimeService.getVariable(delegateExecution.getId(), "applicationId");
        PaperApplication app = paperApplicationRepository.getOne(paperId);

        boolean decision = (boolean) runtimeService.getVariable(delegateExecution.getId(), "structure_appropriate");

        if(decision) {
            app.setState(PaperApplicationState.REVIEWER_PROPOSAL);
        } else {
            app.setState(PaperApplicationState.APPLICATION_CORRECTION);
        }

        paperApplicationRepository.save(app);
    }

}
