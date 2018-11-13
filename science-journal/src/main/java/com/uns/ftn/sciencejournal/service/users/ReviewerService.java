package com.uns.ftn.sciencejournal.service.users;

import com.uns.ftn.sciencejournal.model.users.Reviewer;
import com.uns.ftn.sciencejournal.repository.users.ReviewerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewerService {

    @Autowired
    ReviewerRepository reviewerRepository;

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

        return reviewerRepository.save(reviewer);
    }

    public Reviewer updateReviewer(Reviewer newReviewer, Integer id) {

        if (id == null) {
            return null;
        }

        Reviewer reviewer = getById(id);
        if (reviewer != null) {

            return reviewerRepository.save(reviewer);
        }

        return null;
    }

    public void deleteReviewer(Integer id) {
        if (id != null) {
            reviewerRepository.deleteById(id);
        }
    }


}
