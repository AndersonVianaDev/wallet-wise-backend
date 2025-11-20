package com.anderson.wallet_wise.domain.services.impl;

import com.anderson.wallet_wise.domain.model.Category;
import com.anderson.wallet_wise.domain.model.User;
import com.anderson.wallet_wise.domain.services.ICategoryService;
import com.anderson.wallet_wise.infra.database.repositories.CategoryRepository;
import com.anderson.wallet_wise.infra.exceptions.NotFoundException;
import com.anderson.wallet_wise.infra.exceptions.ResourceAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {

    private final CategoryRepository repository;

    @Override
    public Category save(Category category) {
        if(repository.existsByOwnerOrOwnerIsNullAndName(category.getOwner(), category.getName())) {
            throw new ResourceAlreadyExistsException("Existing category");
        }

        return repository.save(category);
    }

    @Override
    public Category findByOwnerOrOwnerIsNullAndId(User owner, Long id) {
        return repository.findByOwnerOrOwnerIsNullAndId(owner, id)
                .orElseThrow(() -> new NotFoundException("Category not found!"));
    }

    @Override
    public List<Category> findAll(User owner) {
        return repository.findAllByOwnerOrOwnerIsNull(owner);
    }
}
