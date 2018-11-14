package com.uns.ftn.sciencejournal.mapper.users;

import com.uns.ftn.sciencejournal.dto.users.BoardMemberDTO;
import com.uns.ftn.sciencejournal.model.users.BoardMember;
import com.uns.ftn.sciencejournal.repository.users.EditorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardMemberMapper {

    @Autowired
    EditorRepository editorRepository;

    public BoardMember mapFromDTO(BoardMemberDTO dto) {
        BoardMember boardMember = new BoardMember();
        BoardMember.BoardPK key = boardMember.new BoardPK();

        key.setCode(dto.getFieldCode());
        key.setIssn(dto.getMagazine());
        boardMember.setKey(key);
        boardMember.setEditor(editorRepository.getOne(dto.getEditor()));

        return boardMember;
    }

    public BoardMemberDTO mapToDTO(BoardMember boardMember) {
        BoardMemberDTO dto = new BoardMemberDTO();

        dto.setEditor(boardMember.getEditor().getId());
        dto.setFieldCode(boardMember.getKey().getCode());
        dto.setMagazine(boardMember.getKey().getIssn());

        return dto;
    }
}
