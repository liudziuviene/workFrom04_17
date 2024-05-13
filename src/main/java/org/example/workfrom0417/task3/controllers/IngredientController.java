package org.example.workfrom0417.task3.controllers;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.workfrom0417.task3.converters.IngredientConverter;
import org.example.workfrom0417.task3.dto.CreateIngredientDTO;
import org.example.workfrom0417.task3.dto.IngredientDTO;
import org.example.workfrom0417.task3.entities.Ingredient;
import org.example.workfrom0417.task3.entities.Recipe;
import org.example.workfrom0417.task3.services.IngredientService;
import org.example.workfrom0417.task3.services.RecipeService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/recipes")
public class IngredientController {
    private final IngredientService ingredientService;
    private final RecipeService recipeService;

    @GetMapping("/ingredients")
    public List<IngredientDTO> getAllIngredients() {
        List<Ingredient> ingredients = this.ingredientService.getAllIngredients();
        return IngredientConverter.convertEntityListToDTOList(ingredients);
    }

    @GetMapping("/ingredients/{ingredientId}")
    public IngredientDTO getIngredientById(@PathVariable Long ingredientId) {
        Ingredient ingredient = this.ingredientService.getIngredientById(ingredientId);
        return IngredientConverter.convertEntitytoDTO(ingredient);
    }

    @PostMapping("/{recipeId}/ingredients/existing-ingredients")
    public ResponseEntity<List<IngredientDTO>> addExistingIngredientsToRecipe(@PathVariable Long recipeId, @RequestBody List<Long> ingredientIds) {
        Recipe updatedRecipe = recipeService.addExistingIngredientsToRecipe(recipeId, ingredientIds);

        List<IngredientDTO> ingredientDTOList = IngredientConverter.convertEntityListToDTOList(updatedRecipe.getIngredients());

        return ResponseEntity.ok(ingredientDTOList);
    }

    @PostMapping("/{recipeId}/ingredients/new-ingredients")
    public ResponseEntity<List<IngredientDTO>> addNewIngredientsToRecipe(@PathVariable Long recipeId, @RequestBody List<CreateIngredientDTO> createIngredientDTOList) {
        recipeService.addNewIngredientsToRecipe(recipeId, createIngredientDTOList);
        Recipe updatedRecipe = recipeService.getRecipeById(recipeId);
        List<IngredientDTO> ingredientDTOList = IngredientConverter.convertEntityListToDTOList(updatedRecipe.getIngredients());
        return ResponseEntity.ok(ingredientDTOList);
    }

    @PostMapping("/{recipeId}/ingredients/new-ingredient")
    public ResponseEntity<List<IngredientDTO>> addNewIngredientToRecipe(@PathVariable Long recipeId, @RequestBody CreateIngredientDTO createIngredientDTO) {
        List<CreateIngredientDTO> createIngredientDTOList = new ArrayList<>();
        createIngredientDTOList.add(createIngredientDTO);
        recipeService.addNewIngredientsToRecipe(recipeId, createIngredientDTOList);
        Recipe updatedRecipe = recipeService.getRecipeById(recipeId);
        List<IngredientDTO> ingredientDTOList = IngredientConverter.convertEntityListToDTOList(updatedRecipe.getIngredients());
        return ResponseEntity.ok(ingredientDTOList);
    }

    //    @DeleteMapping("/ingredients/{ingredientId}")
//    public ResponseEntity<?> deleteIngredientById(@PathVariable Long ingredientId) {
//        try {
//            this.ingredientService.deleteIngredientById(ingredientId);
//            return ResponseEntity.ok().build(); // 200 OK
//        } catch (EntityNotFoundException e) {
//            //System.out.println("The ingredient with id " + ingredientId + " does not exist");
//            return ResponseEntity.notFound().build(); // 404 NOT FOUND
//        } catch (DataIntegrityViolationException e) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).body("Cannot delete ingredient because it is in use"); //409 Conflict
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()); // 400 Bad request
//        }
//    }
    @DeleteMapping("/{recipeId}/ingredients/{ingredientId}")
    public ResponseEntity<?> removeIngredientFromRecipe(@PathVariable Long recipeId, @PathVariable Long ingredientId) {
        try {
            ingredientService.removeIngredientFromRecipe(recipeId, ingredientId);
            System.out.println("Ingredient by ID: " + ingredientId + " is removed");
            return ResponseEntity.ok().build(); // Return 200 OK on successful deletion
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());  // Return 400 Bad Request on error
        }
    }
}

