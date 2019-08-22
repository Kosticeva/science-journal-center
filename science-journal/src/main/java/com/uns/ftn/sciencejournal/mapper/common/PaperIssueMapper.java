package com.uns.ftn.sciencejournal.mapper.common;

import com.uns.ftn.sciencejournal.dto.common.PaperIssueDTO;
import com.uns.ftn.sciencejournal.model.common.PaperIssue;
import com.uns.ftn.sciencejournal.repository.common.MagazineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaperIssueMapper {

    @Autowired
    MagazineRepository magazineRepository;

    public PaperIssue mapFromDTO(PaperIssueDTO dto) {
        PaperIssue paperIssue = new PaperIssue();

        paperIssue.setId(dto.getId());

        if(dto.getMagazine() != null) paperIssue.setMagazine(magazineRepository.getOne(dto.getMagazine()));
        paperIssue.setEdition(dto.getEdition());
        paperIssue.setDate(dto.getDate());
        paperIssue.setPrice(dto.getPrice());
        paperIssue.setCurrency(dto.getCurrency());

        return paperIssue;
    }

    public PaperIssueDTO mapToDTO(PaperIssue paperIssue) {
        PaperIssueDTO dto = new PaperIssueDTO();

        if(paperIssue.getMagazine() != null) dto.setMagazine(paperIssue.getMagazine().getIssn());
        dto.setEdition(paperIssue.getEdition());
        dto.setDate(paperIssue.getDate());
        dto.setPrice(paperIssue.getPrice());
        dto.setId(paperIssue.getId());
        dto.setCurrency(paperIssue.getCurrency());

        return dto;
    }

    public List<PaperIssue> mapManyFromDTO(List<PaperIssueDTO> paperIssueDTOS) {
        List<PaperIssue> paperIssues = new ArrayList<>();
        for (PaperIssueDTO paperIssueDTO : paperIssueDTOS) {
            paperIssues.add(mapFromDTO(paperIssueDTO));
        }

        return paperIssues;
    }

    public List<PaperIssueDTO> mapManyToDTO(List<PaperIssue> paperIssues) {
        List<PaperIssueDTO> paperIssueDTOS = new ArrayList<>();
        for (PaperIssue paperIssue : paperIssues) {
            paperIssueDTOS.add(mapToDTO(paperIssue));
        }

        return paperIssueDTOS;
    }
}
