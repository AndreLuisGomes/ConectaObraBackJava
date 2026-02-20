package com.conectaobra.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TokenController {

    private final JwtEncoder jwtEncoder;


}
