package com.anderson.wallet_wise.infra.database.repositories;

import com.anderson.wallet_wise.domain.model.Category;
import com.anderson.wallet_wise.domain.model.Transaction;
import com.anderson.wallet_wise.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    boolean existsByOwnerIdAndValueAndTransactionDateAndCategoryIdAndDescription(
            UUID ownerId,
            BigDecimal value,
            LocalDateTime transactionDate,
            Long categoryId,
            String description
    );

    Optional<Transaction> findByOwnerIdAndId(UUID ownerId, UUID id);
    List<Transaction> findAllByOwnerId(UUID ownerId);
    List<Transaction> findAllByOwnerIdAndCategoryId(UUID ownerId, Long categoryId);

    @Query("SELECT COALESCE(SUM(t.value), 0) FROM" +
            " Transaction t WHERE t.owner.id = :ownerId" +
            " AND t.category.id = :categoryId")
    BigDecimal sumByOwnerAndCategory(@Param("ownerId") UUID ownerId, @Param("categoryId") Long categoryId);
}
