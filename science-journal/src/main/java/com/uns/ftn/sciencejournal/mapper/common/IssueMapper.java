package com.uns.ftn.sciencejournal.mapper.common;

import com.uns.ftn.sciencejournal.dto.common.IssueDTO;
import com.uns.ftn.sciencejournal.model.common.Issue;
import com.uns.ftn.sciencejournal.repository.common.MagazineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<Issue> mapManyFromDTO(List<IssueDTO> issueDTOs) {
        List<Issue> issues = new ArrayList<>();
        for (IssueDTO issueDTO : issueDTOs) {
            issues.add(mapFromDTO(issueDTO));
        }

        return issues;
    }

    public List<IssueDTO> mapManyToDTO(List<Issue> issues) {
        List<IssueDTO> issueDTOs = new ArrayList<>();
        for (Issue issue : issues) {
            issueDTOs.add(mapToDTO(issue));
        }

        return issueDTOs;
    }
}
