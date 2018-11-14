package com.uns.ftn.sciencejournal.mapper.common;

import com.uns.ftn.sciencejournal.dto.common.PaperDTO;
import com.uns.ftn.sciencejournal.model.common.Paper;
import com.uns.ftn.sciencejournal.model.users.User;
import com.uns.ftn.sciencejournal.repository.common.IssueRepository;
import com.uns.ftn.sciencejournal.repository.common.ScienceFieldRepository;
import com.uns.ftn.sciencejournal.repository.users.CredentialsRepository;
import com.uns.ftn.sciencejournal.repository.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class PaperMapper {
    @Autowired
    CredentialsRepository credentialsRepository;

    @Autowired
    IssueRepository issueRepository;

    @Autowired
    ScienceFieldRepository scienceFieldRepository;

    @Autowired
    UserRepository userRepository;

    public Paper mapFromDTO(PaperDTO dto) {
        Paper paper = new Paper();

        paper.setDoi(dto.getDoi());
        paper.setTitle(dto.getTitle());
        paper.setPaperAbstract(dto.getPaperAbstract());
        paper.setKeyTerms(dto.getKeyTerms());
        paper.setAuthor(credentialsRepository.getOne(dto.getAuthor()));
        paper.setIssue(issueRepository.getOne(dto.getIssue()));
        paper.setField(scienceFieldRepository.getOne(dto.getField()));
        paper.setPrice(dto.getPrice());

        paper.setCoauthors(new HashSet<>());
        for (Long coauthor : dto.getCoauthors()) {
            paper.getCoauthors().add(userRepository.getOne(coauthor));
        }

        return paper;
    }

    public PaperDTO mapToDTO(Paper paper) {
        PaperDTO dto = new PaperDTO();

        dto.setDoi(paper.getDoi());
        dto.setTitle(paper.getTitle());
        dto.setPaperAbstract(paper.getPaperAbstract());
        dto.setKeyTerms(paper.getKeyTerms());
        dto.setAuthor(paper.getAuthor().getUsername());
        dto.setIssue(paper.getIssue().getId());
        dto.setField(paper.getField().getCode());
        dto.setPrice(paper.getPrice());

        dto.setCoauthors(new HashSet<>());
        for (User coauthor : paper.getCoauthors()) {
            dto.getCoauthors().add(coauthor.getUserId());
        }

        return dto;
    }

    public List<Paper> mapManyFromDTO(List<PaperDTO> paperDTOs) {
        List<Paper> papers = new ArrayList<>();
        for (PaperDTO paperDTO : paperDTOs) {
            papers.add(mapFromDTO(paperDTO));
        }

        return papers;
    }

    public List<PaperDTO> mapManyToDTO(List<Paper> papers) {
        List<PaperDTO> paperDTOs = new ArrayList<>();
        for (Paper paper : papers) {
            paperDTOs.add(mapToDTO(paper));
        }

        return paperDTOs;
    }
}
