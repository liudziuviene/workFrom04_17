package org.example.workfrom0417.task3.services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.workfrom0417.task3.entities.Ingredient;
import org.example.workfrom0417.task3.entities.Recipe;
import org.example.workfrom0417.task3.repositories.IngredientRepository;

import org.example.workfrom0417.task3.repositories.RecipeIngredientRepository;
import org.example.workfrom0417.task3.repositories.RecipeRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class IngredientService {
    private final IngredientRepository ingredientRepository;
    private final RecipeIngredientRepository recipeIngredientRepository;
    private final RecipeRepository recipeRepository;

//    public List<Ingredient> getAllIngredients() {
//        return this.ingredientRepository.findAll();
//    }
//
//    public Ingredient getIngredientById(Long id) {
//        return this.ingredientRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Ingredient not found with ID:" + id));
//    }
//
//    @Transactional
//    public void deleteIngredientById(Long id) {
//        if (!this.ingredientRepository.existsById(id)) {
//            throw new EntityNotFoundException("Ingredient not found with ID:" + id);
//        }
//        if (checkIngredientInUse(id)) {
//            throw new DataIntegrityViolationException("Cannot delete ingredient because it is in use");
//        }
//        this.ingredientRepository.deleteById(id);
//    }
//
//    private boolean checkIngredientInUse(Long id) {
//        return recipeIngredientRepository.existsByIngredientId(id);
//    }
//
//    @Transactional
//    public void safelyDeleteIngredient(Long ingredientId) {
//        // Check if the ingredient is in use
//        if (checkIngredientInUse(ingredientId)) {
//            // Optional: Find and update recipes that use this ingredient
//            List<Recipe> recipes = findRecipesByIngredientId(ingredientId);
//            for (Recipe recipe : recipes) {
//                removeIngredientFromRecipe(recipe, ingredientId);
//            }
//        }
//
//        // Try to delete the ingredient again after removing from recipes
//        ingredientRepository.deleteById(ingredientId);
//    }
//
//    private List<Recipe> findRecipesByIngredientId(Long ingredientId) {
//        // Implement logic to fetch recipes that include the given ingredient
//        return new ArrayList<>();  // Placeholder
//    }
//
//    private void removeIngredientFromRecipe(Recipe recipe, Long ingredientId) {
//        // Implement logic to remove the ingredient from the recipe
//    }

    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    // Retrieves a single ingredient by its ID
    public Ingredient getIngredientById(Long id) throws EntityNotFoundException {
        return ingredientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ingredient not found with ID: " + id));
    }

    // Deletes an ingredient by its ID, handling it transactionally
    @Transactional
    public void deleteIngredientById(Long id) throws EntityNotFoundException, DataIntegrityViolationException {
        if (!ingredientRepository.existsById(id)) {
            throw new EntityNotFoundException("Ingredient not found with ID: " + id);
        }
        if (checkIngredientInUse(id)) {
            throw new DataIntegrityViolationException("Cannot delete ingredient because it is in use");
        }
        ingredientRepository.deleteById(id);
    }

    // Checks if the ingredient is currently in use in any recipe
    private boolean checkIngredientInUse(Long id) {
        return recipeIngredientRepository.existsByIngredientId(id);
    }

    @Transactional
    public void removeIngredientFromRecipe(Long recipeId, Long ingredientId) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new IllegalStateException("Recipe not found with ID: " + recipeId));

        boolean removed = recipe.getIngredients().removeIf(ingredient -> ingredient.getId().equals(ingredientId));
        if (!removed) {
            throw new IllegalStateException("Ingredient not found in recipe or already removed");
        }

        recipeRepository.save(recipe);
    }
}

