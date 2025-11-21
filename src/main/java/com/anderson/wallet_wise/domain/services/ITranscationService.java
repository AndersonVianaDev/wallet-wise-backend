package com.anderson.wallet_wise.domain.services;

import com.anderson.wallet_wise.domain.model.Transaction;
import com.anderson.wallet_wise.domain.model.User;

import java.util.List;
import java.util.UUID;

public interface ITranscationService {
    Transaction save(Transaction transaction);
    Transaction findByOwnerAndId(User owner, UUID id);
    List<Transaction> findAllByOwner(User owner);
    List<Transaction> findAllByOwnerAndCategory(User owner, Long categoryId);
}
