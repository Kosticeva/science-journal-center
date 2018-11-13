package com.uns.ftn.sciencejournal.service.common;

import com.uns.ftn.sciencejournal.model.common.Issue;
import com.uns.ftn.sciencejournal.repository.common.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueService {

    @Autowired
    IssueRepository issueRepository;

    public Issue getById(Long id) {
        return issueRepository.findById(id).orElse(null);
    }

    public List<Issue> getAll() {
        return issueRepository.findAll();
    }

    public Issue createIssue(Issue issue) {

        if (issue.getId() != null) {
            return null;
        }

        return issueRepository.save(issue);
    }

    public Issue updateIssue(Issue newIssue, Long id) {

        if (id == null) {
            return null;
        }

        Issue issue = getById(id);
        if (issue != null) {

            return issueRepository.save(issue);
        }

        return null;
    }

    public void deleteIssue(Long id) {
        if (id != null) {
            issueRepository.deleteById(id);
        }
    }


}
