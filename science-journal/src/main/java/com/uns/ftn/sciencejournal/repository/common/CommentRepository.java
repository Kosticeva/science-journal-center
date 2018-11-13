package com.uns.ftn.sciencejournal.repository.common;

import com.uns.ftn.sciencejournal.model.common.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
