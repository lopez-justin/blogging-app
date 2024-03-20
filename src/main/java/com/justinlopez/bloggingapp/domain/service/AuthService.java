package com.justinlopez.bloggingapp.domain.service;

import com.justinlopez.bloggingapp.config.security.JwtUtils;
import com.justinlopez.bloggingapp.domain.dto.AuthRequestDTO;
import com.justinlopez.bloggingapp.domain.dto.AuthResponseDTO;
import com.justinlopez.bloggingapp.domain.dto.UserRequestDTO;
import com.justinlopez.bloggingapp.domain.repository.IUserRepository;
import com.justinlopez.bloggingapp.domain.usecase.IAuthUseCase;
import com.justinlopez.bloggingapp.exception.UserAlreadyExistsException;
import com.justinlopez.bloggingapp.persistence.entity.RoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthUseCase {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final IUserRepository iUserRepository;

    @Override
    public AuthResponseDTO login(AuthRequestDTO authRequestDTO) {
        var authToken = new UsernamePasswordAuthenticationToken(authRequestDTO.getEmail(), authRequestDTO.getPassword());
        var authentication = authenticationManager.authenticate(authToken);

        // Get the username from the authentication object
        String username = ((UserDetails)(authentication.getPrincipal())).getUsername();
        // Generate a token
        String token = JwtUtils.generateToken(username);

        return new AuthResponseDTO(token);
    }

    @Override
    public AuthResponseDTO signUp(UserRequestDTO userRequestDTO) {
        // Check if the user already exists
        if (iUserRepository.existsByEmail(userRequestDTO.getEmail())) {
            throw new UserAlreadyExistsException(userRequestDTO.getEmail());
        }

        // Encode the password
        userRequestDTO.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));

        // Role
        userRequestDTO.setRole(RoleEnum.USER.name());

        // Save the user
        iUserRepository.save(userRequestDTO);

        // Generate and return a token
        return new AuthResponseDTO(JwtUtils.generateToken(userRequestDTO.getEmail()));
    }

    @Override
    public String verifyToken(String token) {
        Optional<String> usernameFromToken = JwtUtils.getUsernameFromToken(token);

        if (usernameFromToken.isPresent()) {
            return usernameFromToken.get();
        }

        throw new RuntimeException("Invalid token");
    }
}
