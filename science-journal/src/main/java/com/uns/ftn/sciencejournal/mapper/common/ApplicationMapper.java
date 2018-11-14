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

import java.util.HashSet;

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

        application.setPaperId(dto.getPaperId());
        application.setTitle(dto.getTitle());
        application.setPaperAbstract(dto.getPaperAbstract());
        application.setKeyTerms(dto.getKeyTerms());
        application.setAuthor(credentialsRepository.getOne(dto.getAuthor()));
        application.setMagazine(magazineRepository.getOne(dto.getMagazine()));
        application.setField(scienceFieldRepository.getOne(dto.getField()));
        application.setAccepted(dto.getAccepted());
        application.setState(dto.getState());

        application.setCoauthors(new HashSet<>());
        for (Long coauthor : dto.getCoauthors()) {
            application.getCoauthors().add(userRepository.getOne(coauthor));
        }

        return application;
    }

    public ApplicationDTO mapToDTO(Application application) {
        ApplicationDTO dto = new ApplicationDTO();

        dto.setPaperId(application.getPaperId());
        dto.setTitle(application.getTitle());
        dto.setPaperAbstract(application.getPaperAbstract());
        dto.setKeyTerms(application.getKeyTerms());
        dto.setAuthor(application.getAuthor().getUsername());
        dto.setMagazine(application.getMagazine().getIssn());
        dto.setField(application.getField().getCode());
        dto.setAccepted(application.getAccepted());
        dto.setState(application.getState());

        dto.setCoauthors(new HashSet<>());
        for (User coauthor : application.getCoauthors()) {
            dto.getCoauthors().add(coauthor.getUserId());
        }

        return dto;
    }
}
