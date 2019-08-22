package com.uns.ftn.sciencejournal.mapper.users;

import com.uns.ftn.sciencejournal.dto.users.UserDetailsDTO;
import com.uns.ftn.sciencejournal.model.users.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsMapper {

    public UserDetails mapFromDTO(UserDetailsDTO dto) {
        UserDetails userDetails = new UserDetails();

        userDetails.setCity(dto.getCity());
        userDetails.setCountry(dto.getCountry());
        userDetails.setEmail(dto.getEmail());
        userDetails.setfName(dto.getfName());
        userDetails.setlName(dto.getlName());
        userDetails.setUserId(dto.getId());
        userDetails.setLatitude(dto.getLatitude());
        userDetails.setLongitude(dto.getLongitude());

        return userDetails;
    }

    public UserDetailsDTO mapToDTO(UserDetails userDetails) {
        UserDetailsDTO dto = new UserDetailsDTO();

        dto.setCity(userDetails.getCity());
        dto.setCountry(userDetails.getCountry());
        dto.setEmail(userDetails.getEmail());
        dto.setfName(userDetails.getfName());
        dto.setlName(userDetails.getlName());
        dto.setId(userDetails.getUserId());
        dto.setLatitude(userDetails.getLatitude());
        dto.setLongitude(userDetails.getLongitude());

        return dto;
    }

    public List<UserDetails> mapManyFromDTO(List<UserDetailsDTO> userDetailsDTOS) {
        List<UserDetails> userDetails = new ArrayList<>();
        for (UserDetailsDTO userDetailsDTO : userDetailsDTOS) {
            userDetails.add(mapFromDTO(userDetailsDTO));
        }

        return userDetails;
    }

    public List<UserDetailsDTO> mapManyToDTO(List<UserDetails> usersDetails) {
        List<UserDetailsDTO> userDetailsDTOS = new ArrayList<>();
        for (UserDetails userDetails : usersDetails) {
            userDetailsDTOS.add(mapToDTO(userDetails));
        }

        return userDetailsDTOS;
    }
}
