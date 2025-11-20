package com.anderson.wallet_wise.controller;

import com.anderson.wallet_wise.controller.dtos.request.CategoryRequestDTO;
import com.anderson.wallet_wise.controller.dtos.response.CategoryResponseDTO;
import com.anderson.wallet_wise.domain.model.Category;
import com.anderson.wallet_wise.domain.model.User;
import com.anderson.wallet_wise.domain.services.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final ICategoryService service;

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> save(@AuthenticationPrincipal User user,
                                                    @RequestBody CategoryRequestDTO request) {
        final Category category = service.save(CategoryRequestDTO.from(user, request));
        final CategoryResponseDTO response = CategoryResponseDTO.of(category);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> findByOwnerOrOwnerIsNullAndId(@AuthenticationPrincipal User user,
                                                                @PathVariable("id") Long id) {
        final Category category = service.findByOwnerOrOwnerIsNullAndId(user, id);
        final CategoryResponseDTO response = CategoryResponseDTO.of(category);

        return ResponseEntity.ok(response);
    }
}
