package com.uns.ftn.sciencejournal.mapper.common;

import com.uns.ftn.sciencejournal.dto.common.MagazineDTO;
import com.uns.ftn.sciencejournal.model.common.Magazine;
import com.uns.ftn.sciencejournal.model.common.ScienceField;
import com.uns.ftn.sciencejournal.model.payment.PaymentOption;
import com.uns.ftn.sciencejournal.repository.common.ScienceFieldRepository;
import com.uns.ftn.sciencejournal.repository.payment.PaymentOptionRepository;
import com.uns.ftn.sciencejournal.repository.users.EditorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class MagazineMapper {

    @Autowired
    EditorRepository editorRepository;

    @Autowired
    ScienceFieldRepository scienceFieldRepository;

    @Autowired
    PaymentOptionRepository paymentOptionRepository;

    public Magazine mapFromDTO(MagazineDTO dto) {
        Magazine magazine = new Magazine();

        magazine.setEditor(editorRepository.getOne(dto.getEditor()));
        magazine.setIssn(dto.getIssn());
        magazine.setMembership(dto.getMembership());
        magazine.setName(dto.getName());
        magazine.setType(dto.getType());

        magazine.setFields(new HashSet<>());
        for (String field : dto.getFields()) {
            magazine.getFields().add(scienceFieldRepository.getOne(field));
        }

        magazine.setOptions(new HashSet<>());
        for (Integer option : dto.getOptions()) {
            magazine.getOptions().add(paymentOptionRepository.getOne(option));
        }

        return magazine;
    }

    public MagazineDTO mapToDTO(Magazine magazine) {
        MagazineDTO dto = new MagazineDTO();

        dto.setEditor(magazine.getEditor().getId());
        dto.setIssn(magazine.getIssn());
        dto.setMembership(magazine.getMembership());
        dto.setName(magazine.getName());
        dto.setType(magazine.getType());

        dto.setFields(new HashSet<>());
        for (ScienceField field : magazine.getFields()) {
            dto.getFields().add(field.getCode());
        }

        dto.setOptions(new HashSet<>());
        for (PaymentOption option : magazine.getOptions()) {
            dto.getOptions().add(option.getPaymentOptionCode());
        }

        return dto;
    }
}
