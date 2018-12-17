package com.uns.ftn.sciencejournal.mapper.users;

import com.uns.ftn.sciencejournal.dto.users.ReviewerDTO;
import com.uns.ftn.sciencejournal.model.common.Magazine;
import com.uns.ftn.sciencejournal.model.common.ScienceField;
import com.uns.ftn.sciencejournal.model.users.Reviewer;
import com.uns.ftn.sciencejournal.repository.common.MagazineRepository;
import com.uns.ftn.sciencejournal.repository.common.ScienceFieldRepository;
import com.uns.ftn.sciencejournal.repository.users.CredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class ReviewerMapper {

    @Autowired
    CredentialsRepository credentialsRepository;

    @Autowired
    MagazineRepository magazineRepository;

    @Autowired
    ScienceFieldRepository scienceFieldRepository;

    public Reviewer mapFromDTO(ReviewerDTO dto) {
        Reviewer reviewer = new Reviewer();

        reviewer.setId(dto.getId());
        reviewer.setTitle(dto.getTitle());
        reviewer.setUser(credentialsRepository.getOne(dto.getUser()));

        reviewer.setMagazines(new HashSet<>());
        for (String issn : dto.getMagazines()) {
            reviewer.getMagazines().add(magazineRepository.getOne(issn));
        }

        reviewer.setFields(new HashSet<>());
        for (String code : dto.getFields()) {
            reviewer.getFields().add(scienceFieldRepository.getOne(code));
        }


        return reviewer;
    }

    public ReviewerDTO mapToDTO(Reviewer reviewer) {
        ReviewerDTO dto = new ReviewerDTO();

        dto.setId(reviewer.getId());
        dto.setTitle(reviewer.getTitle());
        dto.setUser(reviewer.getUser().getUsername());

        dto.setMagazines(new HashSet<>());
        for (Magazine magazine : reviewer.getMagazines()) {
            dto.getMagazines().add(magazine.getIssn());
        }

        dto.setFields(new HashSet<>());
        for (ScienceField field : reviewer.getFields()) {
            dto.getFields().add(field.getCode());
        }

        return dto;
    }

    public List<Reviewer> mapManyFromDTO(List<ReviewerDTO> reviewerDTOs) {
        List<Reviewer> reviewers = new ArrayList<>();
        for (ReviewerDTO reviewerDTO : reviewerDTOs) {
            reviewers.add(mapFromDTO(reviewerDTO));
        }

        return reviewers;
    }

    public List<ReviewerDTO> mapManyToDTO(List<Reviewer> reviewers) {
        List<ReviewerDTO> reviewerDTOs = new ArrayList<>();
        for (Reviewer reviewer : reviewers) {
            reviewerDTOs.add(mapToDTO(reviewer));
        }

        return reviewerDTOs;
    }
}
