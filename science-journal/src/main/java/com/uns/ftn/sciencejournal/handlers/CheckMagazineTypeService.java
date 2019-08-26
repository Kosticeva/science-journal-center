package com.uns.ftn.sciencejournal.handlers;

import com.uns.ftn.sciencejournal.model.common.Magazine;
import com.uns.ftn.sciencejournal.model.enums.MagazinePaymentType;
import com.uns.ftn.sciencejournal.repository.common.MagazineRepository;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckMagazineTypeService implements JavaDelegate {

    @Autowired
    MagazineRepository magazineRepository;

    @Autowired
    RuntimeService runtimeService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        String issn = runtimeService.getVariable(delegateExecution.getId(), "magazine").toString();
        Magazine magazine = magazineRepository.getOne(issn);

        boolean openAccess = magazine.getType().equals(MagazinePaymentType.OPEN_ACCESS);
        runtimeService.setVariable(delegateExecution.getId(), "open_access", openAccess);
    }
}
