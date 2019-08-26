package com.uns.ftn.sciencejournal.handlers;

import com.uns.ftn.sciencejournal.model.common.Magazine;
import com.uns.ftn.sciencejournal.model.users.Editor;
import com.uns.ftn.sciencejournal.repository.common.MagazineRepository;
import com.uns.ftn.sciencejournal.repository.users.EditorRepository;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChooseMagazineEditorService implements JavaDelegate {

    @Autowired
    EditorRepository editorRepository;

    @Autowired
    MagazineRepository magazineRepository;

    @Autowired
    RuntimeService runtimeService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        String issn = runtimeService.getVariable(delegateExecution.getId(), "magazine").toString();
        Magazine magazine = magazineRepository.getOne(issn);

        Editor chiefEditor = editorRepository.findFirstByMagazineAndField(magazine, null);
        if(chiefEditor != null) {
            runtimeService.setVariable(delegateExecution.getId(), "magazine_editor", chiefEditor.getUser().getUsername());
        }

        runtimeService.removeVariable(delegateExecution.getId(), "open_access");
    }
}
