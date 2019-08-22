package com.uns.ftn.sciencejournal.mapper.common;

import com.uns.ftn.sciencejournal.dto.common.PaperDTO;
import com.uns.ftn.sciencejournal.model.common.Paper;
import com.uns.ftn.sciencejournal.model.users.UserDetails;
import com.uns.ftn.sciencejournal.repository.common.PaperApplicationRepository;
import com.uns.ftn.sciencejournal.repository.common.PaperIssueRepository;
import com.uns.ftn.sciencejournal.repository.common.ScienceFieldRepository;
import com.uns.ftn.sciencejournal.repository.users.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class PaperMapper {

    @Autowired
    PaperIssueRepository paperIssueRepository;

    @Autowired
    ScienceFieldRepository scienceFieldRepository;

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Autowired
    PaperApplicationRepository paperApplicationRepository;

    public Paper mapFromDTO(PaperDTO dto) {
        Paper paper = new Paper();

        paper.setDoi(dto.getDoi());
        paper.setTitle(dto.getTitle());
        paper.setPaperAbstract(dto.getPaperAbstract());
        paper.setKeyTerms(dto.getKeyTerms());

        paper.setCoauthors(new HashSet<>());
        for (Long coauthor : dto.getCoauthors()) {
            paper.getCoauthors().add(userDetailsRepository.getOne(coauthor));
        }

        if (dto.getIssue() != null) paper.setPaperIssue(paperIssueRepository.getOne(dto.getIssue()));
        if (dto.getField() != null) paper.setField(scienceFieldRepository.getOne(dto.getField()));
        paper.setFile(dto.getFile());
        paper.setPrice(dto.getPrice());
        paper.setCurrency(dto.getCurrency());
        if (dto.getLastRevision() != null)
            paper.setLastRevision(paperApplicationRepository.getOne(dto.getLastRevision()));

        return paper;
    }

    public PaperDTO mapToDTO(Paper paper) {
        PaperDTO dto = new PaperDTO();

        dto.setDoi(paper.getDoi());
        dto.setTitle(paper.getTitle());
        dto.setIssue(paper.getPaperIssue().getId());
        dto.setPaperAbstract(paper.getPaperAbstract());
        dto.setKeyTerms(paper.getKeyTerms());

        dto.setCoauthors(new HashSet<>());
        for (UserDetails coauthor : paper.getCoauthors()) {
            dto.getCoauthors().add(coauthor.getUserId());
        }

        if (paper.getField() != null) dto.setField(paper.getField().getCode());

        dto.setFile(paper.getFile());
        dto.setPrice(paper.getPrice());
        dto.setCurrency(paper.getCurrency());

        if (paper.getLastRevision() != null) dto.setLastRevision(paper.getLastRevision().getId());

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
