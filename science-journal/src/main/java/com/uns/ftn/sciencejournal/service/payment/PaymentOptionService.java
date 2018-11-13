package com.uns.ftn.sciencejournal.service.payment;

import com.uns.ftn.sciencejournal.model.payment.PaymentOption;
import com.uns.ftn.sciencejournal.repository.payment.PaymentOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentOptionService {

    @Autowired
    PaymentOptionRepository paymentOptionRepository;

    public PaymentOption getById(Integer id) {
        return paymentOptionRepository.findById(id).orElse(null);
    }

    public List<PaymentOption> getAll() {
        return paymentOptionRepository.findAll();
    }

    public PaymentOption createPaymentOption(PaymentOption paymentOption) {

        if (paymentOption.getPaymentOptionCode() != null) {
            return null;
        }

        return paymentOptionRepository.save(paymentOption);
    }

    public PaymentOption updatePaymentOption(PaymentOption newPaymentOption, Integer id) {

        if (id == null) {
            return null;
        }

        PaymentOption paymentOption = getById(id);
        if (paymentOption != null) {

            return paymentOptionRepository.save(paymentOption);
        }

        return null;
    }

    public void deletePaymentOption(Integer id) {
        if (id != null) {
            paymentOptionRepository.deleteById(id);
        }
    }


}
