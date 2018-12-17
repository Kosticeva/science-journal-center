package com.uns.ftn.sciencejournal.service.common;

import com.uns.ftn.sciencejournal.model.common.Paper;
import com.uns.ftn.sciencejournal.model.users.User;
import com.uns.ftn.sciencejournal.repository.common.*;
import com.uns.ftn.sciencejournal.repository.users.CredentialsRepository;
import com.uns.ftn.sciencejournal.repository.users.UserRepository;
import com.uns.ftn.sciencejournal.service.utils.DOIUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaperService {

    @Autowired
    PaperRepository paperRepository;

    @Autowired
    ApplicationRepository applicationRepository;

    @Autowired
    CredentialsRepository credentialsRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    IssueRepository issueRepository;

    @Autowired
    ScienceFieldRepository scienceFieldRepository;

    public Paper getById(String id) {
        return paperRepository.findById(id).orElse(null);
    }

    public List<Paper> getAll() {
        return paperRepository.findAll();
    }

    public Paper createPaper(Paper paper) {
        if (!checkDependencyValidity(paper)) {
            return null;
        }

        paper.setDoi(DOIUtils.generateDOI(paper));

        return paperRepository.save(paper);
    }

    public Paper updatePaper(Paper newPaper, String id) {

        if (id == null) {
            return null;
        }

        Paper paper = getById(id);
        if (paper == null) {
            return null;
        }

        if (!checkDependencyValidity(paper)) {
            return null;
        }

        paper.setFile(newPaper.getFile());
        paper.setPaperAbstract(newPaper.getPaperAbstract());
        paper.setKeyTerms(newPaper.getKeyTerms());
        paper.setTitle(newPaper.getTitle());
        paper.setPrice(newPaper.getPrice());

        paper.setAuthor(newPaper.getAuthor());
        paper.setCoauthors(newPaper.getCoauthors());
        paper.setIssue(newPaper.getIssue());
        paper.setField(newPaper.getField());
        paper.setLastRevision(newPaper.getLastRevision());

        return paperRepository.save(paper);
    }

    private boolean checkDependencyValidity(Paper paper) {

        if (paper.getTitle() == null || paper.getTitle().equals("")) {
            return false;
        }

        if (paper.getPaperAbstract() == null || paper.getPaperAbstract().equals("")) {
            return false;
        }

        if (paper.getFile() == null || paper.getFile().equals("")) {
            return false;
        }

        if (paper.getKeyTerms() == null || paper.getFile().equals("")) {
            return false;
        }

        if (paper.getPrice() == null) {
            return false;
        }

        if (paper.getIssue() == null || paper.getIssue().getIssuePK() == null) {
            return false;
        }

        if (issueRepository.getOne(paper.getIssue().getIssuePK()) == null) {
            return false;
        }

        if (paper.getLastRevision() == null || paper.getLastRevision().getApplicationPK() == null) {
            return false;
        }

        if (applicationRepository.getOne(paper.getLastRevision().getApplicationPK()) == null) {
            return false;
        }

        if (paper.getAuthor() == null || paper.getAuthor().getUsername() == null) {
            return false;
        }

        if (credentialsRepository.getOne(paper.getAuthor().getUsername()) == null) {
            return false;
        }

        if (paper.getField() == null || paper.getField().getCode() == null) {
            return false;
        }

        if (scienceFieldRepository.getOne(paper.getField().getCode()) == null) {
            return false;
        }

        if (paper.getCoauthors() == null || paper.getCoauthors().size() == 0) {
            return false;
        }

        for (User coauthor : paper.getCoauthors()) {
            if (coauthor.getUserId() == null || userRepository.getOne(coauthor.getUserId()) == null) {
                return false;
            }
        }

        return true;
    }

    public void deletePaper(String id) {
        if (id != null) {
            paperRepository.deleteById(id);
        }
    }


}
