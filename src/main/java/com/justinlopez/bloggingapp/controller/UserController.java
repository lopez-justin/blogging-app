package com.justinlopez.bloggingapp.controller;

import com.justinlopez.bloggingapp.domain.dto.UserResponseDTO;
import com.justinlopez.bloggingapp.domain.dto.UserRequestDTO;
import com.justinlopez.bloggingapp.domain.use_case.IUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final IUserUseCase iUserUseCase;

    // Create
    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(iUserUseCase.createUser(userRequestDTO));
    }

    // Update
    @PatchMapping
    public ResponseEntity<UserResponseDTO> updateUser(@RequestBody UserRequestDTO userRequestDTO) {
        return ResponseEntity.of(iUserUseCase.updateUser(userRequestDTO));
    }

    // Get by id
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.of(iUserUseCase.getUserById(id));
    }

    // Get all
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(iUserUseCase.getAllUsers());
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable Long id) {
        return new ResponseEntity<>(iUserUseCase.deleteUser(id) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

}
