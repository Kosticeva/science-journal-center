package com.uns.ftn.sciencejournal.handlers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.uns.ftn.sciencejournal.model.common.Magazine;
import com.uns.ftn.sciencejournal.model.payment.Subscription;
import com.uns.ftn.sciencejournal.model.payment.SubscriptionPurchase;
import com.uns.ftn.sciencejournal.model.users.Credentials;
import com.uns.ftn.sciencejournal.service.common.MagazineService;
import com.uns.ftn.sciencejournal.service.payment.SubscriptionPurchaseService;
import com.uns.ftn.sciencejournal.service.payment.SubscriptionService;
import com.uns.ftn.sciencejournal.service.users.CredentialsService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class SubscriptionPaidService implements TaskListener {

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    SubscriptionService subscriptionService;

    @Autowired
    SubscriptionPurchaseService subscriptionPurchaseService;

    @Autowired
    @Qualifier("simpleRestTemplate")
    private RestTemplate simpleRestTemplate;

    @Override
    public void notify(DelegateTask delegateTask) {

        DelegateExecution delegateExecution = delegateTask.getExecution();
        String subId = runtimeService.getVariable(delegateExecution.getId(), "subscriptionId").toString();
        Subscription subscription = subscriptionService.getById(subId);

        Gson gson = new Gson();

        if (subscription.getPaid() != null) {
            String subscriptionId = subscription.getId();

            //fixme check uRL
            ResponseEntity<String> res = simpleRestTemplate.getForEntity(
                    URI.create("https://localhost:8080/api/subscription/" + subscriptionId), String.class);

            //fixme check what link to get
            JsonObject obj = gson.fromJson(res.getBody(), JsonObject.class);
            String status = obj.get("state").getAsString();

            //check if pending or not
            if ("ACTIVE".equals(status)) {

                Magazine magazine = subscription.getMagazine();
                Credentials buyer = subscription.getUser();

                IntStream.range(0, 12).forEach(
                        i -> {

                            SubscriptionPurchase subscriptionPurchase = new SubscriptionPurchase();
                            subscriptionPurchase.setAmount(magazine.getMembership());
                            subscriptionPurchase.setCurrency(magazine.getCurrency());
                            subscriptionPurchase.setSubscription(subscription);
                            subscriptionPurchase.setUser(buyer);
                            subscriptionPurchase.setTimeOfPurchase(LocalDateTime.now().plusMonths(i));

                            subscriptionPurchaseService.createSubscriptionPurchase(subscriptionPurchase);
                        }
                );

                subscription.setPaid(true);
                subscription.setDate(LocalDate.now().plusYears(1));
                subscriptionService.updateSubscription(subscription, subscriptionId);

                runtimeService.setVariable(delegateExecution.getId(), "paid", true);
            } else {
                runtimeService.setVariable(delegateExecution.getId(), "paid", false);
            }
        } else {
            runtimeService.setVariable(delegateExecution.getId(), "paid", false);
        }
    }
}
