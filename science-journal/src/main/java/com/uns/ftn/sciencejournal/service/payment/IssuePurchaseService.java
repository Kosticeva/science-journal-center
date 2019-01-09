package com.uns.ftn.sciencejournal.service.payment;

import com.uns.ftn.sciencejournal.model.payment.IssuePurchase;
import com.uns.ftn.sciencejournal.repository.common.IssueRepository;
import com.uns.ftn.sciencejournal.repository.payment.IssuePurchaseRepository;
import com.uns.ftn.sciencejournal.repository.payment.PaymentOptionRepository;
import com.uns.ftn.sciencejournal.repository.users.CredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class IssuePurchaseService {

    @Autowired
    IssuePurchaseRepository issuePurchaseRepository;

    @Autowired
    IssueRepository issueRepository;

    @Autowired
    CredentialsRepository credentialsRepository;

    @Autowired
    PaymentOptionRepository paymentOptionRepository;

    public IssuePurchase getById(Long id) {
        return issuePurchaseRepository.findById(id).orElse(null);
    }

    public List<IssuePurchase> getAll() {
        return issuePurchaseRepository.findAll();
    }

    public IssuePurchase createIssuePurchase(IssuePurchase issuePurchase) {

        if (issuePurchase.getTransactionId() != null) {
            return null;
        }

        /*RestTemplate client = new RestTemplate();
        OrderDTO dto = new OrderDTO();
        dto.setAmount(issuePurchase.getAmount());
        try {
            ResponseEntity<OrderDTO> newDTO = client.postForEntity(new URI("http://localhost:8080/orders"), dto, OrderDTO.class);
        }catch (URISyntaxException e){

        }*/
        if (!checkIssuePurchaseValidity(issuePurchase)) {
            return null;
        }

        issuePurchase.setTimeOfPurchase(LocalDateTime.now());
        issuePurchase.setSuccessful(null);

        return issuePurchaseRepository.save(issuePurchase);
    }

    public IssuePurchase updateIssuePurchase(IssuePurchase newIssuePurchase, Long id) {

        if (id == null) {
            return null;
        }

        IssuePurchase issuePurchase = getById(id);
        if (issuePurchase == null) {
            return null;
        }

        if (!checkIssuePurchaseValidity(issuePurchase)) {
            return null;
        }

        issuePurchase.setSuccessful(newIssuePurchase.getSuccessful());
        issuePurchase.setIssue(newIssuePurchase.getIssue());
        issuePurchase.setAmount(newIssuePurchase.getAmount());
        issuePurchase.setOption(newIssuePurchase.getOption());
        issuePurchase.setType(newIssuePurchase.getType());
        issuePurchase.setUser(newIssuePurchase.getUser());

        return issuePurchaseRepository.save(issuePurchase);
    }

    private boolean checkIssuePurchaseValidity(IssuePurchase issuePurchase) {
        if (issuePurchase.getAmount() == null) {
            return false;
        }

        if (issuePurchase.getType() == null) {
            return false;
        }

        if (issuePurchase.getOption() == null || issuePurchase.getOption().getPaymentOptionCode() == null) {
            return false;
        }

        if (paymentOptionRepository.getOne(issuePurchase.getOption().getPaymentOptionCode()) == null) {
            return false;
        }

        if (issuePurchase.getUser() == null || issuePurchase.getUser().getUsername() == null) {
            return false;
        }

        if (credentialsRepository.getOne(issuePurchase.getUser().getUsername()) == null) {
            return false;
        }

        if (issuePurchase.getIssue() == null || issuePurchase.getIssue().getId() == null) {
            return false;
        }

        if (issueRepository.getOne(issuePurchase.getIssue().getId()) == null) {
            return false;
        }

        return true;
    }

    public void deleteIssuePurchase(Long id) {
        if (id != null) {
            issuePurchaseRepository.deleteById(id);
        }
    }


}
