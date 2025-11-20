package com.anderson.wallet_wise.domain.services;

import com.anderson.wallet_wise.domain.model.Category;
import com.anderson.wallet_wise.domain.model.User;

import java.util.List;

public interface ICategoryService {
    Category save(Category category);
    Category findByOwnerOrOwnerIsNullAndId(User owner, Long id);
    List<Category> findAll(User owner);
}
