package org.example.workfrom0417.task3.repositories;

import org.example.workfrom0417.task3.entities.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, Long> {

    boolean existsByIngredientId(Long ingredientId);
}
