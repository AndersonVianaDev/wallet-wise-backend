package com.anderson.wallet_wise.domain.services.impl;

import com.anderson.wallet_wise.domain.model.Budget;
import com.anderson.wallet_wise.domain.model.Category;
import com.anderson.wallet_wise.domain.model.User;
import com.anderson.wallet_wise.domain.services.IBudgetService;
import com.anderson.wallet_wise.domain.services.ICategoryService;
import com.anderson.wallet_wise.infra.database.repositories.BudgetRepository;
import com.anderson.wallet_wise.infra.database.repositories.TransactionRepository;
import com.anderson.wallet_wise.infra.exceptions.BudgetExceededException;
import com.anderson.wallet_wise.infra.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class BudgetServiceImpl implements IBudgetService {

    private final BudgetRepository repository;
    private final TransactionRepository transactionRepository;
    private final ICategoryService categoryService;

    @Override
    public Budget save(Budget budget) {
        final User owner = budget.getOwner();
        final Long categoryId = budget.getCategory().getId();

        final Category category = categoryService.findByOwnerOrOwnerIsNullAndId(owner, categoryId);

        budget.setCategory(category);
        budget.setActive(true);

        return repository.save(budget);
    }

    @Override
    public Budget findByOwnerAndId(User owner, UUID id) {
        return repository.findByOwnerIdAndId(owner.getId(), id)
                .orElseThrow(() -> new NotFoundException("Budget not found!"));
    }

    @Override
    public Budget findByOwnerAndCategory(User owner, Long categoryId) {
        return repository.findByOwnerIdAndCategoryId(owner.getId(), categoryId)
                .orElseThrow(() -> new NotFoundException("Budget not found!"));
    }

    @Override
    public void validateTransactionRespectsBudget(UUID ownerId, Long categoryId, BigDecimal valueTransaction) {
        final Budget budget = repository.findByOwnerIdAndCategoryId(ownerId, categoryId)
                .orElse(null);

        if (nonNull(budget)) {
            BigDecimal valueTotal = transactionRepository.sumByOwnerAndCategory(ownerId, categoryId);

            valueTotal = valueTotal.add(valueTransaction);

            if (valueTotal.compareTo(budget.getLimitAmount()) > 0) {
                throw new BudgetExceededException("Budget limit exceeded");
            }
        }
    }
}
