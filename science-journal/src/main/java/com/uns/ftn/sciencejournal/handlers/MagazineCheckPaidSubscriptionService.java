package com.uns.ftn.sciencejournal.handlers;

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
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

@Service
public class MagazineCheckPaidSubscriptionService implements JavaDelegate {

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

        String issn = runtimeService.getVariable(delegateExecution.getId(), "issn").toString();
        Magazine magazine = magazineService.getById(issn);

        String initiator = runtimeService.getVariable(delegateExecution.getId(), "initiator").toString();
        Credentials buyer = credentialsService.getById(initiator);

        Long subscriptionId = (Long) runtimeService.getVariable(delegateExecution.getId(), "subscriptionId");
        Subscription subscription = subscriptionService.getById(subscriptionId);

        SubscriptionPurchase subscriptionPurchase = subscriptionService.findActiveUserSubscriptionForMagazine(issn, initiator);

        if(!subscription.getPaid() && subscriptionPurchase == null) {
            runtimeService.setVariable(delegateExecution.getId(), "payment_needed", true);
        } else if(subscriptionPurchase == null) {
            runtimeService.setVariable(delegateExecution.getId(), "payment_needed", true);
        }else {
            runtimeService.setVariable(delegateExecution.getId(), "payment_needed", false);
        }
    }
}
