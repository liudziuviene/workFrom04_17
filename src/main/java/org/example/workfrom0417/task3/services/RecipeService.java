package org.example.workfrom0417.task3.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.workfrom0417.task3.dto.CreateIngredientDTO;
import org.example.workfrom0417.task3.entities.Ingredient;
import org.example.workfrom0417.task3.entities.Recipe;
import org.example.workfrom0417.task3.repositories.IngredientRepository;
import org.example.workfrom0417.task3.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RecipeService {

    @Autowired
    private final RecipeRepository recipeRepository;

    @Autowired
    private final IngredientRepository ingredientRepository;

    public Recipe addExistingIngredientsToRecipe(Long recipeId, List<Long> ingredientsIds) {

        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new EntityNotFoundException("Recipe not found"));

        List<Ingredient> ingredientsToAdd = ingredientRepository.findAllById(ingredientsIds);
        recipe.getIngredients().addAll(ingredientsToAdd);

        for (Ingredient ingredient : ingredientsToAdd) {
            ingredient.getRecipes().add(recipe);
        }
        return recipeRepository.save(recipe);
    }

    public Recipe addNewIngredientsToRecipe(Long recipeId, List<CreateIngredientDTO> createIngredientDTOList) {

        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new EntityNotFoundException("Recipe not found"));

        List<Ingredient> ingredientList = new ArrayList<>();

        for (CreateIngredientDTO dto : createIngredientDTOList) {
            Ingredient ingredient = new Ingredient();
            ingredient.setName(dto.getName());
            ingredient.setCategory(dto.getCategory());
            ingredient.setCalorificValue(dto.getCalorificValue());
            ingredientList.add(ingredient);
        }

        ingredientList = ingredientRepository.saveAll(ingredientList);
        recipe.getIngredients().addAll(ingredientList);
        return recipeRepository.save(recipe);
    }

    public Recipe getRecipeById(Long recipeId) {
        return this.recipeRepository.findById(recipeId).orElseThrow(() -> new EntityNotFoundException("Recipe not found"));
    }
}
