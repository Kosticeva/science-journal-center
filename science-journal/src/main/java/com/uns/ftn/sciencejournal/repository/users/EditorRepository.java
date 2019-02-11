package com.uns.ftn.sciencejournal.repository.users;

import com.uns.ftn.sciencejournal.model.common.Magazine;
import com.uns.ftn.sciencejournal.model.common.ScienceField;
import com.uns.ftn.sciencejournal.model.users.Editor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EditorRepository extends JpaRepository<Editor, Integer> {

    Editor findFirstByMagazineAndField(Magazine magazine, ScienceField field);
}
