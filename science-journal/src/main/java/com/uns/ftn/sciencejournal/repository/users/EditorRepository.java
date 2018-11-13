package com.uns.ftn.sciencejournal.repository.users;

import com.uns.ftn.sciencejournal.model.users.Editor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditorRepository extends JpaRepository<Editor, Integer> {
}
