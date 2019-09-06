package com.uns.ftn.sciencejournal.service.payment;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.uns.ftn.sciencejournal.model.common.Magazine;
import com.uns.ftn.sciencejournal.model.payment.Subscription;
import com.uns.ftn.sciencejournal.model.payment.SubscriptionPurchase;
import com.uns.ftn.sciencejournal.model.users.Credentials;
import com.uns.ftn.sciencejournal.repository.payment.PaymentOptionRepository;
import com.uns.ftn.sciencejournal.repository.payment.SubscriptionPurchaseRepository;
import com.uns.ftn.sciencejournal.repository.users.CredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

@Service
public class SubscriptionPurchaseService {

    @Autowired
    SubscriptionPurchaseRepository subscriptionPurchaseRepository;

    @Autowired
    SubscriptionService subscriptionService;

    @Autowired
    PaymentOptionRepository paymentOptionRepository;

    @Autowired
    CredentialsRepository credentialsRepository;

    @Autowired
    @Qualifier("simpleRestTemplate")
    private RestTemplate simpleRestTemplate;

    public List<SubscriptionPurchase> getAllFromUser(String username) {
        Credentials user = credentialsRepository.findFirstByUsername(username);
        if (user == null) {
            return new ArrayList<>();
        }

        return subscriptionPurchaseRepository.getByUser(user);
    }

    public SubscriptionPurchase getById(Long id) {
        return subscriptionPurchaseRepository.findById(id).orElse(null);
    }

    public List<SubscriptionPurchase> getAll() {
        return subscriptionPurchaseRepository.findAll();
    }

    public SubscriptionPurchase createSubscriptionPurchase(SubscriptionPurchase subscriptionPurchase) {

        if (!checkSubscriptionPurchaseValidity(subscriptionPurchase)) {
            return null;
        }

        subscriptionPurchase.setSuccessful(true);
        subscriptionPurchase.setTransactionId(UUID.randomUUID().toString());

        return subscriptionPurchaseRepository.save(subscriptionPurchase);
    }

    public SubscriptionPurchase updateSubscriptionPurchase(SubscriptionPurchase newSubscriptionPurchase, Long id) {

        if (id == null) {
            return null;
        }

        SubscriptionPurchase subscriptionPurchase = getById(id);
        if (subscriptionPurchase == null) {
            return null;
        }

        if (!checkSubscriptionPurchaseValidity(subscriptionPurchase)) {
            return null;
        }

        subscriptionPurchase.setSuccessful(newSubscriptionPurchase.getSuccessful());
        subscriptionPurchase.setSubscription(newSubscriptionPurchase.getSubscription());
        subscriptionPurchase.setAmount(newSubscriptionPurchase.getAmount());
        subscriptionPurchase.setOption(newSubscriptionPurchase.getOption());
        subscriptionPurchase.setType(newSubscriptionPurchase.getType());
        subscriptionPurchase.setUser(newSubscriptionPurchase.getUser());

        return subscriptionPurchaseRepository.save(subscriptionPurchase);
    }

    private boolean checkSubscriptionPurchaseValidity(SubscriptionPurchase subscriptionPurchase) {
        if (subscriptionPurchase.getAmount() == null) {
            return false;
        }

        /*if (subscriptionPurchase.getOption() == null || subscriptionPurchase.getOption().getPaymentOptionCode() == null) {
            return false;
        }

        if (paymentOptionRepository.getOne(subscriptionPurchase.getOption().getPaymentOptionCode()) == null) {
            return false;
        }*/

        if (subscriptionPurchase.getUser() == null || subscriptionPurchase.getUser().getUsername() == null) {
            return false;
        }

        if (credentialsRepository.getOne(subscriptionPurchase.getUser().getUsername()) == null) {
            return false;
        }

        if (subscriptionPurchase.getSubscription() == null || subscriptionPurchase.getSubscription().getId() == null) {
            return false;
        }

        if (subscriptionService.getById(subscriptionPurchase.getSubscription().getId()) == null) {
            return false;
        }

        return true;
    }

    public void deleteSubscriptionPurchase(Long id) {
        if (id != null) {
            subscriptionPurchaseRepository.deleteById(id);
        }
    }
}
