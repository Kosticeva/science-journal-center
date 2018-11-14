package com.uns.ftn.sciencejournal.mapper.users;

import com.uns.ftn.sciencejournal.dto.users.UserDTO;
import com.uns.ftn.sciencejournal.model.users.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public User mapFromDTO(UserDTO dto) {
        User user = new User();

        user.setCity(dto.getCity());
        user.setCountry(dto.getCountry());
        user.setEmail(dto.getEmail());
        user.setfName(dto.getfName());
        user.setlName(dto.getlName());
        user.setUserId(dto.getId());

        return user;
    }

    public UserDTO mapToDTO(User user) {
        UserDTO dto = new UserDTO();

        dto.setCity(user.getCity());
        dto.setCountry(user.getCountry());
        dto.setEmail(user.getEmail());
        dto.setfName(user.getfName());
        dto.setlName(user.getlName());
        dto.setId(user.getUserId());

        return dto;
    }
}
