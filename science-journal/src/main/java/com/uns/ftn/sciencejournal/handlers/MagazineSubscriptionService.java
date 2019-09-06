package com.uns.ftn.sciencejournal.handlers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.uns.ftn.sciencejournal.model.common.Magazine;
import com.uns.ftn.sciencejournal.model.payment.Subscription;
import com.uns.ftn.sciencejournal.model.users.Credentials;
import com.uns.ftn.sciencejournal.service.common.MagazineService;
import com.uns.ftn.sciencejournal.service.payment.SubscriptionService;
import com.uns.ftn.sciencejournal.service.users.CredentialsService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.awt.*;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Service
public class MagazineSubscriptionService implements JavaDelegate {

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    MagazineService magazineService;

    @Autowired
    SubscriptionService subscriptionService;

    @Autowired
    CredentialsService credentialsService;

    @Autowired
    @Qualifier("simpleRestTemplate")
    private RestTemplate simpleRestTemplate;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        String issn = runtimeService.getVariable(delegateExecution.getId(), "magazine").toString();
        Magazine magazine = magazineService.getById(issn);

        String initiator = runtimeService.getVariable(delegateExecution.getId(), "initiator").toString();
        Credentials buyer = credentialsService.getById(initiator);

        Map<String, Object> elem = new HashMap<>();
        elem.put("payerId", buyer.getUserDetails().getEmail());
        elem.put("merchantId", magazine.getIssn());
        elem.put("merchandise", magazine.getName() + " subscription");
        elem.put("magazinId", magazine.getIssn());
        elem.put("amount", magazine.getMembership());
        elem.put("currency", magazine.getCurrency());
        elem.put("quantity", 1);

        ResponseEntity<String> uri = simpleRestTemplate.postForEntity("https://localhost:8080/api/subscribe", elem, String.class);

        Gson gson = new Gson();
        JsonObject obj = gson.fromJson(uri.getBody(), JsonObject.class);
        String link = obj.get("link").getAsString();
        String planID = obj.get("planID").getAsString();

        Subscription subscription = new Subscription();
        subscription.setId(planID);
        subscription.setCancelled(false);
        subscription.setMagazine(magazine);
        subscription.setUser(buyer);
        subscription.setPaid(false);

        subscription = subscriptionService.createSubscription(subscription);
        runtimeService.setVariable(delegateExecution.getId(), "subscriptionId", subscription.getId());
        runtimeService.setVariable(delegateExecution.getId(), "link", link);
    }
}
