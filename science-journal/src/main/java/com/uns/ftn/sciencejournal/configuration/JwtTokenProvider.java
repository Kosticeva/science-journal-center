package com.uns.ftn.sciencejournal.configuration;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import static com.uns.ftn.sciencejournal.configuration.JWTConstants.HEADER_STRING;

@Service
public class JwtTokenProvider {

    public JWTToken createToken(String username) {
        /*String token = JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + JWTConstants.EXPIRATION_TIME))
                .sign(HMAC512(JWTConstants.SECRET.getBytes()));*/
        return new JWTToken("");
    }

    public String parseToken(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            // parse the token.
            /*String user = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                    .build()
                    .verify(token.replace(TOKEN_PREFIX, ""))
                    .getSubject();*/

            return /*user*/"";
        }

        return null;
    }

}
