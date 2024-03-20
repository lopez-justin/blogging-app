package com.justinlopez.bloggingapp.domain.usecase;

import com.justinlopez.bloggingapp.domain.dto.AuthRequestDTO;
import com.justinlopez.bloggingapp.domain.dto.AuthResponseDTO;
import com.justinlopez.bloggingapp.domain.dto.UserRequestDTO;

public interface IAuthUseCase {

    AuthResponseDTO login(AuthRequestDTO authRequestDTO);

    AuthResponseDTO signUp(UserRequestDTO userRequestDTO);

    String verifyToken(String token);

}
