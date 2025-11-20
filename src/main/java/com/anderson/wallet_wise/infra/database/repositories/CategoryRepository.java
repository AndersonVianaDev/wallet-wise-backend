package com.anderson.wallet_wise.infra.database.repositories;

import com.anderson.wallet_wise.domain.model.Category;
import com.anderson.wallet_wise.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByOwnerOrOwnerIsNullAndName(User owner, String name);
    Optional<Category> findByOwnerOrOwnerIsNullAndId(User owner, Long id);
    List<Category> findAllByOwnerOrOwnerIsNull(User owner);
}
