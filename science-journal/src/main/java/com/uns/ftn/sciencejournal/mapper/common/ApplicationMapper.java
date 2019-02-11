package com.uns.ftn.sciencejournal.mapper.common;

import com.uns.ftn.sciencejournal.dto.common.ApplicationDTO;
import com.uns.ftn.sciencejournal.model.common.Application;
import com.uns.ftn.sciencejournal.model.users.User;
import com.uns.ftn.sciencejournal.repository.common.MagazineRepository;
import com.uns.ftn.sciencejournal.repository.common.ScienceFieldRepository;
import com.uns.ftn.sciencejournal.repository.users.CredentialsRepository;
import com.uns.ftn.sciencejournal.repository.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class ApplicationMapper {

    @Autowired
    CredentialsRepository credentialsRepository;

    @Autowired
    MagazineRepository magazineRepository;

    @Autowired
    ScienceFieldRepository scienceFieldRepository;

    @Autowired
    UserRepository userRepository;

    public Application mapFromDTO(ApplicationDTO dto) {
        Application application = new Application();

        application.setId(dto.getPaperId());
        application.setTitle(dto.getTitle());
        application.setPaperAbstract(dto.getPaperAbstract());
        application.setKeyTerms(dto.getKeyTerms());

        if(dto.getAuthor() != null) application.setAuthor(credentialsRepository.getOne(dto.getAuthor()));

        application.setCoauthors(new HashSet<>());
        if(dto.getCoauthors() == null) {

        }else {
            for (Long coauthor : dto.getCoauthors()) {
                application.getCoauthors().add(userRepository.getOne(coauthor));
            }
        }


        if(dto.getMagazine() != null) application.setMagazine(magazineRepository.getOne(dto.getMagazine()));
        if(dto.getField() != null) application.setField(scienceFieldRepository.getOne(dto.getField()));
        application.setFile(dto.getFile());
        application.setState(dto.getState());
        application.setAccepted(dto.getAccepted());
        application.setTimestamp(dto.getTimestamp());

        return application;
    }

    public ApplicationDTO mapToDTO(Application application) {
        ApplicationDTO dto = new ApplicationDTO();

        dto.setPaperId(application.getId());
        dto.setVersion(application.getVersion());
        dto.setTitle(application.getTitle());
        dto.setPaperAbstract(application.getPaperAbstract());
        dto.setKeyTerms(application.getKeyTerms());
        if(application.getAuthor() != null) dto.setAuthor(application.getAuthor().getUsername());

        dto.setCoauthors(new HashSet<>());
        for (User coauthor : application.getCoauthors()) {
            dto.getCoauthors().add(coauthor.getUserId());
        }

        if(application.getMagazine() != null) dto.setMagazine(application.getMagazine().getIssn());
        if(application.getField() != null) dto.setField(application.getField().getCode());

        dto.setFile(application.getFile());
        dto.setState(application.getState());
        dto.setAccepted(application.getAccepted());
        dto.setTimestamp(application.getTimestamp());

        return dto;
    }

    public List<Application> mapManyFromDTO(List<ApplicationDTO> applicationDTOs) {
        List<Application> applications = new ArrayList<>();
        for (ApplicationDTO applicationDTO : applicationDTOs) {
            applications.add(mapFromDTO(applicationDTO));
        }

        return applications;
    }

    public List<ApplicationDTO> mapManyToDTO(List<Application> applications) {
        List<ApplicationDTO> applicationDTOs = new ArrayList<>();
        for (Application application : applications) {
            applicationDTOs.add(mapToDTO(application));
        }

        return applicationDTOs;
    }
}
