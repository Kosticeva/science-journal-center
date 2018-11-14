package com.uns.ftn.sciencejournal.controller.users;

import com.uns.ftn.sciencejournal.dto.users.BoardMemberDTO;
import com.uns.ftn.sciencejournal.mapper.users.BoardMemberMapper;
import com.uns.ftn.sciencejournal.model.users.BoardMember;
import com.uns.ftn.sciencejournal.service.users.BoardMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/api/boards")
public class BoardMemberController {

    @Autowired
    BoardMemberService boardMemberService;
    
    @Autowired
    BoardMemberMapper boardMemberMapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BoardMemberDTO>> getAllBoards() {
        return ResponseEntity.ok(boardMemberMapper.mapManyToDTO(boardMemberService.getAll()));
    }

    @GetMapping(value = "/{magazineId}/{fieldId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BoardMemberDTO> getBoardById(@PathVariable("magazineId") String issn, @PathVariable("fieldId") String field) {
        if (!issn.equals(null) && !field.equals(null)) {
            return ResponseEntity.ok(boardMemberMapper.mapToDTO(boardMemberService.getById(issn, field)));
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BoardMemberDTO> createBoard(@RequestBody BoardMemberDTO newBoardMember) {
        if (!newBoardMember.getMagazine().equals(null) && !newBoardMember.getFieldCode().equals(null)) {
            BoardMember boardMember = boardMemberService.createBoardMember(boardMemberMapper.mapFromDTO(newBoardMember));

            if (!boardMember.equals(null)) {
                return ResponseEntity.ok(boardMemberMapper.mapToDTO(boardMember));
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping(value = "/{magazineId}/{fieldId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BoardMemberDTO> updateBoard(@PathVariable("magazineId") String issn, @PathVariable("fieldId") String field, @RequestBody BoardMemberDTO newBoardMember) {
        if (!newBoardMember.getMagazine().equals(null) && !newBoardMember.getFieldCode().equals(null) && !issn.equals(null) && !field.equals(null)) {
            BoardMember boardMember = boardMemberService.updateBoardMember(boardMemberMapper.mapFromDTO(newBoardMember), issn, field);

            if (!boardMember.equals(null)) {
                return ResponseEntity.ok(boardMemberMapper.mapToDTO(boardMember));
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(value = "/{magazineId}/{fieldId}")
    public ResponseEntity deleteBoard(@PathVariable("magazineId") String issn, @PathVariable("fieldId") String field) {
        if (!issn.equals(null) && !field.equals(null)) {
            boardMemberService.deleteBoardMember(issn, field);
            return ResponseEntity.ok(null);
        }

        return ResponseEntity.badRequest().build();
    }
}
