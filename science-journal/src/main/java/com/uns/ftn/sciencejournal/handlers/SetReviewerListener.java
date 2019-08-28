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

        Boolean taskOneSet = (Boolean) runtimeService.getVariable(delegateExecution.getId(), "taskSet");
        String reviewer1 = runtimeService.getVariable(delegateExecution.getId(), "reviewer1").toString();
        String reviewer2 = runtimeService.getVariable(delegateExecution.getId(), "reviewer2").toString();

        if(taskOneSet != null && taskOneSet) {
            delegateTask.setAssignee(reviewer2);
            runtimeService.setVariable(delegateExecution.getId(), "taskSet", false);
        } else {
            delegateTask.setAssignee(reviewer1);
            runtimeService.setVariable(delegateExecution.getId(), "taskSet", true);
        }

        runtimeService.removeVariable(delegateExecution.getId(), "correction_deadline");
    }
}
