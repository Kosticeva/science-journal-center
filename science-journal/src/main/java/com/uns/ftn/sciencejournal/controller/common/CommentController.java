package com.uns.ftn.sciencejournal.controller.common;

import com.uns.ftn.sciencejournal.dto.common.CommentDTO;
import com.uns.ftn.sciencejournal.mapper.common.CommentMapper;
import com.uns.ftn.sciencejournal.model.common.Comment;
import com.uns.ftn.sciencejournal.service.common.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/comments")
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    CommentMapper commentMapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CommentDTO>> getAllComments() {
        return ResponseEntity.ok(commentMapper.mapManyToDTO(commentService.getAll()));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable("id") Long id) {
        if (!id.equals(null)) {
            return ResponseEntity.ok(commentMapper.mapToDTO(commentService.getById(id)));
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO newComment) {
        if (!newComment.getTask().equals(null)) {
            Comment comment = commentService.createComment(commentMapper.mapFromDTO(newComment));

            if (!comment.equals(null)) {
                return ResponseEntity.ok(commentMapper.mapToDTO(comment));
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommentDTO> updateComment(@PathVariable("id") Long id, @RequestBody CommentDTO newComment) {
        if (!newComment.getId().equals(null) && !id.equals(null)) {
            Comment comment = commentService.updateComment(commentMapper.mapFromDTO(newComment), id);

            if (!comment.equals(null)) {
                return ResponseEntity.ok(commentMapper.mapToDTO(comment));
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteComment(@PathVariable("id") Long id) {
        if (!id.equals(null)) {
            commentService.deleteComment(id);
            return ResponseEntity.ok(null);
        }

        return ResponseEntity.badRequest().build();
    }
}
