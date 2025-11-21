package com.anderson.wallet_wise.controller;

import com.anderson.wallet_wise.controller.dtos.request.BudgetRequestDTO;
import com.anderson.wallet_wise.controller.dtos.response.BudgetResponseDTO;
import com.anderson.wallet_wise.domain.model.Budget;
import com.anderson.wallet_wise.domain.model.User;
import com.anderson.wallet_wise.domain.services.IBudgetService;
import com.anderson.wallet_wise.infra.database.repositories.BudgetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/budget")
@RequiredArgsConstructor
public class BudgetController {

    private final IBudgetService service;

    @PostMapping
    public ResponseEntity<BudgetResponseDTO> save(@AuthenticationPrincipal User user,
                                                  @RequestBody BudgetRequestDTO request) {
        final Budget budget = service.save(BudgetRequestDTO.from(user, request));
        final BudgetResponseDTO response = BudgetResponseDTO.of(budget);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BudgetResponseDTO> findByOwnerAndId(@AuthenticationPrincipal User user,
                                                              @PathVariable("id")UUID id) {
        final Budget budget = service.findByOwnerAndId(user, id);
        final BudgetResponseDTO response = BudgetResponseDTO.of(budget);

        return ResponseEntity.ok(response);
    }
}
