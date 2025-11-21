package com.anderson.wallet_wise.infra.database.repositories;

import com.anderson.wallet_wise.domain.model.Budget;
import com.anderson.wallet_wise.domain.model.Category;
import com.anderson.wallet_wise.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, UUID> {
    Optional<Budget> findByOwnerIdAndId(UUID owner, UUID id);
    Optional<Budget> findByOwnerIdAndCategoryId(UUID ownerId, Long categoryId);
}
