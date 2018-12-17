package com.uns.ftn.sciencejournal.controller.payment;

import com.uns.ftn.sciencejournal.dto.payment.PaperPurchaseDTO;
import com.uns.ftn.sciencejournal.mapper.payment.PaperPurchaseMapper;
import com.uns.ftn.sciencejournal.model.payment.PaperPurchase;
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

    @Autowired
    PaperPurchaseMapper paperPurchaseMapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PaperPurchaseDTO>> getAllPaperPurchases() {
        return ResponseEntity.ok(paperPurchaseMapper.mapManyToDTO(paperPurchaseService.getAll()));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaperPurchaseDTO> getPaperPurchaseById(@PathVariable("id") Long id) {
        if (!id.equals(null)) {
            return ResponseEntity.ok(paperPurchaseMapper.mapToDTO(paperPurchaseService.getById(id)));
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaperPurchaseDTO> createPaperPurchase(@RequestBody PaperPurchaseDTO newPaperPurchase) {
        if (newPaperPurchase.getId().equals(null)) {
            PaperPurchase paperPurchase = paperPurchaseService.createPaperPurchase(
                    paperPurchaseMapper.mapFromDTO(newPaperPurchase));

            if (!paperPurchase.equals(null)) {
                return ResponseEntity.ok(paperPurchaseMapper.mapToDTO(paperPurchase));
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaperPurchaseDTO> updatePaperPurchase(@PathVariable("id") Long id,
                                                                @RequestBody PaperPurchaseDTO newPaperPurchase) {
        if (!newPaperPurchase.getId().equals(null) && !id.equals(null)) {
            PaperPurchase paperPurchase = paperPurchaseService.updatePaperPurchase(
                    paperPurchaseMapper.mapFromDTO(newPaperPurchase), id);

            if (!paperPurchase.equals(null)) {
                return ResponseEntity.ok(paperPurchaseMapper.mapToDTO(paperPurchase));
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deletePaperPurchase(@PathVariable("id") Long id) {
        if (!id.equals(null)) {
            paperPurchaseService.deletePaperPurchase(id);
            return ResponseEntity.ok(null);
        }

        return ResponseEntity.badRequest().build();
    }
}
