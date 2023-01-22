package com.abnamro.exam.recipe.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Pageable;

import com.abnamro.exam.recipe.entity.Recipe;
import com.abnamro.exam.recipe.exception.DataNotFoundException;
import com.abnamro.exam.recipe.model.RecipeRequest;
import com.abnamro.exam.recipe.model.RecipeSearchRequest;
import com.abnamro.exam.recipe.repository.RecipeRepository;
import com.abnamro.exam.recipe.search.RecipeSpecificationBuilder;
import com.abnamro.exam.recipe.util.CustomMessagesUtil;


@RunWith(MockitoJUnitRunner.class)
public class RecipeServiceTest {
    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private CustomMessagesUtil customMessagesUtil;

    @InjectMocks
    private RecipeService recipeService;

    @Test
    public void testCreateRecipe() {
        RecipeRequest request = new RecipeRequest("pesto pasta", "OTHER", 4, null, "instructions");


        Recipe response = new Recipe();
        response.setName("pesto pasta");
        response.setInstructions("instructions");
        response.setNumberOfServings(4);

        when(recipeRepository.save(any(Recipe.class))).thenReturn(response);

        Integer id = recipeService.createRecipe(request);

        assertThat(id).isSameAs(response.getId());
    }

    @Test
    public void testUpdateRecipe() {
        Recipe recipeResponse = new Recipe();
        recipeResponse.setName("pesto pasta");
        recipeResponse.setType("OTHER");
        recipeResponse.setNumberOfServings(4);
        recipeResponse.setId(1);

        RecipeRequest request = new RecipeRequest(1, "pasta", "OTHER", 4, null, "instructions");

        when(recipeRepository.save(any(Recipe.class))).thenReturn(recipeResponse);
        when(recipeRepository.findById(anyInt())).thenReturn(Optional.of(recipeResponse));

        recipeService.updateRecipe(request);
    }

    @Test(expected = DataNotFoundException.class)
    public void testUpdateRecipeDataNotFound() {
        RecipeRequest request = new RecipeRequest(1, "pesto pasta", "OTHER", 4, null, "instructions");

        when(recipeRepository.findById(anyInt())).thenReturn(Optional.empty());

        recipeService.updateRecipe(request);
    }

    @Test
    public void testDeleteRecipe() {
        when(recipeRepository.existsById(anyInt())).thenReturn(true);
        doNothing().when(recipeRepository).deleteById(anyInt());

        recipeService.deleteRecipe(1);
    }

    @Test(expected = DataNotFoundException.class)
    public void testDeleteRecipeDataNotFound() {
        when(recipeRepository.existsById(anyInt())).thenReturn(false);

        recipeService.deleteRecipe(1);
    }

    @Test(expected = DataNotFoundException.class)
    public void testSearchRecipeWithFilterDataNotFound() {
        RecipeSearchRequest recipeSearchRequest = mock(RecipeSearchRequest.class);
        RecipeSpecificationBuilder builder = mock(RecipeSpecificationBuilder.class);
        recipeService.findFilteredRecipes(recipeSearchRequest);
    }

}