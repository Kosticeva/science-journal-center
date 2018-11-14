package com.uns.ftn.sciencejournal.mapper.common;

import com.uns.ftn.sciencejournal.dto.common.IssueDTO;
import com.uns.ftn.sciencejournal.model.common.Issue;
import com.uns.ftn.sciencejournal.repository.common.MagazineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IssueMapper {

    @Autowired
    MagazineRepository magazineRepository;

    public Issue mapFromDTO(IssueDTO dto) {
        Issue issue = new Issue();

        issue.setDate(dto.getDate());
        issue.setMagazine(magazineRepository.getOne(dto.getMagazine()));
        issue.setEdition(dto.getEdition());
        issue.setId(dto.getId());
        issue.setPrice(dto.getPrice());

        return issue;
    }

    public IssueDTO mapToDTO(Issue issue) {
        IssueDTO dto = new IssueDTO();

        dto.setDate(issue.getDate());
        dto.setMagazine(issue.getMagazine().getIssn());
        dto.setEdition(issue.getEdition());
        dto.setId(issue.getId());
        dto.setPrice(issue.getPrice());

        return dto;
    }
}
