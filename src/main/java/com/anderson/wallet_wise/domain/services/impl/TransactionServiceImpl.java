package com.anderson.wallet_wise.domain.services.impl;

import com.anderson.wallet_wise.domain.model.Category;
import com.anderson.wallet_wise.domain.model.Transaction;
import com.anderson.wallet_wise.domain.model.User;
import com.anderson.wallet_wise.domain.services.IBudgetService;
import com.anderson.wallet_wise.domain.services.ICategoryService;
import com.anderson.wallet_wise.domain.services.ITranscationService;
import com.anderson.wallet_wise.infra.database.repositories.TransactionRepository;
import com.anderson.wallet_wise.infra.exceptions.NotFoundException;
import com.anderson.wallet_wise.infra.exceptions.ResourceAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements ITranscationService {

    private final TransactionRepository repository;
    private final IBudgetService budgetService;
    private final ICategoryService categoryService;

    @Override
    public Transaction save(Transaction transaction) {
        final User owner = transaction.getOwner();
        final Long categoryId = transaction.getCategory().getId();

        boolean exists = repository.existsByOwnerIdAndValueAndTransactionDateAndCategoryIdAndDescription(
                owner.getId(),
                transaction.getValue(),
                transaction.getTransactionDate(),
                categoryId,
                transaction.getDescription()
        );

        if (exists) {
            throw new ResourceAlreadyExistsException("transaction already registered");
        }

        final Category category = categoryService.findByOwnerOrOwnerIsNullAndId(owner, categoryId);
        transaction.setCategory(category);

        budgetService.validateTransactionRespectsBudget(owner.getId(), categoryId, transaction.getValue());

        return repository.save(transaction);
    }

    @Override
    public Transaction findByOwnerAndId(User owner, UUID id) {
        return repository.findByOwnerIdAndId(owner.getId(), id)
                .orElseThrow(() -> new NotFoundException("Category not found!"));
    }

    @Override
    public List<Transaction> findAllByOwner(User owner) {
        return repository.findAllByOwnerId(owner.getId());
    }

    @Override
    public List<Transaction> findAllByOwnerAndCategory(User owner, Long categoryId) {
        return repository.findAllByOwnerIdAndCategoryId(owner.getId(), categoryId);
    }
}
