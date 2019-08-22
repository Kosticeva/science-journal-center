package com.uns.ftn.sciencejournal.repository.common;

import com.uns.ftn.sciencejournal.model.common.PaperIssue;
import com.uns.ftn.sciencejournal.model.common.Magazine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaperIssueRepository extends JpaRepository<PaperIssue, Long> {

    PaperIssue findFirstByMagazineAndEdition(Magazine magazine, String edition);
    List<PaperIssue> findByMagazine(Magazine magazine);
}
