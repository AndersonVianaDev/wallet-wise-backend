package com.anderson.wallet_wise.controller;

import com.anderson.wallet_wise.controller.dtos.request.TransactionRequestDTO;
import com.anderson.wallet_wise.controller.dtos.response.TransactionResponseDTO;
import com.anderson.wallet_wise.domain.model.Transaction;
import com.anderson.wallet_wise.domain.model.User;
import com.anderson.wallet_wise.domain.services.ITranscationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final ITranscationService service;

    @PostMapping
    public ResponseEntity<TransactionResponseDTO> save(@AuthenticationPrincipal User user,
                                                       @RequestBody TransactionRequestDTO request) {
        final Transaction transaction = service.save(TransactionRequestDTO.from(user, request));
        final TransactionResponseDTO response = TransactionResponseDTO.of(transaction);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponseDTO> findByOwnerAndId(@AuthenticationPrincipal User user,
                                                                   @PathVariable("id")UUID id) {
        final Transaction transaction = service.findByOwnerAndId(user, id);
        final TransactionResponseDTO response = TransactionResponseDTO.of(transaction);

        return ResponseEntity.ok(response);
    }

}
