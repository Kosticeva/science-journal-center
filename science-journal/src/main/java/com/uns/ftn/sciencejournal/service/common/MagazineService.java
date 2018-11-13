package com.uns.ftn.sciencejournal.service.common;

import com.uns.ftn.sciencejournal.model.common.Magazine;
import com.uns.ftn.sciencejournal.repository.common.MagazineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MagazineService {

    @Autowired
    MagazineRepository magazineRepository;

    public Magazine getById(String id) {
        return magazineRepository.findById(id).orElse(null);
    }

    public List<Magazine> getAll() {
        return magazineRepository.findAll();
    }

    public Magazine createMagazine(Magazine magazine) {

        if (magazine.getIssn() != null) {
            return null;
        }

        return magazineRepository.save(magazine);
    }

    public Magazine updateMagazine(Magazine newMagazine, String id) {

        if (id == null) {
            return null;
        }

        Magazine magazine = getById(id);
        if (magazine != null) {

            return magazineRepository.save(magazine);
        }

        return null;
    }

    public void deleteMagazine(String id) {
        if (id != null) {
            magazineRepository.deleteById(id);
        }
    }


}
