package com.uns.ftn.sciencejournal.handlers;

import com.uns.ftn.sciencejournal.model.common.Magazine;
import com.uns.ftn.sciencejournal.model.enums.PurchaseType;
import com.uns.ftn.sciencejournal.model.payment.PaymentOption;
import com.uns.ftn.sciencejournal.model.payment.Subscription;
import com.uns.ftn.sciencejournal.model.payment.SubscriptionPurchase;
import com.uns.ftn.sciencejournal.model.users.Credentials;
import com.uns.ftn.sciencejournal.repository.payment.PaymentOptionRepository;
import com.uns.ftn.sciencejournal.service.common.MagazineService;
import com.uns.ftn.sciencejournal.service.payment.SubscriptionPurchaseService;
import com.uns.ftn.sciencejournal.service.payment.SubscriptionService;
import com.uns.ftn.sciencejournal.service.users.CredentialsService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MagazinePaySubscriptionService implements JavaDelegate {

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    MagazineService magazineService;

    @Autowired
    SubscriptionService subscriptionService;

    @Autowired
    CredentialsService credentialsService;

    @Autowired
    SubscriptionPurchaseService subscriptionPurchaseService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        String issn = runtimeService.getVariable(delegateExecution.getId(), "magazine").toString();
        Magazine magazine = magazineService.getById(issn);

        String initiator = runtimeService.getVariable(delegateExecution.getId(), "initiator").toString();
        Credentials buyer = credentialsService.getById(initiator);

        Long subscriptionId = (Long) runtimeService.getVariable(delegateExecution.getId(), "subscriptionId");
        Subscription subscription = subscriptionService.getById(subscriptionId);

        SubscriptionPurchase subscriptionPurchase = new SubscriptionPurchase();
        subscriptionPurchase.setAmount(magazine.getMembership());
        subscriptionPurchase.setCurrency(magazine.getCurrency());
        subscriptionPurchase.setSubscription(subscription);
        subscriptionPurchase.setUser(buyer);

        subscriptionPurchase = subscriptionPurchaseService.createSubscriptionPurchase(subscriptionPurchase);

        subscription.setPaid(true);
        subscriptionService.updateSubscription(subscription, subscriptionId);
    }
}
