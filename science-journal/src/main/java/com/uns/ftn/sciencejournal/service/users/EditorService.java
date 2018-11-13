package com.uns.ftn.sciencejournal.service.users;

import com.uns.ftn.sciencejournal.model.users.Editor;
import com.uns.ftn.sciencejournal.repository.users.EditorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EditorService {

    @Autowired
    EditorRepository editorRepository;

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

        return editorRepository.save(editor);
    }

    public Editor updateEditor(Editor newEditor, Integer id) {

        if (id == null) {
            return null;
        }

        Editor editor = getById(id);
        if (editor != null) {

            return editorRepository.save(editor);
        }

        return null;
    }

    public void deleteEditor(Integer id) {
        if (id != null) {
            editorRepository.deleteById(id);
        }
    }


}
