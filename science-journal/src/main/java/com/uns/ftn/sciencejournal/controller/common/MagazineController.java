package com.uns.ftn.sciencejournal.controller.common;

import com.uns.ftn.sciencejournal.dto.common.MagazineDTO;
import com.uns.ftn.sciencejournal.mapper.common.MagazineMapper;
import com.uns.ftn.sciencejournal.model.common.Magazine;
import com.uns.ftn.sciencejournal.service.common.MagazineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/magazines")
public class MagazineController {

    @Autowired
    MagazineService magazineService;

    @Autowired
    MagazineMapper magazineMapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MagazineDTO>> getAllMagazines() {
        return ResponseEntity.ok(magazineMapper.mapManyToDTO(magazineService.getAll()));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MagazineDTO> getMagazineById(@PathVariable("id") String id) {
        if (!id.equals(null)) {
            return ResponseEntity.ok(magazineMapper.mapToDTO(magazineService.getById(id)));
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MagazineDTO> createMagazine(@RequestBody MagazineDTO newMagazine) {
        if (!newMagazine.getIssn().equals(null)) {
            Magazine magazine = magazineService.createMagazine(magazineMapper.mapFromDTO(newMagazine));

            if (!magazine.equals(null)) {
                return ResponseEntity.ok(magazineMapper.mapToDTO(magazine));
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MagazineDTO> updateMagazine(@PathVariable("id") String id, @RequestBody MagazineDTO newMagazine) {
        if (!newMagazine.getIssn().equals(null) && !id.equals(null)) {
            Magazine magazine = magazineService.updateMagazine(magazineMapper.mapFromDTO(newMagazine), id);

            if (!magazine.equals(null)) {
                return ResponseEntity.ok(magazineMapper.mapToDTO(magazine));
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteMagazine(@PathVariable("id") String id) {
        if (!id.equals(null)) {
            magazineService.deleteMagazine(id);
            return ResponseEntity.ok(null);
        }

        return ResponseEntity.badRequest().build();
    }
}
