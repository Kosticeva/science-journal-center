package com.uns.ftn.sciencejournal.service.common;

import com.uns.ftn.sciencejournal.model.common.ScienceField;
import com.uns.ftn.sciencejournal.repository.common.MagazineRepository;
import com.uns.ftn.sciencejournal.repository.common.ScienceFieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ScienceFieldService {

    @Autowired
    ScienceFieldRepository scienceFieldRepository;

    @Autowired
    MagazineRepository magazineRepository;

    public List<ScienceField> getFieldsFromMagazine(String issn) {
        return new ArrayList<>(magazineRepository.getOne(issn).getFields());
    }

    public ScienceField getById(String id) {
        return scienceFieldRepository.findById(id).orElse(null);
    }

    public List<ScienceField> getAll() {
        return scienceFieldRepository.findAll();
    }

    public ScienceField createScienceField(ScienceField scienceField) {

        if (scienceField.getCode() == null || scienceField.getCode().equals("")) {
            return null;
        }

        if (scienceField.getName() == null || scienceField.getName().equals("")) {
            return null;
        }

        return scienceFieldRepository.save(scienceField);
    }

    public ScienceField updateScienceField(ScienceField newScienceField, String id) {

        if (id == null) {
            return null;
        }

        ScienceField scienceField = getById(id);
        if (scienceField == null) {
            return null;
        }

        if (scienceField.getName() == null || scienceField.getName().equals("")) {
            return null;
        }

        scienceField.setName(newScienceField.getName());
        return scienceFieldRepository.save(scienceField);

    }

    public void deleteScienceField(String id) {
        if (id != null) {
            scienceFieldRepository.deleteById(id);
        }
    }


}
