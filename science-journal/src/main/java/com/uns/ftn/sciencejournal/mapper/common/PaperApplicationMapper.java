package com.uns.ftn.sciencejournal.mapper.common;

import com.uns.ftn.sciencejournal.dto.common.PaperApplicationDTO;
import com.uns.ftn.sciencejournal.model.common.PaperApplication;
import com.uns.ftn.sciencejournal.model.users.UserDetails;
import com.uns.ftn.sciencejournal.repository.common.MagazineRepository;
import com.uns.ftn.sciencejournal.repository.common.ScienceFieldRepository;
import com.uns.ftn.sciencejournal.repository.users.CredentialsRepository;
import com.uns.ftn.sciencejournal.repository.users.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class PaperApplicationMapper {

    @Autowired
    MagazineRepository magazineRepository;

    @Autowired
    ScienceFieldRepository scienceFieldRepository;

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Autowired
    CredentialsRepository credentialsRepository;

    public PaperApplication mapFromDTO(PaperApplicationDTO dto) {
        PaperApplication paperApplication = new PaperApplication();

        paperApplication.setId(dto.getPaperId());
        paperApplication.setTitle(dto.getTitle());
        paperApplication.setPaperAbstract(dto.getPaperAbstract());
        paperApplication.setKeyTerms(dto.getKeyTerms());
        paperApplication.setCoauthors(new HashSet<>());

        if (dto.getCoauthors() == null) {

        } else {
            for (Long coauthor : dto.getCoauthors()) {
                paperApplication.getCoauthors().add(userDetailsRepository.getOne(coauthor));
            }
        }


        if (dto.getMagazine() != null) paperApplication.setMagazine(magazineRepository.getOne(dto.getMagazine()));
        if (dto.getAuthor() != null) paperApplication.setAuthor(credentialsRepository.getOne(dto.getAuthor()));
        if (dto.getField() != null) paperApplication.setField(scienceFieldRepository.getOne(dto.getField()));
        paperApplication.setFile(dto.getFile());
        paperApplication.setState(dto.getState());
        paperApplication.setAccepted(dto.getAccepted());
        paperApplication.setTimestamp(dto.getTimestamp());

        return paperApplication;
    }

    public PaperApplicationDTO mapToDTO(PaperApplication paperApplication) {
        PaperApplicationDTO dto = new PaperApplicationDTO();

        dto.setPaperId(paperApplication.getId());
        dto.setVersion(paperApplication.getVersion());
        dto.setTitle(paperApplication.getTitle());
        dto.setPaperAbstract(paperApplication.getPaperAbstract());
        dto.setKeyTerms(paperApplication.getKeyTerms());
        dto.setAuthor(paperApplication.getAuthor().getUsername());

        dto.setCoauthors(new HashSet<>());
        for (UserDetails coauthor : paperApplication.getCoauthors()) {
            dto.getCoauthors().add(coauthor.getUserId());
        }

        if (paperApplication.getMagazine() != null) dto.setMagazine(paperApplication.getMagazine().getIssn());
        if (paperApplication.getField() != null) dto.setField(paperApplication.getField().getCode());

        dto.setFile(paperApplication.getFile());
        dto.setState(paperApplication.getState());
        dto.setAccepted(paperApplication.getAccepted());
        dto.setTimestamp(paperApplication.getTimestamp());

        return dto;
    }

    public List<PaperApplication> mapManyFromDTO(List<PaperApplicationDTO> paperApplicationDTOS) {
        List<PaperApplication> paperApplications = new ArrayList<>();
        for (PaperApplicationDTO paperApplicationDTO : paperApplicationDTOS) {
            paperApplications.add(mapFromDTO(paperApplicationDTO));
        }

        return paperApplications;
    }

    public List<PaperApplicationDTO> mapManyToDTO(List<PaperApplication> paperApplications) {
        List<PaperApplicationDTO> paperApplicationDTOS = new ArrayList<>();
        for (PaperApplication paperApplication : paperApplications) {
            paperApplicationDTOS.add(mapToDTO(paperApplication));
        }

        return paperApplicationDTOS;
    }
}
