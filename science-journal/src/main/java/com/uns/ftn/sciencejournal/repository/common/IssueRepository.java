package com.uns.ftn.sciencejournal.repository.common;

import com.uns.ftn.sciencejournal.model.common.Issue;
import com.uns.ftn.sciencejournal.model.common.Magazine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {

    Issue findFirstByMagazineAndEdition(Magazine magazine, String edition);
    List<Issue> findByMagazine(Magazine magazine);
}
