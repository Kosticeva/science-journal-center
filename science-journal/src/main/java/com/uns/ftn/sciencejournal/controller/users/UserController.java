package com.uns.ftn.sciencejournal.controller.users;

import com.uns.ftn.sciencejournal.dto.users.UserDTO;
import com.uns.ftn.sciencejournal.mapper.users.UserMapper;
import com.uns.ftn.sciencejournal.model.users.User;
import com.uns.ftn.sciencejournal.service.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userMapper.mapManyToDTO(userService.getAll()));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id) {
        if (!id.equals(null)) {
            return ResponseEntity.ok(userMapper.mapToDTO(userService.getById(id)));
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO newUser) {
        if (newUser.getId() == null) {
            User user = userService.createUser(userMapper.mapFromDTO(newUser));

            if (!user.equals(null)) {
                return ResponseEntity.ok(userMapper.mapToDTO(user));
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") Long id, @RequestBody UserDTO newUser) {
        if (!newUser.getId().equals(null) && !id.equals(null)) {
            User user = userService.updateUser(userMapper.mapFromDTO(newUser), id);

            if (!user.equals(null)) {
                return ResponseEntity.ok(userMapper.mapToDTO(user));
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") Long id) {
        if (!id.equals(null)) {
            userService.deleteUser(id);
            return ResponseEntity.ok(null);
        }

        return ResponseEntity.badRequest().build();
    }
}
