package com.uns.ftn.sciencejournal.mapper.common;

import com.uns.ftn.sciencejournal.dto.common.CommentDTO;
import com.uns.ftn.sciencejournal.model.common.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentMapper {

    public Comment mapFromDTO(CommentDTO dto) {
        Comment comment = new Comment();

        comment.setId(dto.getId());
        comment.setPrivateComment(dto.getPrivateComment());
        comment.setPublicComment(dto.getPublicComment());
        comment.setSummary(dto.getSummary());
        comment.setTimestamp(dto.getTimestamp());

        return comment;
    }

    public CommentDTO mapToDTO(Comment comment) {
        CommentDTO dto = new CommentDTO();

        dto.setId(comment.getId());
        dto.setPrivateComment(comment.getPrivateComment());
        dto.setPublicComment(comment.getPublicComment());
        dto.setSummary(comment.getSummary());
        dto.setTimestamp(comment.getTimestamp());

        return dto;
    }

    public List<Comment> mapManyFromDTO(List<CommentDTO> commentDTOs) {
        List<Comment> comments = new ArrayList<>();
        for (CommentDTO commentDTO : commentDTOs) {
            comments.add(mapFromDTO(commentDTO));
        }

        return comments;
    }

    public List<CommentDTO> mapManyToDTO(List<Comment> comments) {
        List<CommentDTO> commentDTOs = new ArrayList<>();
        for (Comment comment : comments) {
            commentDTOs.add(mapToDTO(comment));
        }

        return commentDTOs;
    }
}
