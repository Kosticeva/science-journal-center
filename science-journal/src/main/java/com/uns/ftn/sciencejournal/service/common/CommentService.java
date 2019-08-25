package com.uns.ftn.sciencejournal.service.common;

import com.uns.ftn.sciencejournal.model.common.Comment;
import com.uns.ftn.sciencejournal.repository.common.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

// TODO: 8/24/2019 tasks!
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

        if (!checkCommentValidity(comment)) {
            return null;
        }

        comment.setTimestamp(LocalDateTime.now());

        return commentRepository.save(comment);
    }

    public Comment updateComment(Comment newComment, Long id) {

        if (id == null) {
            return null;
        }

        Comment comment = getById(id);
        if (comment == null) {
            return null;
        }

        if (!checkCommentValidity(newComment)) {
            return null;
        }

        //comment.setTask(newComment.getTask());
        comment.setPrivateComment(newComment.getPrivateComment());
        comment.setPublicComment(newComment.getPublicComment());
        comment.setSummary(newComment.getSummary());

        return commentRepository.save(comment);
    }

    public boolean checkCommentValidity(Comment comment) {
        /*if (comment.getTask() == null || comment.getTask().getId() == null) {
            return false;
        }

        if (taskRepository.getOne(comment.getId()) == null) {
            return false;
        }*/

        if (comment.getPublicComment() == null || comment.getPublicComment().equals("")) {
            return false;
        }

        if (comment.getSummary() == null) {
            return false;
        }

        return true;
    }

    public void deleteComment(Long id) {
        if (id != null) {
            commentRepository.deleteById(id);
        }
    }


}
