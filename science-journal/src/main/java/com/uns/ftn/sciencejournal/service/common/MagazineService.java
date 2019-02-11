package com.uns.ftn.sciencejournal.service.common;

import com.uns.ftn.sciencejournal.model.common.Magazine;
import com.uns.ftn.sciencejournal.model.common.ScienceField;
import com.uns.ftn.sciencejournal.model.payment.PaymentOption;
import com.uns.ftn.sciencejournal.repository.common.MagazineRepository;
import com.uns.ftn.sciencejournal.repository.common.ScienceFieldRepository;
import com.uns.ftn.sciencejournal.repository.payment.PaymentOptionRepository;
import com.uns.ftn.sciencejournal.repository.users.EditorRepository;
import com.uns.ftn.sciencejournal.service.storage.MagazineStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MagazineService {

    @Autowired
    MagazineRepository magazineRepository;

    @Autowired
    ScienceFieldRepository scienceFieldRepository;

    @Autowired
    PaymentOptionRepository paymentOptionRepository;

    @Autowired
    MagazineStorageService magazineStorageService;

    public Magazine getById(String id) {
        return magazineRepository.findById(id).orElse(null);
    }

    public List<Magazine> getAll() {
        return magazineRepository.findAll();
    }

    public Magazine createMagazine(Magazine magazine) {

        if (magazine.getIssn() == null) {
            return null;
        }

        if (!checkMagazineValidity(magazine)) {
            return null;
        }

        Magazine dbMagazine = magazineRepository.save(magazine);
        if(magazineStorageService.initMagazineRepository(dbMagazine) == null) {
            return null;
        }

        return dbMagazine;
    }

    public Magazine updateMagazine(Magazine newMagazine, String id) {
        if (id == null) {
            return null;
        }

        Magazine magazine = getById(id);
        if (magazine == null) {
            return null;
        }

        if (!checkMagazineValidity(newMagazine)) {
            return null;
        }

        magazine.setName(newMagazine.getName());
        magazine.setMembership(newMagazine.getMembership());
        magazine.setType(newMagazine.getType());
        magazine.setFields(newMagazine.getFields());
        magazine.setOptions(newMagazine.getOptions());
        magazine.setCurrency(newMagazine.getCurrency());

        return magazineRepository.save(magazine);
    }

    private boolean checkMagazineValidity(Magazine magazine) {
        if (magazine.getName() == null || magazine.getName().equals("")) {
            return false;
        }

        if (magazine.getType() == null) {
            return false;
        }

        if (magazine.getMembership() == null) {
            return false;
        }

        if(magazine.getCurrency() == null || magazine.getCurrency().equals("")) {
            return false;
        }

        if (magazine.getFields() == null || magazine.getFields().size() == 0) {
            return false;
        }

        for (ScienceField field : magazine.getFields()) {
            if (field.getCode() == null || scienceFieldRepository.getOne(field.getCode()) == null) {
                return false;
            }
        }

        if (magazine.getOptions() == null) {
            return false;
        }

        for (PaymentOption option : magazine.getOptions()) {
            if (option.getPaymentOptionCode() == null || paymentOptionRepository.getOne(option.getPaymentOptionCode()) == null) {
                return false;
            }
        }

        return true;
    }

    public void deleteMagazine(String id) {
        if (id == null) {
            return;
        }

        magazineStorageService.removeMagazineRepository(magazineRepository.getOne(id));
        magazineRepository.deleteById(id);
    }


}
