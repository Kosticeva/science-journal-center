package com.uns.ftn.sciencejournal.repository.users;

import com.uns.ftn.sciencejournal.model.users.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Board.BoardPK> {
}
