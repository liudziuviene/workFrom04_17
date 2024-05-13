package org.example.workfrom0417.task3.entities;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class RecipeIngredientId implements Serializable {

    private Long recipeId;
    private Long ingredientId;

}
