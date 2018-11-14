package com.uns.ftn.sciencejournal.mapper.common;

import com.uns.ftn.sciencejournal.dto.common.ScienceFieldDTO;
import com.uns.ftn.sciencejournal.model.common.ScienceField;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<ScienceField> mapManyFromDTO(List<ScienceFieldDTO> scienceFieldDTOs) {
        List<ScienceField> scienceFields = new ArrayList<>();
        for (ScienceFieldDTO scienceFieldDTO : scienceFieldDTOs) {
            scienceFields.add(mapFromDTO(scienceFieldDTO));
        }

        return scienceFields;
    }

    public List<ScienceFieldDTO> mapManyToDTO(List<ScienceField> scienceFields) {
        List<ScienceFieldDTO> scienceFieldDTOs = new ArrayList<>();
        for (ScienceField scienceField : scienceFields) {
            scienceFieldDTOs.add(mapToDTO(scienceField));
        }

        return scienceFieldDTOs;
    }
}
