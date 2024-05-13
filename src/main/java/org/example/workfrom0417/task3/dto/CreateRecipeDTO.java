package org.example.workfrom0417.task3.dto;

import lombok.Data;

@Data
public class CreateRecipeDTO {
    private String title;
    private String difficulty;
    private String description;
    private int servings;
    private int cookingTime;
}
