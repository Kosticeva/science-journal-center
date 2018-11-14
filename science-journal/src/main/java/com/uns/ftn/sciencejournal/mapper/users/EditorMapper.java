package com.uns.ftn.sciencejournal.mapper.users;

import com.uns.ftn.sciencejournal.dto.users.EditorDTO;
import com.uns.ftn.sciencejournal.model.users.Editor;
import com.uns.ftn.sciencejournal.repository.users.CredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditorMapper {

    @Autowired
    CredentialsRepository credentialsRepository;

    public Editor mapFromDTO(EditorDTO dto) {
        Editor editor = new Editor();

        editor.setId(dto.getId());
        editor.setTitle(dto.getTitle());
        editor.setUser(credentialsRepository.getOne(dto.getUser()));

        return editor;
    }

    public EditorDTO mapToDTO(Editor editor) {
        EditorDTO dto = new EditorDTO();

        dto.setId(editor.getId());
        dto.setTitle(editor.getTitle());
        dto.setUser(editor.getUser().getUsername());

        return dto;
    }
}
