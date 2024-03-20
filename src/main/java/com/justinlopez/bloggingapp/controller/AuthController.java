package com.justinlopez.bloggingapp.controller;

import com.justinlopez.bloggingapp.domain.dto.AuthRequestDTO;
import com.justinlopez.bloggingapp.domain.dto.AuthResponseDTO;
import com.justinlopez.bloggingapp.domain.dto.UserRequestDTO;
import com.justinlopez.bloggingapp.domain.usecase.IAuthUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IAuthUseCase iAuthUseCase;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO authRequestDTO) {
        return ResponseEntity.ok(iAuthUseCase.login(authRequestDTO));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<AuthResponseDTO> signUp(@RequestBody UserRequestDTO userRequestDTO) {
        try {
            return ResponseEntity.ok(iAuthUseCase.signUp(userRequestDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @PostMapping("/verify-token")
    public ResponseEntity<String> verifyToken(@RequestBody String token) {
        try {
            return ResponseEntity.ok(iAuthUseCase.verifyToken(token.substring(7)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
