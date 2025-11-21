package com.anderson.wallet_wise.controller.dtos.request;

import com.anderson.wallet_wise.domain.model.Budget;
import com.anderson.wallet_wise.domain.model.Category;
import com.anderson.wallet_wise.domain.model.User;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BudgetRequestDTO(
        Long categoryId,
        BigDecimal limitAmount,
        LocalDate startDate,
        LocalDate endDate
) {
    public static Budget from(User user, BudgetRequestDTO request) {
        final Category category = Category.builder()
                .id(request.categoryId).build();

        return Budget.builder()
                .owner(user)
                .category(category)
                .limitAmount(request.limitAmount())
                .startDate(request.startDate())
                .endDate(request.endDate())
                .build();
    }
}
