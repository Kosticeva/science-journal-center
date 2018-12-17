package com.uns.ftn.sciencejournal.service.common;

import com.uns.ftn.sciencejournal.model.common.Issue;
import com.uns.ftn.sciencejournal.repository.common.IssueRepository;
import com.uns.ftn.sciencejournal.repository.common.MagazineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueService {

    @Autowired
    IssueRepository issueRepository;

    @Autowired
    MagazineRepository magazineRepository;

    public Issue getById(String issn, String edition) {
        return issueRepository.findById(new Issue().new IssuePK(issn, edition)).orElse(null);
    }

    public List<Issue> getAll() {
        return issueRepository.findAll();
    }

    public Issue createIssue(Issue issue) {

        if (issue.getIssuePK() != null) {
            return null;
        }

        return issueRepository.save(issue);
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
        issue.setIssuePK(issue.new IssuePK(newIssue.getMagazine().getIssn(), newIssue.getIssuePK().getEdition()));
        issue.setPrice(newIssue.getPrice());
        issue.setDate(newIssue.getDate());

        return issueRepository.save(issue);
    }

    private boolean checkIssueValidity(Issue issue) {
        if (issue.getIssuePK().getEdition() == null || issue.getIssuePK().getEdition().equals("")) {
            return false;
        }

        if (issue.getPrice() == null) {
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

        issueRepository.deleteById(new Issue().new IssuePK(issn, edition));
    }


}
