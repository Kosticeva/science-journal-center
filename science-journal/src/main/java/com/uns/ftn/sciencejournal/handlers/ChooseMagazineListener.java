package com.uns.ftn.sciencejournal.handlers;

import com.uns.ftn.sciencejournal.model.common.Magazine;
import com.uns.ftn.sciencejournal.repository.common.MagazineRepository;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChooseMagazineListener implements TaskListener {

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    MagazineRepository magazineRepository;

    @Override
    public void notify(DelegateTask delegateTask) {

        DelegateExecution delegateExecution = delegateTask.getExecution();

        String magazineTitle = runtimeService.getVariable(delegateExecution.getId(), "magazine_title").toString();
        Optional<Magazine> dbMagazine = magazineRepository.findFirstByName(magazineTitle);

        if (dbMagazine.isPresent()) {
            runtimeService.setVariable(delegateExecution.getId(), "magazine_title", dbMagazine.get().getName());
            runtimeService.setVariable(delegateExecution.getId(), "magazine", dbMagazine.get().getIssn());
        } else {
            throw new ValidationException("Magazin sa datim nazivom ne postoji. Moguci magazini su: " +
                    magazineRepository.findAll().stream().map(Magazine::getName).collect(Collectors.joining(",")));
        }
    }
}
