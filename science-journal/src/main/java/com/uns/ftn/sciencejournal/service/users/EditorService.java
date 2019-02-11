package com.uns.ftn.sciencejournal.service.users;

import com.uns.ftn.sciencejournal.model.users.Editor;
import com.uns.ftn.sciencejournal.repository.common.MagazineRepository;
import com.uns.ftn.sciencejournal.repository.common.ScienceFieldRepository;
import com.uns.ftn.sciencejournal.repository.users.CredentialsRepository;
import com.uns.ftn.sciencejournal.repository.users.EditorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EditorService {

    @Autowired
    EditorRepository editorRepository;

    @Autowired
    CredentialsRepository credentialsRepository;

    @Autowired
    MagazineRepository magazineRepository;

    @Autowired
    ScienceFieldRepository scienceFieldRepository;

    public Editor getChiefEditorOfMagazine(String issn) {
        return editorRepository.findFirstByMagazineAndField(magazineRepository.getOne(issn), null);
    }

    public Editor getById(Integer id) {
        return editorRepository.findById(id).orElse(null);
    }

    public List<Editor> getAll() {
        return editorRepository.findAll();
    }

    public Editor createEditor(Editor editor) {

        if (editor.getId() != null) {
            return null;
        }

        if (!checkEditorValidity(editor)) {
            return null;
        }

        return editorRepository.save(editor);
    }

    public Editor updateEditor(Editor newEditor, Integer id) {

        if (id == null) {
            return null;
        }

        Editor editor = getById(id);
        if (editor == null) {
            return null;
        }

        if (!checkEditorValidity(newEditor)) {
            return null;
        }

        editor.setField(newEditor.getField());
        editor.setMagazine(newEditor.getMagazine());
        editor.setTitle(newEditor.getTitle());
        editor.setUser(newEditor.getUser());

        return editorRepository.save(editor);
    }

    private boolean checkEditorValidity(Editor editor) {

        if (editor.getTitle() == null || editor.getTitle().equals("")) {
            return false;
        }

        if (editor.getUser() == null || editor.getUser().getUsername() == null) {
            return false;
        }

        if (credentialsRepository.getOne(editor.getUser().getUsername()) == null) {
            return false;
        }

        if (editor.getMagazine() == null || editor.getMagazine().getIssn().equals("")) {
            return false;
        }

        if (magazineRepository.getOne(editor.getMagazine().getIssn()) == null) {
            return false;
        }

        if (editor.getField() != null && editor.getField().getCode().equals("")) {
            return false;
        }

        if (editor.getField() != null && scienceFieldRepository.getOne(editor.getField().getCode()) == null) {
            return false;
        }

        return true;
    }

    public void deleteEditor(Integer id) {
        if (id != null) {
            editorRepository.deleteById(id);
        }
    }


}
