package com.uns.ftn.sciencejournal.repository.users;

import com.uns.ftn.sciencejournal.model.common.Magazine;
import com.uns.ftn.sciencejournal.model.common.ScienceField;
import com.uns.ftn.sciencejournal.model.users.Credentials;
import com.uns.ftn.sciencejournal.model.users.Reviewer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewerRepository extends JpaRepository<Reviewer, Integer> {

    List<Reviewer> findByMagazinesAndFields(Magazine magazine, ScienceField field);
    List<Reviewer> findByUser(Credentials user);
}
