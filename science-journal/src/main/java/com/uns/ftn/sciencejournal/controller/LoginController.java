package com.uns.ftn.sciencejournal.controller;

import com.uns.ftn.sciencejournal.configuration.JWTToken;
import com.uns.ftn.sciencejournal.configuration.JwtTokenProvider;
import com.uns.ftn.sciencejournal.dto.users.UserDTO;
import com.uns.ftn.sciencejournal.mapper.users.UserMapper;
import com.uns.ftn.sciencejournal.model.users.Credentials;
import com.uns.ftn.sciencejournal.model.users.User;
import com.uns.ftn.sciencejournal.repository.users.CredentialsRepository;
import com.uns.ftn.sciencejournal.repository.users.UserRepository;
import com.uns.ftn.sciencejournal.service.users.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/login")
@CrossOrigin(origins = "http://localhost:4201")
public class LoginController {

    @Autowired
    LoginService loginService;

    @Autowired
    JwtTokenProvider provider;

    @Autowired
    UserMapper userMapper;

    @Autowired
    CredentialsRepository credentialsRepository;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JWTToken> authorize(@RequestBody Credentials credentials) {
        if(loginService.login(credentials) == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(provider.createToken(credentials.getUsername()));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> getPrincipal(HttpServletRequest request) {
        String username = provider.parseToken(request);
        if(username == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().body(userMapper.mapToDTO(credentialsRepository.getOne(username).getUserDetails()));
    }
}
