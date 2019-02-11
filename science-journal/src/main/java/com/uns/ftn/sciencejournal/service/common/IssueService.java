package com.uns.ftn.sciencejournal.service.common;

import com.uns.ftn.sciencejournal.model.common.Issue;
import com.uns.ftn.sciencejournal.repository.common.IssueRepository;
import com.uns.ftn.sciencejournal.repository.common.MagazineRepository;
import com.uns.ftn.sciencejournal.service.storage.MagazineStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueService {

    @Autowired
    IssueRepository issueRepository;

    @Autowired
    MagazineRepository magazineRepository;

    @Autowired
    MagazineStorageService magazineStorageService;

    public Issue getById(String issn, String edition) {
        return issueRepository.findFirstByMagazineAndEdition(issn, edition);
    }

    public List<Issue> getAll() {
        return issueRepository.findAll();
    }

    public Issue createIssue(Issue issue) {

        if (issue.getId() != null) {
            return null;
        }

        Issue dbIssue = issueRepository.save(issue);
        if(magazineStorageService.initIssueRepository(dbIssue) == null){
            return null;
        }

        return dbIssue;
    }

    public Issue updateIssue(Issue newIssue, String issn, String edition) {

        if (issn == null) {
            return null;
        }

        Issue issue = getById(issn, edition);
        if (issue == null) {
            return null;
        }

        if (!checkIssueValidity(issue)) {
            return null;
        }

        issue.setMagazine(newIssue.getMagazine());
        issue.setEdition(newIssue.getEdition());
        issue.setPrice(newIssue.getPrice());
        issue.setDate(newIssue.getDate());
        issue.setCurrency(newIssue.getCurrency());

        return issueRepository.save(issue);
    }

    private boolean checkIssueValidity(Issue issue) {
        if (issue.getEdition() == null || issue.getEdition().equals("")) {
            return false;
        }

        if (issue.getPrice() == null) {
            return false;
        }

        if(issue.getCurrency() == null || issue.getCurrency().equals("")) {
            return false;
        }

        if (issue.getDate() == null) {
            return false;
        }

        if (issue.getMagazine() == null || issue.getMagazine().getIssn() == null) {
            return false;
        }

        if (magazineRepository.getOne(issue.getMagazine().getIssn()) == null) {
            return false;
        }

        return true;
    }

    public void deleteIssue(String issn, String edition) {
        if (issn == null || edition == null) {
            return;
        }

        magazineStorageService.removeIssueRepository(issueRepository.findFirstByMagazineAndEdition(issn, edition));
        issueRepository.deleteById(issueRepository.findFirstByMagazineAndEdition(issn, edition).getId());
    }

}