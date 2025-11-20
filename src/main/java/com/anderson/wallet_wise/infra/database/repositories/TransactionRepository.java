package com.anderson.wallet_wise.infra.database.repositories;

import com.anderson.wallet_wise.domain.model.Category;
import com.anderson.wallet_wise.domain.model.Transaction;
import com.anderson.wallet_wise.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    boolean existsByOwnerAndValueAndTransactionDateAndCategoryAndDescription(
            User owner,
            BigDecimal value,
            LocalDateTime transactionDate,
            Category category,
            String description
    );

    Optional<Transaction> findByOwnerAndId(User owner, UUID id);
    List<Transaction> findAllByOwner(User owner);
}
