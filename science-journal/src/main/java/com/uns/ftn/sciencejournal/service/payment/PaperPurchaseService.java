package com.uns.ftn.sciencejournal.service.payment;

import com.uns.ftn.sciencejournal.model.payment.PaperPurchase;
import com.uns.ftn.sciencejournal.model.users.Credentials;
import com.uns.ftn.sciencejournal.repository.common.PaperRepository;
import com.uns.ftn.sciencejournal.repository.payment.PaperPurchaseRepository;
import com.uns.ftn.sciencejournal.repository.payment.PaymentOptionRepository;
import com.uns.ftn.sciencejournal.repository.users.CredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PaperPurchaseService {

    @Autowired
    PaperPurchaseRepository paperPurchaseRepository;

    @Autowired
    PaperRepository paperRepository;

    @Autowired
    CredentialsRepository credentialsRepository;

    @Autowired
    PaymentOptionRepository paymentOptionRepository;

    public List<PaperPurchase> getAllFromUser(String username) {
        Credentials user = credentialsRepository.findFirstByUsername(username);
        if (user == null) {
            return new ArrayList<>();
        }

        return paperPurchaseRepository.getByUser(user);
    }

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

        if (!checkPaperPurchaseValidity(paperPurchase)) {
            return null;
        }

        paperPurchase.setTimeOfPurchase(LocalDateTime.now());
        paperPurchase.setSuccessful(null);
        paperPurchase.setTransactionId(UUID.randomUUID().toString());

        return paperPurchaseRepository.save(paperPurchase);
    }

    public PaperPurchase updatePaperPurchase(PaperPurchase newPaperPurchase, Long id) {

        if (id == null) {
            return null;
        }

        PaperPurchase paperPurchase = getById(id);
        if (paperPurchase == null) {
            return null;
        }

        if (!checkPaperPurchaseValidity(paperPurchase)) {
            return null;
        }

        paperPurchase.setSuccessful(newPaperPurchase.getSuccessful());
        paperPurchase.setPaper(newPaperPurchase.getPaper());
        paperPurchase.setAmount(newPaperPurchase.getAmount());
        paperPurchase.setOption(newPaperPurchase.getOption());
        paperPurchase.setType(newPaperPurchase.getType());
        paperPurchase.setUser(newPaperPurchase.getUser());


        return paperPurchaseRepository.save(paperPurchase);
    }

    private boolean checkPaperPurchaseValidity(PaperPurchase paperPurchase) {
        if (paperPurchase.getAmount() == null) {
            return false;
        }

        if (paperPurchase.getType() == null) {
            return false;
        }

        if (paperPurchase.getOption() == null || paperPurchase.getOption().getPaymentOptionCode() == null) {
            return false;
        }

        if (paymentOptionRepository.getOne(paperPurchase.getOption().getPaymentOptionCode()) == null) {
            return false;
        }

        if (paperPurchase.getUser() == null || paperPurchase.getUser().getUsername() == null) {
            return false;
        }

        if (credentialsRepository.getOne(paperPurchase.getUser().getUsername()) == null) {
            return false;
        }

        if (paperPurchase.getPaper() == null || paperPurchase.getPaper().getDoi() == null) {
            return false;
        }

        if (paperRepository.getOne(paperPurchase.getPaper().getDoi()) == null) {
            return false;
        }

        return true;
    }

    public void deletePaperPurchase(Long id) {
        if (id != null) {
            paperPurchaseRepository.deleteById(id);
        }
    }


}
