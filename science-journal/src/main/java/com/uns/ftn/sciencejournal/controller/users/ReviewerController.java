package com.uns.ftn.sciencejournal.controller.users;

import com.uns.ftn.sciencejournal.dto.users.ReviewerDTO;
import com.uns.ftn.sciencejournal.service.users.ReviewerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/api/reviewers")
public class ReviewerController {

    @Autowired
    ReviewerService reviewerService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ReviewerDTO>> getAllReviewers() {
        return null;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReviewerDTO> getReviewerById(@PathVariable("id") Integer id) {
        return null;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReviewerDTO> createReviewer(@RequestBody ReviewerDTO newReviewer) {
        return null;
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReviewerDTO> updateReviewer(@PathVariable("id") Integer id, @RequestBody ReviewerDTO newReviewer) {
        return null;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteReviewer(@PathVariable("id") Integer id) {
        return null;
    }
}
