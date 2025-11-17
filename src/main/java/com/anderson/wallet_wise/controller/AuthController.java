package com.anderson.wallet_wise.controller;


import com.anderson.wallet_wise.controller.dtos.request.LoginRequestDTO;
import com.anderson.wallet_wise.controller.dtos.response.LoginResponseDTO;
import com.anderson.wallet_wise.domain.model.User;
import com.anderson.wallet_wise.infra.security.token.ITokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class AuthController {

    private final ITokenService tokenService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {
        final var usernamePassword = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        final User user = (User) authenticationManager.authenticate(usernamePassword).getPrincipal();
        final LoginResponseDTO response = new LoginResponseDTO(tokenService.generate(user.getId()));

        return ResponseEntity.ok(response);
    }

}