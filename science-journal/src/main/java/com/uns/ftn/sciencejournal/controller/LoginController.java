package com.uns.ftn.sciencejournal.controller;

import com.uns.ftn.sciencejournal.configuration.JWTToken;
import com.uns.ftn.sciencejournal.configuration.JwtTokenProvider;
import com.uns.ftn.sciencejournal.dto.users.UserDetailsDTO;
import com.uns.ftn.sciencejournal.mapper.users.UserDetailsMapper;
import com.uns.ftn.sciencejournal.model.users.UserDetails;
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
    UserDetailsMapper userDetailsMapper;

    /*@Autowired
    CredentialsRepository credentialsRepository;*/

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JWTToken> authorize(@RequestBody /*Credentials*/String credentials) {
        if (loginService.login(credentials) == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(provider.createToken(/*credentials.getUsername()*/""));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDetailsDTO> getPrincipal(HttpServletRequest request) {
        String username = provider.parseToken(request);
        if (username == null) {
            return ResponseEntity.badRequest().build();
        }

        UserDetails userDetails = new UserDetails();
        return ResponseEntity.ok().body(userDetailsMapper.mapToDTO(/*credentialsRepository.getOne(username).getUserDetails()*/userDetails));
    }
}
