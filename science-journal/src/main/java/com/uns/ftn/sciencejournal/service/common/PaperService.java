package com.uns.ftn.sciencejournal.service.common;

import com.uns.ftn.sciencejournal.mapper.ElasticSearchPaperMapper;
import com.uns.ftn.sciencejournal.model.common.Paper;
import com.uns.ftn.sciencejournal.model.users.UserDetails;
import com.uns.ftn.sciencejournal.repository.common.PaperApplicationRepository;
import com.uns.ftn.sciencejournal.repository.common.PaperIssueRepository;
import com.uns.ftn.sciencejournal.repository.common.PaperRepository;
import com.uns.ftn.sciencejournal.repository.common.ScienceFieldRepository;
import com.uns.ftn.sciencejournal.repository.users.CredentialsRepository;
import com.uns.ftn.sciencejournal.repository.users.UserDetailsRepository;
import com.uns.ftn.sciencejournal.service.search.ElasticSearchPlugin;
import com.uns.ftn.sciencejournal.service.storage.MagazineStorageService;
import com.uns.ftn.sciencejournal.service.utils.DOIUtils;
import com.uns.ftn.sciencejournal.service.utils.OldElasticSearchJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.List;

@Service
public class PaperService {

    @Autowired
    CredentialsRepository credentialsRepository;

    @Autowired
    PaperRepository paperRepository;

    @Autowired
    PaperApplicationRepository paperApplicationRepository;

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Autowired
    PaperIssueRepository paperIssueRepository;

    @Autowired
    ScienceFieldRepository scienceFieldRepository;

    @Autowired
    ElasticSearchPlugin elasticSearchPlugin;

    @Autowired
    ElasticSearchPaperMapper elasticSearchPaperMapper;

    @Autowired
    MagazineStorageService magazineStorageService;

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

        Paper dbPaper = paperRepository.save(paper);
        Path pathToPaperOnServer = magazineStorageService.publishPaper(paper);
        if (pathToPaperOnServer == null) {
            return null;
        }

        dbPaper.setFile(pathToPaperOnServer.toString());

        paperRepository.save(dbPaper);

        OldElasticSearchJsonUtil util = new OldElasticSearchJsonUtil();
        elasticSearchPlugin.addPaper(util.convertPaperSearchModelToJson(elasticSearchPaperMapper.mapPaperToElasticSearchModel(paper)), paper.getDoi());

        return dbPaper;
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
        paper.setCurrency(newPaper.getCurrency());

        paper.setAuthor(newPaper.getAuthor());
        paper.setCoauthors(newPaper.getCoauthors());
        paper.setPaperIssue(newPaper.getPaperIssue());
        paper.setField(newPaper.getField());
        paper.setLastRevision(newPaper.getLastRevision());

        Paper dbPaper = paperRepository.save(paper);
        Path pathToPaperOnServer = magazineStorageService.publishPaper(paper);
        if (pathToPaperOnServer == null) {
            return null;
        }

        dbPaper.setFile(pathToPaperOnServer.toString());

        paperRepository.save(dbPaper);

        OldElasticSearchJsonUtil util = new OldElasticSearchJsonUtil();
        elasticSearchPlugin.addPaper(util.convertPaperSearchModelToJson(elasticSearchPaperMapper.mapPaperToElasticSearchModel(paper)), paper.getDoi());

        return dbPaper;
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

        if (paper.getKeyTerms() == null || paper.getKeyTerms().equals("")) {
            return false;
        }

        if (paper.getPrice() == null) {
            return false;
        }

        if (paper.getCurrency() == null || paper.getCurrency().equals("")) {
            return false;
        }

        if (paper.getPaperIssue() == null || paper.getPaperIssue().getId() == null) {
            return false;
        }

        if (paperIssueRepository.getOne(paper.getPaperIssue().getId()) == null) {
            return false;
        }

        if (paper.getLastRevision() == null || paper.getLastRevision().getId() == null) {
            return false;
        }

        if (paperApplicationRepository.getOne(paper.getLastRevision().getId()) == null) {
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

        if (paper.getCoauthors() == null) {
            return false;
        }

        for (UserDetails coauthor : paper.getCoauthors()) {
            if (coauthor.getUserId() == null || userDetailsRepository.getOne(coauthor.getUserId()) == null) {
                return false;
            }
        }

        return true;
    }

    public void deletePaper(String id) {
        if (id == null) {
            return;
        }

        elasticSearchPlugin.removePaper(id);
        magazineStorageService.removePaper(paperRepository.getOne(id));
        paperRepository.deleteById(id);
    }
}
