package org.example.workfrom0417.task3.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@IdClass(RecipeIngredientId.class)
@Table(name = "recipe_ingredient")
public class RecipeIngredient {

    @Id
    @Column(name = "recipe_id")
    private Long recipeId;

    @Id
    @Column(name = "ingredient_id")
    private Long ingredientId;

}
