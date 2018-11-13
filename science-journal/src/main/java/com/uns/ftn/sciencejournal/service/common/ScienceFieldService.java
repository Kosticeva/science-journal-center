package com.uns.ftn.sciencejournal.service.common;

import com.uns.ftn.sciencejournal.model.common.ScienceField;
import com.uns.ftn.sciencejournal.repository.common.ScienceFieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScienceFieldService {

    @Autowired
    ScienceFieldRepository scienceFieldRepository;

    public ScienceField getById(String id) {
        return scienceFieldRepository.findById(id).orElse(null);
    }

    public List<ScienceField> getAll() {
        return scienceFieldRepository.findAll();
    }

    public ScienceField createScienceField(ScienceField scienceField) {

        if (scienceField.getCode() != null) {
            return null;
        }

        return scienceFieldRepository.save(scienceField);
    }

    public ScienceField updateScienceField(ScienceField newScienceField, String id) {

        if (id == null) {
            return null;
        }

        ScienceField scienceField = getById(id);
        if (scienceField != null) {

            return scienceFieldRepository.save(scienceField);
        }

        return null;
    }

    public void deleteScienceField(String id) {
        if (id != null) {
            scienceFieldRepository.deleteById(id);
        }
    }


}
