package com.anderson.wallet_wise.controller.dtos.response;

import com.anderson.wallet_wise.domain.model.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record TransactionResponseDTO(
        UUID id,
        BigDecimal value,
        LocalDateTime transactionDate,
        CategoryResponseDTO category,
        String description,
        UserResponseDTO owner
) {
    public static TransactionResponseDTO of(Transaction transaction) {
        final CategoryResponseDTO categoryResponseDTO = CategoryResponseDTO.of(transaction.getCategory());
        final UserResponseDTO userResponseDTO = UserResponseDTO.of(transaction.getOwner());

        return new TransactionResponseDTO(
                transaction.getId(),
                transaction.getValue(),
                transaction.getTransactionDate(),
                categoryResponseDTO,
                transaction.getDescription(),
                userResponseDTO
        );
    }
}
