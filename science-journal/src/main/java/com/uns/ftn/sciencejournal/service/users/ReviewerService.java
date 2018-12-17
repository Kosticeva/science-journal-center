package com.uns.ftn.sciencejournal.service.users;

import com.uns.ftn.sciencejournal.model.common.Magazine;
import com.uns.ftn.sciencejournal.model.common.ScienceField;
import com.uns.ftn.sciencejournal.model.users.Reviewer;
import com.uns.ftn.sciencejournal.repository.common.MagazineRepository;
import com.uns.ftn.sciencejournal.repository.common.ScienceFieldRepository;
import com.uns.ftn.sciencejournal.repository.users.CredentialsRepository;
import com.uns.ftn.sciencejournal.repository.users.ReviewerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewerService {

    @Autowired
    ReviewerRepository reviewerRepository;

    @Autowired
    CredentialsRepository credentialsRepository;

    @Autowired
    MagazineRepository magazineRepository;

    @Autowired
    ScienceFieldRepository scienceFieldRepository;

    public Reviewer getById(Integer id) {
        return reviewerRepository.findById(id).orElse(null);
    }

    public List<Reviewer> getAll() {
        return reviewerRepository.findAll();
    }

    public Reviewer createReviewer(Reviewer reviewer) {

        if (reviewer.getId() != null) {
            return null;
        }

        if (!checkReviewerValidity(reviewer)) {
            return null;
        }

        return reviewerRepository.save(reviewer);
    }

    public Reviewer updateReviewer(Reviewer newReviewer, Integer id) {

        if (id == null) {
            return null;
        }

        Reviewer reviewer = getById(id);
        if (reviewer == null) {
            return null;
        }

        if (!checkReviewerValidity(newReviewer)) {
            return null;
        }

        reviewer.setFields(newReviewer.getFields());
        reviewer.setMagazines(newReviewer.getMagazines());
        reviewer.setTitle(newReviewer.getTitle());
        reviewer.setUser(newReviewer.getUser());

        return reviewerRepository.save(reviewer);
    }

    private boolean checkReviewerValidity(Reviewer reviewer) {

        if (reviewer.getTitle() == null || reviewer.getTitle().equals("")) {
            return false;
        }

        if (reviewer.getUser() == null || reviewer.getUser().getUsername() == null) {
            return false;
        }

        if (credentialsRepository.getOne(reviewer.getUser().getUsername()) == null) {
            return false;
        }

        if (reviewer.getMagazines() == null || reviewer.getMagazines().isEmpty()) {
            return false;
        }

        for (Magazine magazine : reviewer.getMagazines()) {
            if (magazineRepository.getOne(magazine.getIssn()) == null) {
                return false;
            }
        }

        if (reviewer.getFields() == null || reviewer.getFields().isEmpty()) {
            return false;
        }

        for (ScienceField field : reviewer.getFields()) {
            if (scienceFieldRepository.getOne(field.getCode()) == null) {
                return false;
            }
        }

        return true;
    }

    public void deleteReviewer(Integer id) {
        if (id != null) {
            reviewerRepository.deleteById(id);
        }
    }


}
