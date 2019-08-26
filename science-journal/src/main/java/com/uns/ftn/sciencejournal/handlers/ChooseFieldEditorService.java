package com.uns.ftn.sciencejournal.handlers;

import com.uns.ftn.sciencejournal.model.common.Magazine;
import com.uns.ftn.sciencejournal.model.common.PaperApplication;
import com.uns.ftn.sciencejournal.model.enums.PaperApplicationState;
import com.uns.ftn.sciencejournal.model.users.Editor;
import com.uns.ftn.sciencejournal.repository.common.MagazineRepository;
import com.uns.ftn.sciencejournal.repository.common.PaperApplicationRepository;
import com.uns.ftn.sciencejournal.repository.users.EditorRepository;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChooseFieldEditorService implements JavaDelegate {

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    MagazineRepository magazineRepository;

    @Autowired
    EditorRepository editorRepository;

    @Autowired
    PaperApplicationRepository paperApplicationRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        Long paperId = (Long) runtimeService.getVariable(delegateExecution.getId(), "applicationId");
        PaperApplication application = paperApplicationRepository.getOne(paperId);

        Editor fieldEditor = editorRepository.findFirstByMagazineAndField(application.getMagazine(), application.getField());
        if (fieldEditor != null) {
            runtimeService.setVariable(delegateExecution.getId(), "field_editor", fieldEditor.getUser().getUsername());
        } else {
            fieldEditor = editorRepository.findFirstByMagazineAndField(application.getMagazine(), null);
            runtimeService.setVariable(delegateExecution.getId(), "field_editor", fieldEditor.getUser().getUsername());
        }
    }
}
