package com.uns.ftn.sciencejournal.service.common;

import com.uns.ftn.sciencejournal.model.common.Comment;
import com.uns.ftn.sciencejournal.repository.common.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    public Comment getById(Long id) {
        return commentRepository.findById(id).orElse(null);
    }

    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    public Comment createComment(Comment comment) {

        if (comment.getId() != null) {
            return null;
        }

        return commentRepository.save(comment);
    }

    public Comment updateComment(Comment newComment, Long id) {

        if (id == null) {
            return null;
        }

        Comment comment = getById(id);
        if (comment != null) {

            return commentRepository.save(comment);
        }

        return null;
    }

    public void deleteComment(Long id) {
        if (id != null) {
            commentRepository.deleteById(id);
        }
    }


}
