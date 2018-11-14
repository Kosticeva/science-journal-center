package com.uns.ftn.sciencejournal.controller.users;

import com.uns.ftn.sciencejournal.dto.users.ReviewerDTO;
import com.uns.ftn.sciencejournal.mapper.users.ReviewerMapper;
import com.uns.ftn.sciencejournal.model.users.Reviewer;
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

    @Autowired
    ReviewerMapper reviewerMapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ReviewerDTO>> getAllReviewers() {
        return ResponseEntity.ok(reviewerMapper.mapManyToDTO(reviewerService.getAll()));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReviewerDTO> getReviewerById(@PathVariable("id") Integer id) {
        if (!id.equals(null)) {
            return ResponseEntity.ok(reviewerMapper.mapToDTO(reviewerService.getById(id)));
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReviewerDTO> createReviewer(@RequestBody ReviewerDTO newReviewer) {
        if (newReviewer.getId().equals(null)) {
            Reviewer reviewer = reviewerService.createReviewer(reviewerMapper.mapFromDTO(newReviewer));

            if (!reviewer.equals(null)) {
                return ResponseEntity.ok(reviewerMapper.mapToDTO(reviewer));
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReviewerDTO> updateReviewer(@PathVariable("id") Integer id, @RequestBody ReviewerDTO newReviewer) {
        if (!newReviewer.getId().equals(null) && !id.equals(null)) {
            Reviewer reviewer = reviewerService.updateReviewer(reviewerMapper.mapFromDTO(newReviewer), id);

            if (!reviewer.equals(null)) {
                return ResponseEntity.ok(reviewerMapper.mapToDTO(reviewer));
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteReviewer(@PathVariable("id") Integer id) {
        if (!id.equals(null)) {
            reviewerService.deleteReviewer(id);
            return ResponseEntity.ok(null);
        }

        return ResponseEntity.badRequest().build();
    }
}
