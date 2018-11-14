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

import java.util.HashSet;

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

        reviewer.setFields(new HashSet<>());
        for (String code : dto.getFields()) {
            reviewer.getFields().add(scienceFieldRepository.getOne(code));
        }

        reviewer.setMagazines(new HashSet<>());
        for (String issn : dto.getMagazines()) {
            reviewer.getMagazines().add(magazineRepository.getOne(issn));
        }

        return reviewer;
    }

    public ReviewerDTO mapToDTO(Reviewer reviewer) {
        ReviewerDTO dto = new ReviewerDTO();

        dto.setId(reviewer.getId());
        dto.setTitle(reviewer.getTitle());
        dto.setUser(reviewer.getUser().getUsername());

        dto.setFields(new HashSet<>());
        for (ScienceField field : reviewer.getFields()) {
            dto.getFields().add(field.getCode());
        }

        dto.setMagazines(new HashSet<>());
        for (Magazine magazine : reviewer.getMagazines()) {
            dto.getMagazines().add(magazine.getIssn());
        }

        return dto;
    }
}
