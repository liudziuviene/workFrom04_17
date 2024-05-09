package org.example.workfrom0417.task3.converters;

import org.example.workfrom0417.task3.dto.CreateRecipeDTO;
import org.example.workfrom0417.task3.dto.RecipeDTO;
import org.example.workfrom0417.task3.entities.Recipe;

import java.util.ArrayList;

public class RecipeConverter {

    public static RecipeDTO convertEntityToDTO(Recipe recipe) {
        RecipeDTO recipeDTO = null;
        if (recipe != null) {
            recipeDTO = new RecipeDTO();
            recipeDTO.setId(recipe.getId());
            recipeDTO.setTitle(recipe.getTitle());
            recipeDTO.setDifficulty(recipe.getDifficulty());
        }
        return recipeDTO;
    }

    public static Recipe convertCreateRecipeDTOToEntity(CreateRecipeDTO createRecipeDTO) {
        Recipe recipe = null;
        if (createRecipeDTO != null) {
            recipe = new Recipe();
            recipe.setTitle(createRecipeDTO.getTitle());
            recipe.setDifficulty(createRecipeDTO.getDifficulty());
            recipe.setDescription(createRecipeDTO.getDescription());
            recipe.setServings(createRecipeDTO.getServings());
            recipe.setCookingTime(createRecipeDTO.getCookingTime());
            recipe.setIngredients(new ArrayList<>());
        }
        return recipe;
    }
}
