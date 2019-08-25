package com.uns.ftn.sciencejournal.handlers;

import com.uns.ftn.sciencejournal.model.common.Magazine;
import com.uns.ftn.sciencejournal.model.enums.SubscriptionType;
import com.uns.ftn.sciencejournal.model.payment.Subscription;
import com.uns.ftn.sciencejournal.model.users.Credentials;
import com.uns.ftn.sciencejournal.service.common.MagazineService;
import com.uns.ftn.sciencejournal.service.payment.SubscriptionService;
import com.uns.ftn.sciencejournal.service.users.CredentialsService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        String issn = runtimeService.getVariable(delegateExecution.getId(), "issn").toString();
        Magazine magazine = magazineService.getById(issn);

        String initiator = runtimeService.getVariable(delegateExecution.getId(), "initiator").toString();
        Credentials buyer = credentialsService.getById(initiator);

        Subscription subscription = new Subscription();
        subscription.setCancelled(false);
        subscription.setMagazine(magazine);
        subscription.setUser(buyer);
        subscription.setPaid(false);

        subscription = subscriptionService.createSubscription(subscription);
        runtimeService.setVariable(delegateExecution.getId(), "subscriptionId", subscription.getId());
    }
}
