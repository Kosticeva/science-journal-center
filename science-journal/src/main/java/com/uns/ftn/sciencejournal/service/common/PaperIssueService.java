package com.uns.ftn.sciencejournal.service.common;

import com.uns.ftn.sciencejournal.model.common.PaperIssue;
import com.uns.ftn.sciencejournal.repository.common.PaperIssueRepository;
import com.uns.ftn.sciencejournal.repository.common.MagazineRepository;
import com.uns.ftn.sciencejournal.service.storage.MagazineStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaperIssueService {

    @Autowired
    PaperIssueRepository paperIssueRepository;

    @Autowired
    MagazineRepository magazineRepository;

    @Autowired
    MagazineStorageService magazineStorageService;

    public List<PaperIssue> getByMagazine(String issn) {
        return paperIssueRepository.findByMagazine(magazineRepository.getOne(issn));
    }

    public PaperIssue getById(String issn, String edition) {
        return paperIssueRepository.findFirstByMagazineAndEdition(magazineRepository.getOne(issn), edition);
    }

    public List<PaperIssue> getAll() {
        return paperIssueRepository.findAll();
    }

    public PaperIssue createIssue(PaperIssue paperIssue) {

        if (paperIssue.getId() != null) {
            return null;
        }

        PaperIssue dbPaperIssue = paperIssueRepository.save(paperIssue);
        if(magazineStorageService.initIssueRepository(dbPaperIssue) == null){
            return null;
        }

        return dbPaperIssue;
    }

    public PaperIssue updateIssue(PaperIssue newPaperIssue, String issn, String edition) {

        if (issn == null) {
            return null;
        }

        PaperIssue paperIssue = getById(issn, edition);
        if (paperIssue == null) {
            return null;
        }

        if (!checkIssueValidity(paperIssue)) {
            return null;
        }

        paperIssue.setMagazine(newPaperIssue.getMagazine());
        paperIssue.setEdition(newPaperIssue.getEdition());
        paperIssue.setPrice(newPaperIssue.getPrice());
        paperIssue.setDate(newPaperIssue.getDate());
        paperIssue.setCurrency(newPaperIssue.getCurrency());

        return paperIssueRepository.save(paperIssue);
    }

    private boolean checkIssueValidity(PaperIssue paperIssue) {
        if (paperIssue.getEdition() == null || paperIssue.getEdition().equals("")) {
            return false;
        }

        if (paperIssue.getPrice() == null) {
            return false;
        }

        if(paperIssue.getCurrency() == null || paperIssue.getCurrency().equals("")) {
            return false;
        }

        if (paperIssue.getDate() == null) {
            return false;
        }

        if (paperIssue.getMagazine() == null || paperIssue.getMagazine().getIssn() == null) {
            return false;
        }

        if (magazineRepository.getOne(paperIssue.getMagazine().getIssn()) == null) {
            return false;
        }

        return true;
    }

    public void deleteIssue(String issn, String edition) {
        if (issn == null || edition == null) {
            return;
        }

        magazineStorageService.removeIssueRepository(paperIssueRepository.findFirstByMagazineAndEdition(magazineRepository.getOne(issn), edition));
        paperIssueRepository.deleteById(paperIssueRepository.findFirstByMagazineAndEdition(magazineRepository.getOne(issn), edition).getId());
    }

}
