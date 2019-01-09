package com.uns.ftn.sciencejournal.service.common;

import com.uns.ftn.sciencejournal.model.common.Application;
import com.uns.ftn.sciencejournal.model.users.User;
import com.uns.ftn.sciencejournal.repository.common.ApplicationRepository;
import com.uns.ftn.sciencejournal.repository.common.MagazineRepository;
import com.uns.ftn.sciencejournal.repository.common.ScienceFieldRepository;
import com.uns.ftn.sciencejournal.repository.users.CredentialsRepository;
import com.uns.ftn.sciencejournal.repository.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ApplicationService {

    @Autowired
    ApplicationRepository applicationRepository;

    @Autowired
    CredentialsRepository credentialsRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MagazineRepository magazineRepository;

    @Autowired
    ScienceFieldRepository scienceFieldRepository;

    public Application getById(Long id) {
        return applicationRepository.findById(id).orElse(null);
    }

    public List<Application> getAll() {
        return applicationRepository.findAll();
    }

    public Application createApplication(Application application) {

        if (checkDependencyValidity(application) == false) {
            return null;
        }

        application.setVersion(0);
        application.setTimestamp(LocalDate.now());
        application.setAccepted(null);

        return applicationRepository.save(application);
    }

    public Application updateApplication(Application newApplication, Long id) {

        if (id == null) {
            return null;
        }

        Application application = getById(id);
        if (application == null) {
            return null;
        }

        if (!checkDependencyValidity(newApplication)) {
            return null;
        }

        application.setAccepted(newApplication.getAccepted());
        application.setTimestamp(LocalDate.now());
        application.setVersion(application.getVersion()+1);
        application.setState(newApplication.getState());

        application.setFile(newApplication.getFile());
        application.setPaperAbstract(newApplication.getPaperAbstract());
        application.setKeyTerms(newApplication.getKeyTerms());
        application.setTitle(newApplication.getTitle());

        application.setAuthor(newApplication.getAuthor());
        application.setCoauthors(newApplication.getCoauthors());
        application.setMagazine(newApplication.getMagazine());
        application.setField(newApplication.getField());

        return applicationRepository.save(application);
    }

    private boolean checkDependencyValidity(Application application) {

        if (application.getTitle() == null || application.getTitle().equals("")) {
            return false;
        }

        if (application.getPaperAbstract() == null || application.getPaperAbstract().equals("")) {
            return false;
        }

        if (application.getFile() == null || application.getFile().equals("")) {
            return false;
        }

        if (application.getKeyTerms() == null || application.getFile().equals("")) {
            return false;
        }

        if (application.getMagazine() == null || application.getMagazine().getIssn() == null) {
            return false;
        }

        if (magazineRepository.getOne(application.getMagazine().getIssn()) == null) {
            return false;
        }

        if (application.getAuthor() == null || application.getAuthor().getUsername() == null) {
            return false;
        }

        if (credentialsRepository.getOne(application.getAuthor().getUsername()) == null) {
            return false;
        }

        if (application.getField() == null || application.getField().getCode() == null) {
            return false;
        }

        if (scienceFieldRepository.getOne(application.getField().getCode()) == null) {
            return false;
        }

        if (application.getCoauthors() == null || application.getCoauthors().size() == 0) {
            return false;
        }

        for (User coauthor : application.getCoauthors()) {
            if (coauthor.getUserId() == null || userRepository.getOne(coauthor.getUserId()) == null) {
                return false;
            }
        }

        return true;
    }

    public void deleteApplication(Long id) {
        if (id == null) {
            return;
        }

        applicationRepository.deleteById(id);
    }


}
