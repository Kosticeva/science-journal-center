package com.uns.ftn.sciencejournal.service.common;

import com.uns.ftn.sciencejournal.model.common.Paper;
import com.uns.ftn.sciencejournal.repository.common.PaperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaperService {

    @Autowired
    PaperRepository paperRepository;

    public Paper getById(String id) {
        return paperRepository.findById(id).orElse(null);
    }

    public List<Paper> getAll() {
        return paperRepository.findAll();
    }

    public Paper createPaper(Paper paper) {

        if (paper.getDoi() != null) {
            return null;
        }

        return paperRepository.save(paper);
    }

    public Paper updatePaper(Paper newPaper, String id) {

        if (id == null) {
            return null;
        }

        Paper paper = getById(id);
        if (paper != null) {

            return paperRepository.save(paper);
        }

        return null;
    }

    public void deletePaper(String id) {
        if (id != null) {
            paperRepository.deleteById(id);
        }
    }


}
