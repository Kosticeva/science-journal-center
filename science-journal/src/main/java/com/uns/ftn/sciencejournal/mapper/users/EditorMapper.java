package com.uns.ftn.sciencejournal.mapper.users;

import com.uns.ftn.sciencejournal.dto.users.EditorDTO;
import com.uns.ftn.sciencejournal.model.users.Editor;
import com.uns.ftn.sciencejournal.repository.common.MagazineRepository;
import com.uns.ftn.sciencejournal.repository.common.ScienceFieldRepository;
import com.uns.ftn.sciencejournal.repository.users.CredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EditorMapper {

    @Autowired
    CredentialsRepository credentialsRepository;

    @Autowired
    MagazineRepository magazineRepository;

    @Autowired
    ScienceFieldRepository scienceFieldRepository;

    public Editor mapFromDTO(EditorDTO dto) {
        Editor editor = new Editor();

        if(dto.getUser() != null) editor.setUser(credentialsRepository.getOne(dto.getUser()));
        editor.setId(dto.getId());
        editor.setTitle(dto.getTitle());
        if(dto.getMagazine() != null) editor.setMagazine(magazineRepository.getOne(dto.getMagazine()));

        if(dto.getField() != null) editor.setField(scienceFieldRepository.getOne(dto.getField()));

        return editor;
    }

    public EditorDTO mapToDTO(Editor editor) {
        EditorDTO dto = new EditorDTO();

        if(editor.getUser() != null) dto.setUser(editor.getUser().getUsername());
        dto.setId(editor.getId());
        dto.setTitle(editor.getTitle());
        if(editor.getMagazine() != null) dto.setMagazine(editor.getMagazine().getIssn());

        if(editor.getField() != null) dto.setField(editor.getField().getCode());

        return dto;
    }

    public List<Editor> mapManyFromDTO(List<EditorDTO> editorDTOs) {
        List<Editor> editors = new ArrayList<>();
        for (EditorDTO editorDTO : editorDTOs) {
            editors.add(mapFromDTO(editorDTO));
        }

        return editors;
    }

    public List<EditorDTO> mapManyToDTO(List<Editor> editors) {
        List<EditorDTO> editorDTOs = new ArrayList<>();
        for (Editor editor : editors) {
            editorDTOs.add(mapToDTO(editor));
        }

        return editorDTOs;
    }
}
