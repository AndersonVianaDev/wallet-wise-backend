package com.anderson.wallet_wise.controller;

import com.anderson.wallet_wise.controller.dtos.request.UserRequestDTO;
import com.anderson.wallet_wise.controller.dtos.response.UserResponseDTO;
import com.anderson.wallet_wise.domain.model.User;
import com.anderson.wallet_wise.domain.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService service;

    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@RequestBody UserRequestDTO request) {
        final User user = service.save(UserRequestDTO.from(request));
        final UserResponseDTO response = UserResponseDTO.of(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
