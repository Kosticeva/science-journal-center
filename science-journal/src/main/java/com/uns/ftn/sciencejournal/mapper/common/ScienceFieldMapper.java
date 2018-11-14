package com.uns.ftn.sciencejournal.mapper.common;

import com.uns.ftn.sciencejournal.dto.common.ScienceFieldDTO;
import com.uns.ftn.sciencejournal.model.common.ScienceField;
import org.springframework.stereotype.Service;

@Service
public class ScienceFieldMapper {

    public ScienceField mapFromDTO(ScienceFieldDTO dto) {
        ScienceField field = new ScienceField();

        field.setCode(dto.getCode());
        field.setName(dto.getName());

        return field;
    }

    public ScienceFieldDTO mapToDTO(ScienceField scienceField) {
        ScienceFieldDTO dto = new ScienceFieldDTO();

        dto.setCode(scienceField.getCode());
        dto.setName(scienceField.getName());

        return dto;
    }
}
