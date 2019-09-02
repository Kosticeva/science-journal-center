package com.uns.ftn.sciencejournal.service.payment;

import com.google.gson.Gson;
import com.uns.ftn.sciencejournal.model.enums.PurchaseType;
import com.uns.ftn.sciencejournal.model.payment.PaperPurchase;
import com.uns.ftn.sciencejournal.model.payment.PaymentOption;
import com.uns.ftn.sciencejournal.model.users.Credentials;
import com.uns.ftn.sciencejournal.repository.common.PaperRepository;
import com.uns.ftn.sciencejournal.repository.payment.PaperPurchaseRepository;
import com.uns.ftn.sciencejournal.repository.payment.PaymentOptionRepository;
import com.uns.ftn.sciencejournal.repository.users.CredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class PaperPurchaseService {

    @Autowired
    PaperPurchaseRepository paperPurchaseRepository;

    @Autowired
    PaperRepository paperRepository;

    @Autowired
    PaymentOptionRepository paymentOptionRepository;

    @Autowired
    CredentialsRepository credentialsRepository;

    @Autowired
    @Qualifier("simpleRestTemplate")
    RestTemplate simpleRestTemplate;

    public List<PaperPurchase> getAllFromUser(String username) {
        Credentials user = credentialsRepository.findFirstByUsername(username);
        if (user == null) {
            return new ArrayList<>();
        }

        String response = simpleRestTemplate.getForObject("https://localhost:8080/api/orders/getOrders/" + user.getUserDetails().getEmail(), String.class);
        Gson gson = new Gson();
        List responseMap = gson.fromJson(response, List.class);

        List<PaperPurchase> papers = new ArrayList<>();
        for (Object obj: responseMap) {
            Map<String, Object> objectJson = (Map<String, Object>) obj;

            PaperPurchase paperPurchase = new PaperPurchase();

            paperPurchase.setUser(user);
            paperPurchase.setAmount(((Double) objectJson.get("price")) * ((Double) objectJson.get("quantity")));
            paperPurchase.setTimeOfPurchase(LocalDateTime.parse(objectJson.get("merchantTimestamp").toString()));
            paperPurchase.setTransactionId(objectJson.get("merchantOrderId").toString());
            paperPurchase.setCurrency(objectJson.get("currency").toString());
            paperPurchase.setSuccessful((Boolean)objectJson.get("executed"));
            paperPurchase.setPaper(paperRepository.findFirstByTitle(objectJson.get("merchandise").toString()).get());
            paperPurchase.setType(PurchaseType.PAPER);
            //paperPurchase.setType(PaymentOption.objectJson.get("type"));

            paperPurchase = paperPurchaseRepository.save(paperPurchase);
            papers.add(paperPurchase);
        }

        return papers;
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
