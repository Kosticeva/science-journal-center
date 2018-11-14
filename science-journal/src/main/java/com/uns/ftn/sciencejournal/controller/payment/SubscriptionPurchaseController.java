package com.uns.ftn.sciencejournal.controller.payment;

import com.uns.ftn.sciencejournal.dto.payment.SubscriptionPurchaseDTO;
import com.uns.ftn.sciencejournal.mapper.payment.SubscriptionPurchaseMapper;
import com.uns.ftn.sciencejournal.model.payment.SubscriptionPurchase;
import com.uns.ftn.sciencejournal.service.payment.SubscriptionPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/api/subscriptionPurchases")
public class SubscriptionPurchaseController {

    @Autowired
    SubscriptionPurchaseService subscriptionPurchaseService;

    @Autowired
    SubscriptionPurchaseMapper subscriptionPurchaseMapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SubscriptionPurchaseDTO>> getAllSubscriptionPurchases() {
        return ResponseEntity.ok(subscriptionPurchaseMapper.mapManyToDTO(subscriptionPurchaseService.getAll()));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SubscriptionPurchaseDTO> getSubscriptionPurchaseById(@PathVariable("id") Long id) {
        if (!id.equals(null)) {
            return ResponseEntity.ok(subscriptionPurchaseMapper.mapToDTO(subscriptionPurchaseService.getById(id)));
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SubscriptionPurchaseDTO> createSubscriptionPurchase(@RequestBody SubscriptionPurchaseDTO newSubscriptionPurchase) {
        if (newSubscriptionPurchase.getId().equals(null)) {
            SubscriptionPurchase subscriptionPurchase = subscriptionPurchaseService.createSubscriptionPurchase(subscriptionPurchaseMapper.mapFromDTO(newSubscriptionPurchase));

            if (!subscriptionPurchase.equals(null)) {
                return ResponseEntity.ok(subscriptionPurchaseMapper.mapToDTO(subscriptionPurchase));
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SubscriptionPurchaseDTO> updateSubscriptionPurchase(@PathVariable("id") Long id, @RequestBody SubscriptionPurchaseDTO newSubscriptionPurchase) {
        if (!newSubscriptionPurchase.getId().equals(null) && !id.equals(null)) {
            SubscriptionPurchase subscriptionPurchase = subscriptionPurchaseService.updateSubscriptionPurchase(subscriptionPurchaseMapper.mapFromDTO(newSubscriptionPurchase), id);

            if (!subscriptionPurchase.equals(null)) {
                return ResponseEntity.ok(subscriptionPurchaseMapper.mapToDTO(subscriptionPurchase));
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteSubscriptionPurchase(@PathVariable("id") Long id) {
        if (!id.equals(null)) {
            subscriptionPurchaseService.deleteSubscriptionPurchase(id);
            return ResponseEntity.ok(null);
        }

        return ResponseEntity.badRequest().build();
    }
}
