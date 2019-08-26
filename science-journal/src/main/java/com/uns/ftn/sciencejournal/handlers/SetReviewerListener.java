package com.uns.ftn.sciencejournal.handlers;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetReviewerListener implements TaskListener {

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    TaskService taskService;

    @Override
    public void notify(DelegateTask delegateTask) {

        DelegateExecution delegateExecution = delegateTask.getExecution();

        String reviewer;
        Object newReviewer = runtimeService.getVariable(delegateExecution.getId(), "new_reviewer");
        if(newReviewer == null) {
            Object reviewer1 = runtimeService.getVariable(delegateExecution.getId(), "reviewer1");
            if(reviewer1 == null) {
                reviewer = runtimeService.getVariable(delegateExecution.getId(), "reviewer2").toString();
                runtimeService.removeVariable(delegateExecution.getId(), "reviewer2");
            } else {
                reviewer = reviewer1.toString();
                runtimeService.removeVariable(delegateExecution.getId(), "reviewer1");
            }
        } else {
            reviewer = newReviewer.toString();
            runtimeService.removeVariable(delegateExecution.getId(), "new_reviewer");
        }

        delegateTask.setAssignee(reviewer);
    }
}
