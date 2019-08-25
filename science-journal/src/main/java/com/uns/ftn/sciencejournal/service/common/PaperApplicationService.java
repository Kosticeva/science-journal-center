package com.uns.ftn.sciencejournal.service.common;

import com.uns.ftn.sciencejournal.model.common.PaperApplication;
import com.uns.ftn.sciencejournal.model.enums.PaperApplicationState;
import com.uns.ftn.sciencejournal.model.users.UserDetails;
import com.uns.ftn.sciencejournal.repository.common.MagazineRepository;
import com.uns.ftn.sciencejournal.repository.common.PaperApplicationRepository;
import com.uns.ftn.sciencejournal.repository.common.ScienceFieldRepository;
import com.uns.ftn.sciencejournal.repository.users.CredentialsRepository;
import com.uns.ftn.sciencejournal.repository.users.UserDetailsRepository;
import com.uns.ftn.sciencejournal.service.storage.MagazineStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

@Service
public class PaperApplicationService {

    @Autowired
    CredentialsRepository credentialsRepository;

    @Autowired
    PaperApplicationRepository paperApplicationRepository;

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Autowired
    MagazineRepository magazineRepository;

    @Autowired
    ScienceFieldRepository scienceFieldRepository;

    @Autowired
    MagazineStorageService magazineStorageService;

    public PaperApplication getById(Long id) {
        return paperApplicationRepository.findById(id).orElse(null);
    }

    public List<PaperApplication> getAll() {
        return paperApplicationRepository.findAll();
    }

    public PaperApplication createApplication(PaperApplication paperApplication, MultipartFile file) {

        if (!checkDependencyValidity(paperApplication)) {
            return null;
        }

        paperApplication.setVersion(0);
        paperApplication.setTimestamp(LocalDate.now());
        paperApplication.setAccepted(null);
        paperApplication.setState(PaperApplicationState.REVIEWER_PROPOSAL);
        paperApplication.setFile(file.getOriginalFilename());

        PaperApplication dbPaperApplication = paperApplicationRepository.save(paperApplication);
        Path pathToApplicationOnServer = magazineStorageService.storeApplication(dbPaperApplication, file);
        if (pathToApplicationOnServer == null) {
            return null;
        }

        dbPaperApplication.setFile(pathToApplicationOnServer.toString());

        paperApplicationRepository.save(dbPaperApplication);
        return dbPaperApplication;
    }

    public PaperApplication updateApplication(PaperApplication newPaperApplication, Long id, MultipartFile file) {

        if (id == null) {
            return null;
        }

        PaperApplication paperApplication = getById(id);
        if (paperApplication == null) {
            return null;
        }

        if (!checkDependencyValidity(newPaperApplication)) {
            return null;
        }

        paperApplication.setAccepted(newPaperApplication.getAccepted());
        paperApplication.setTimestamp(LocalDate.now());
        paperApplication.setVersion(paperApplication.getVersion() + 1);
        paperApplication.setState(newPaperApplication.getState());

        paperApplication.setFile(newPaperApplication.getFile());
        paperApplication.setPaperAbstract(newPaperApplication.getPaperAbstract());
        paperApplication.setKeyTerms(newPaperApplication.getKeyTerms());
        paperApplication.setTitle(newPaperApplication.getTitle());

        paperApplication.setAuthor(newPaperApplication.getAuthor());
        paperApplication.setCoauthors(newPaperApplication.getCoauthors());
        paperApplication.setMagazine(newPaperApplication.getMagazine());
        paperApplication.setField(newPaperApplication.getField());

        PaperApplication dbPaperApplication = paperApplicationRepository.save(paperApplication);
        Path pathToApplicationOnServer = magazineStorageService.storeApplication(dbPaperApplication, file);
        if (pathToApplicationOnServer == null) {
            return null;
        }

        dbPaperApplication.setFile(pathToApplicationOnServer.toString());

        paperApplicationRepository.save(dbPaperApplication);
        return dbPaperApplication;
    }

    private boolean checkDependencyValidity(PaperApplication paperApplication) {

        if (paperApplication.getTitle() == null || paperApplication.getTitle().equals("")) {
            return false;
        }

        if (paperApplication.getPaperAbstract() == null || paperApplication.getPaperAbstract().equals("")) {
            return false;
        }

        if (paperApplication.getKeyTerms() == null || paperApplication.getKeyTerms().equals("")) {
            return false;
        }

        if (paperApplication.getMagazine() == null || paperApplication.getMagazine().getIssn() == null) {
            return false;
        }

        if (magazineRepository.getOne(paperApplication.getMagazine().getIssn()) == null) {
            return false;
        }

        if (paperApplication.getAuthor() == null || paperApplication.getAuthor().getUsername() == null) {
            return false;
        }

        if (credentialsRepository.getOne(paperApplication.getAuthor().getUsername()) == null) {
            return false;
        }

        if (paperApplication.getField() == null || paperApplication.getField().getCode() == null) {
            return false;
        }

        if (scienceFieldRepository.getOne(paperApplication.getField().getCode()) == null) {
            return false;
        }

        if (paperApplication.getCoauthors() == null) {
            return false;
        }

        for (UserDetails coauthor : paperApplication.getCoauthors()) {
            if (coauthor.getUserId() == null || userDetailsRepository.getOne(coauthor.getUserId()) == null) {
                return false;
            }
        }

        return true;
    }

    public void deleteApplication(Long id) {
        if (id == null) {
            return;
        }

        magazineStorageService.removeApplication(paperApplicationRepository.getOne(id));
        paperApplicationRepository.deleteById(id);
    }
}
