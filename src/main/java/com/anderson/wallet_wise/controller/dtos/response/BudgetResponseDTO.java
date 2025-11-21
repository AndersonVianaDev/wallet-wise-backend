package com.anderson.wallet_wise.controller.dtos.response;

import com.anderson.wallet_wise.domain.model.Budget;
import com.anderson.wallet_wise.domain.model.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record BudgetResponseDTO(
        UUID id,
        UserResponseDTO owner,
        CategoryResponseDTO category,
        BigDecimal limitAmount,
        LocalDate startDate,
        LocalDate endDate,
        Boolean active
) {
    public static BudgetResponseDTO of(Budget budget) {
        final UserResponseDTO owner = UserResponseDTO.of(budget.getOwner());
        final CategoryResponseDTO category = CategoryResponseDTO.of(budget.getCategory());

        return new BudgetResponseDTO(
                budget.getId(),
                owner,
                category,
                budget.getLimitAmount(),
                budget.getStartDate(),
                budget.getEndDate(),
                budget.getActive()
        );
    }
}
