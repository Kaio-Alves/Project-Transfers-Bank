package com.bank.transfers.web.controller;

import com.bank.transfers.application.UserService;
import com.bank.transfers.infrastructure.security.JwtProvider;
import com.bank.transfers.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authManager;
    private final JwtProvider jwtProvider;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Validated @RequestBody RegisterRequest req) {
        userService.register(req.getUsername(), req.getPassword());
        return ResponseEntity.ok("Usuário registrado");
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@Validated @RequestBody LoginRequest req) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
        );
        UserDetails ud = (UserDetails) auth.getPrincipal();
        String token = jwtProvider.generateToken(ud.getUsername());
        return ResponseEntity.ok(new TokenResponse(token));
    }
}
