package org.example.workfrom0417.task3.controllers;

import lombok.RequiredArgsConstructor;
import org.example.workfrom0417.task3.services.RecipeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/recipes")
public class RecipeController {
    private final RecipeService recipeService;
}
