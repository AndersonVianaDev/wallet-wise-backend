package com.anderson.wallet_wise.domain.services;

import com.anderson.wallet_wise.domain.model.Budget;
import com.anderson.wallet_wise.domain.model.Category;
import com.anderson.wallet_wise.domain.model.User;

import java.util.UUID;

public interface IBudgetService {
    Budget save(Budget budget);
    Budget findByOwnerAndId(User owner, UUID id);
    Budget findByOwnerAndCategory(User owner, Long categoryId);
}
