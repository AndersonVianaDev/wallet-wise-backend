package com.anderson.wallet_wise.controller.dtos.request;

import com.anderson.wallet_wise.domain.model.Category;
import com.anderson.wallet_wise.domain.model.Transaction;
import com.anderson.wallet_wise.domain.model.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionRequestDTO(
        BigDecimal value,
        LocalDateTime transactionDate,
        Long categoryId,
        String description
) {

    public static Transaction from(User user, TransactionRequestDTO request) {
        final Category category = Category.builder()
                .id(request.categoryId())
                .build();

        return Transaction.builder()
                .value(request.value())
                .category(category)
                .description(request.description())
                .transactionDate(request.transactionDate())
                .owner(user)
                .build();
    }
}
