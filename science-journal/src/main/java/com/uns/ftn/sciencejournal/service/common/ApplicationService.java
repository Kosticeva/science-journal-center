package com.uns.ftn.sciencejournal.service.common;

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

    public Application createApplication(Application application) {

        if (application.getPaperId() != null) {
            return null;
        }

        return applicationRepository.save(application);
    }

    public Application updateApplication(Application newApplication, Long id) {

        if (id == null) {
            return null;
        }

        Application application = getById(id);
        if (application != null) {

            return applicationRepository.save(application);
        }

        return null;
    }

    public void deleteApplication(Long id) {
        if (id != null) {
            applicationRepository.deleteById(id);
        }
    }


}
