package com.uns.ftn.sciencejournal.service.users;

import com.uns.ftn.sciencejournal.model.users.Board;
import com.uns.ftn.sciencejournal.repository.users.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    BoardRepository boardRepository;

    public Board getById(String issn, String field) {
        return boardRepository.findById(new Board().new BoardPK(issn, field)).orElse(null);
    }

    public List<Board> getAll() {
        return boardRepository.findAll();
    }

    public Board createBoard(Board board){

        if(board.getKey() != null){
            return null;
        }

        return boardRepository.save(board);
    }

    public Board updateBoard(Board newBoard,  String issn, String field){

        if(issn.equals(null) || field.equals(null)){
            return null;
        }

        Board board = getById(issn, field);
        if(board != null){

            return boardRepository.save(board);
        }

        return null;
    }

    public void deleteBoard(String issn, String field){
        if(!issn.equals(null) && !field.equals(null)) {
            boardRepository.deleteById(new Board().new BoardPK(issn, field));
        }
    }


}
