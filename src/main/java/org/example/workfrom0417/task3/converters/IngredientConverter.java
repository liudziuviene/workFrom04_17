package org.example.workfrom0417.task3.converters;

import org.example.workfrom0417.task3.dto.CreateIngredientDTO;
import org.example.workfrom0417.task3.dto.IngredientDTO;
import org.example.workfrom0417.task3.entities.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class IngredientConverter {

    public static IngredientDTO convertEntitytoDTO(Ingredient ingredient) {

        IngredientDTO ingredientDTO = null;
        if (ingredient != null) {
            ingredientDTO = new IngredientDTO();
            ingredientDTO.setId(ingredient.getId());
            ingredientDTO.setName(ingredient.getName());
            ingredientDTO.setCategory(ingredient.getCategory());
            ingredientDTO.setCalorificValue(ingredient.getCalorificValue());
        }
        return ingredientDTO;
    }

    public static Ingredient convertCreateIngredientDTOtoEntity(CreateIngredientDTO createIngredientDTO) {
        Ingredient ingredient = null;
        if (createIngredientDTO != null) {
            ingredient = new Ingredient();
            ingredient.setName(createIngredientDTO.getName());
            ingredient.setCategory(createIngredientDTO.getCategory());
            ingredient.setCalorificValue(createIngredientDTO.getCalorificValue());
        }
        return ingredient;
    }

    public static List<IngredientDTO> convertEntityListToDTOList(List<Ingredient> ingredients) {

        List<IngredientDTO> ingredientDTOList = null;
        if (ingredients != null && !ingredients.isEmpty()) {
            ingredientDTOList = new ArrayList<>();
            for (Ingredient ingredient : ingredients) {
                ingredientDTOList.add(convertEntitytoDTO(ingredient));
            }
        }
        return ingredientDTOList;
    }
}
