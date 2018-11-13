package com.uns.ftn.sciencejournal.service.payment;

import com.uns.ftn.sciencejournal.model.payment.PaperPurchase;
import com.uns.ftn.sciencejournal.repository.payment.PaperPurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaperPurchaseService {

    @Autowired
    PaperPurchaseRepository paperPurchaseRepository;

    public PaperPurchase getById(Long id) {
        return paperPurchaseRepository.findById(id).orElse(null);
    }

    public List<PaperPurchase> getAll() {
        return paperPurchaseRepository.findAll();
    }

    public PaperPurchase createPaperPurchase(PaperPurchase paperPurchase) {

        if (paperPurchase.getTransactionId() != null) {
            return null;
        }

        return paperPurchaseRepository.save(paperPurchase);
    }

    public PaperPurchase updatePaperPurchase(PaperPurchase newPaperPurchase, Long id) {

        if (id == null) {
            return null;
        }

        PaperPurchase paperPurchase = getById(id);
        if (paperPurchase != null) {

            return paperPurchaseRepository.save(paperPurchase);
        }

        return null;
    }

    public void deletePaperPurchase(Long id) {
        if (id != null) {
            paperPurchaseRepository.deleteById(id);
        }
    }


}
