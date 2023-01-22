package com.abnamro.exam.recipe.service;

import com.abnamro.exam.recipe.entity.Recipe;
import com.abnamro.exam.recipe.model.RecipeRequest;
import com.abnamro.exam.recipe.model.RecipeResponse;
import com.abnamro.exam.recipe.model.RecipeSearchRequest;

import java.util.List;

/**
 * @author Vishal
 *
 */
public interface RecipeService {
    List<RecipeResponse> getAllRecipes();
    Integer createRecipe(RecipeRequest createRecipeRequest);
    Recipe getRecipeById(int id);
    void updateRecipe(RecipeRequest updateRecipeRequest);
    void deleteRecipe(int id);
    List<RecipeResponse> findFilteredRecipes(RecipeSearchRequest recipeSearchRequest);

}
