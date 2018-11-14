package com.uns.ftn.sciencejournal.controller.payment;

import com.uns.ftn.sciencejournal.dto.payment.PaperPurchaseDTO;
import com.uns.ftn.sciencejournal.service.payment.PaperPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/api/paperPurchases")
public class PaperPurchaseController {

    @Autowired
    PaperPurchaseService paperPurchaseService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PaperPurchaseDTO>> getAllPaperPurchases() {
        return null;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaperPurchaseDTO> getPaperPurchaseById(@PathVariable("id") Long id) {
        return null;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaperPurchaseDTO> createPaperPurchase(@RequestBody PaperPurchaseDTO newPaperPurchase) {
        return null;
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaperPurchaseDTO> updatePaperPurchase(@PathVariable("id") Long id, @RequestBody PaperPurchaseDTO newPaperPurchase) {
        return null;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deletePaperPurchase(@PathVariable("id") Long id) {
        return null;
    }
}
