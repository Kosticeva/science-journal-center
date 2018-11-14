package com.uns.ftn.sciencejournal.controller.users;

import com.uns.ftn.sciencejournal.dto.users.BoardMemberDTO;
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

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BoardMemberDTO>> getAllBoards() {
        return null;
    }

    @GetMapping(value = "/{magazineId}/{fieldId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BoardMemberDTO> getBoardById(@PathVariable("magazineId") String issn, @PathVariable("fieldId") String field) {
        return null;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BoardMemberDTO> createBoard(@RequestBody BoardMemberDTO newBoard) {
        return null;
    }

    @PutMapping(value = "/{magazineId}/{fieldId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BoardMemberDTO> updateBoard(@PathVariable("magazineId") String issn, @PathVariable("fieldId") String field, @RequestBody BoardMemberDTO newBoard) {
        return null;
    }

    @DeleteMapping(value = "/{magazineId}/{fieldId}")
    public ResponseEntity deleteBoard(@PathVariable("magazineId") String issn, @PathVariable("fieldId") String field) {
        return null;
    }
}
