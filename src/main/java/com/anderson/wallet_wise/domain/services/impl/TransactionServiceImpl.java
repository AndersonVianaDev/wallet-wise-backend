package com.anderson.wallet_wise.domain.services.impl;

import com.anderson.wallet_wise.domain.model.Category;
import com.anderson.wallet_wise.domain.model.Transaction;
import com.anderson.wallet_wise.domain.model.User;
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
    private final ICategoryService categoryService;

    @Override
    public Transaction save(Transaction transaction) {
        final User user = transaction.getOwner();

        boolean exists = repository.existsByOwnerAndValueAndTransactionDateAndCategoryAndDescription(
                user,
                transaction.getValue(),
                transaction.getTransactionDate(),
                transaction.getCategory(),
                transaction.getDescription()
        );

        if (exists) {
            throw new ResourceAlreadyExistsException("transaction already registered");
        }

        final Long categoryId = transaction.getCategory().getId();
        final Category category = categoryService.findByOwnerOrOwnerIsNullAndId(user, categoryId);
        transaction.setCategory(category);

        return repository.save(transaction);
    }

    @Override
    public Transaction findByOwnerAndId(User owner, UUID id) {
        return repository.findByOwnerAndId(owner, id)
                .orElseThrow(() -> new NotFoundException("Category not found!"));
    }

    @Override
    public List<Transaction> findAllByOwner(User owner) {
        return repository.findAllByOwner(owner);
    }
}
