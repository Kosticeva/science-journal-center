package com.uns.ftn.sciencejournal.service.payment;

import com.uns.ftn.sciencejournal.model.payment.IssuePurchase;
import com.uns.ftn.sciencejournal.repository.payment.IssuePurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssuePurchaseService {

    @Autowired
    IssuePurchaseRepository issuePurchaseRepository;

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

        return issuePurchaseRepository.save(issuePurchase);
    }

    public IssuePurchase updateIssuePurchase(IssuePurchase newIssuePurchase, Long id) {

        if (id == null) {
            return null;
        }

        IssuePurchase issuePurchase = getById(id);
        if (issuePurchase != null) {

            return issuePurchaseRepository.save(issuePurchase);
        }

        return null;
    }

    public void deleteIssuePurchase(Long id) {
        if (id != null) {
            issuePurchaseRepository.deleteById(id);
        }
    }


}
