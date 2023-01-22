package com.abnamro.exam.recipe.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.abnamro.exam.recipe.entity.Recipe;
import com.abnamro.exam.recipe.model.RecipeRequest;
import com.abnamro.exam.recipe.model.RecipeResponse;
import com.abnamro.exam.recipe.service.RecipeService;


@RunWith(MockitoJUnitRunner.class)
public class RecipeControllerTest {
    @Mock
    private RecipeService recipeService;

    @InjectMocks
    private RecipeController recipeController;

    @Test
    public void testCreateRecipe() {
        RecipeRequest request = new RecipeRequest("pesto pasta", "OTHER", 4, null, "instructions");

        when(recipeService.createRecipe(any(RecipeRequest.class))).thenReturn(1);

        RecipeResponse response = recipeController.createRecipe(request);

        assertThat(response).isNotNull();
        assertThat(response.getId()).isSameAs(1);
    }

    @Test
    public void testGetRecipeById() {
        Recipe Recipe = new Recipe();
        Recipe.setId(1);
        Recipe.setName("pesto pasta");

        when(recipeService.getRecipeById(anyInt())).thenReturn(Recipe);

        RecipeResponse response = recipeController.getRecipeById(1);

        assertThat(response.getId()).isSameAs(Recipe.getId());
        assertThat(response.getName()).isSameAs(Recipe.getName());
    }

    @Test
    public void testGetAllRecipes() {
    	RecipeResponse recipe = new RecipeResponse();
        recipe.setId(5);
        recipe.setName("pesto pasta");

        RecipeResponse recipe1 = new RecipeResponse();
        recipe1.setId(6);
        recipe1.setName("pesto pasta chicken");

        List<RecipeResponse> storedRecipeList = new ArrayList<>();
        storedRecipeList.add(recipe);
        storedRecipeList.add(recipe1);

        when(recipeService.getAllRecipes()).thenReturn(storedRecipeList);

        List<RecipeResponse> recipeList = recipeController.getAllRecipes();

        assertThat(storedRecipeList.size()).isSameAs(recipeList.size());
        assertThat(storedRecipeList.get(0).getId()).isSameAs(recipeList.get(0).getId());
        assertThat(storedRecipeList.get(1).getId()).isSameAs(recipeList.get(1).getId());
    }

    @Test
    public void testUpdateRecipe() {
        Recipe recipe = new Recipe();
        recipe.setName("Pesto Pasta");
        recipe.setType("OTHER");
        recipe.setInstructions("instructions");

        doNothing().when(recipeService).updateRecipe(any());
        recipe.setName("Pesto Pasta chicken");

        RecipeRequest request = new RecipeRequest(1, "pasta", "OTHER", 4, null, "instructions");

        recipeController.updateRecipe(request);
    }

    @Test
    public void testDeleteRecipe() {
        doNothing().when(recipeService).deleteRecipe(anyInt());
        recipeController.deleteRecipe(5);
    }
}