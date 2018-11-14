package com.uns.ftn.sciencejournal.repository.users;

import com.uns.ftn.sciencejournal.model.users.BoardMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardMemberRepository extends JpaRepository<BoardMember, BoardMember.BoardPK> {
}
