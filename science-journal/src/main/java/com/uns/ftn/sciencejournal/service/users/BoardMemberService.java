package com.uns.ftn.sciencejournal.service.users;

import com.uns.ftn.sciencejournal.mapper.users.BoardMemberMapper;
import com.uns.ftn.sciencejournal.model.users.BoardMember;
import com.uns.ftn.sciencejournal.repository.users.BoardMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardMemberService {

    @Autowired
    BoardMemberRepository boardMemberRepository;

    @Autowired
    BoardMemberMapper boardMemberMapper;

    public BoardMember getById(String issn, String field) {
        return boardMemberRepository.findById(new BoardMember().new BoardPK(issn, field)).orElse(null);
    }

    public List<BoardMember> getAll() {
        return boardMemberRepository.findAll();
    }

    public BoardMember createBoardMember(BoardMember boardMember){

        if(boardMember.getMagazine().getIssn().equals(null) || boardMember.getField().getCode().equals(null)){
            return null;
        }

        return boardMemberRepository.save(boardMember);
    }

    public BoardMember updateBoardMember(BoardMember newBoardMember, String issn, String field){

        if(issn.equals(null) || field.equals(null)){
            return null;
        }

        BoardMember boardMember = getById(issn, field);
        if(boardMember != null){

            return boardMemberRepository.save(boardMember);
        }

        return null;
    }

    public void deleteBoardMember(String issn, String field){
        if(!issn.equals(null) && !field.equals(null)) {
            boardMemberRepository.deleteById(new BoardMember().new BoardPK(issn, field));
        }
    }


}
