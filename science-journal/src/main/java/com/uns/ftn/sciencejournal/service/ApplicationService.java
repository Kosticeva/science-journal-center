package com.uns.ftn.sciencejournal.service;

import com.uns.ftn.sciencejournal.model.common.Application;
import com.uns.ftn.sciencejournal.repository.common.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {

    @Autowired
    ApplicationRepository applicationRepository;

    public Application getById(Long id) {
        return applicationRepository.findById(id).orElse(null);
    }

    public List<Application> getAll() {
        return applicationRepository.findAll();
    }


}
