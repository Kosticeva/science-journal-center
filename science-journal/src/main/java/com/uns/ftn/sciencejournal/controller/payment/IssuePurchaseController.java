package com.uns.ftn.sciencejournal.controller.payment;

import com.uns.ftn.sciencejournal.dto.payment.IssuePurchaseDTO;
import com.uns.ftn.sciencejournal.service.payment.IssuePurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/api/issuePurchases")
public class IssuePurchaseController {

    @Autowired
    IssuePurchaseService issuePurchaseService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<IssuePurchaseDTO>> getAllIssuePurchases() {
        return null;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IssuePurchaseDTO> getIssuePurchaseById(@PathVariable("id") Long id) {
        return null;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IssuePurchaseDTO> createIssuePurchase(@RequestBody IssuePurchaseDTO newIssuePurchase) {
        return null;
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IssuePurchaseDTO> updateIssuePurchase(@PathVariable("id") Long id, @RequestBody IssuePurchaseDTO newIssuePurchase) {
        return null;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteIssuePurchase(@PathVariable("id") Long id) {
        return null;
    }
}
