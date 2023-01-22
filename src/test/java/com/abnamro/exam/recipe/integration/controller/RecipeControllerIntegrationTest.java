package com.abnamro.exam.recipe.integration.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.abnamro.exam.recipe.entity.Recipe;
import com.abnamro.exam.recipe.model.RecipeRequest;
import com.abnamro.exam.recipe.model.RecipeResponse;
import com.abnamro.exam.recipe.model.RecipeSearchRequest;
import com.abnamro.exam.recipe.model.SearchConditionsRequest;
import com.abnamro.exam.recipe.repository.RecipeRepository;
import com.abnamro.exam.recipe.utils.RecipeTestData;

public class RecipeControllerIntegrationTest extends AbstractControllerIntegrationTest {
	
    @Autowired
    private RecipeRepository recipeRepository;

    @Before
    public void before() {
        recipeRepository.deleteAll();
    }

    @Test
    public void testCreateRecipe() throws Exception {
        RecipeRequest request = new RecipeRequest("pesto pasta",
                "OTHER", 5, null, "Instruction");

        MvcResult result = performPost("/recipe/createRecipe", request)
                .andExpect(status().isCreated())
                .andReturn();

        Integer id = readByJsonPath(result, "$.id");
        Optional<Recipe> optionalRecipe = recipeRepository.findById(id);
        assertTrue(optionalRecipe.isPresent());
        assertEquals(optionalRecipe.get().getName(), request.getName());
    }

    @Test
    public void testGetRecipe() throws Exception {
        Recipe Recipe = RecipeTestData.createRecipe();
        Recipe savedRecipe = recipeRepository.save(Recipe);

        performGet("/recipe/" + savedRecipe.getId())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(savedRecipe.getId()))
                .andExpect(jsonPath("$.name").value(savedRecipe.getName()))
                .andExpect(jsonPath("$.instructions").value(savedRecipe.getInstructions()))
                .andExpect(jsonPath("$.numberOfServings").value(savedRecipe.getNumberOfServings()));
    }

    @Test
    public void testGetRecipeNotFound() throws Exception {

        performGet("/recipe/1")
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    public void testGetAllRecipes() throws Exception {
        Recipe recipe1 = new Recipe();
        recipe1.setId(5);
        recipe1.setName("pesto pasta");
        recipe1.setInstructions("Instructions");
        recipe1.setType("OTHER");

        Recipe recipe2 = new Recipe();
        recipe2.setId(6);
        recipe2.setName("pesto pasta chicken");
        recipe2.setInstructions("Instruction");
        recipe2.setType("OTHER");

        List<Recipe> storedRecipeList = new ArrayList<>();
        storedRecipeList.add(recipe1);
        storedRecipeList.add(recipe2);

        recipeRepository.saveAll(storedRecipeList);

        MvcResult result = performGet("/recipe/allRecipes")
                .andExpect(status().isOk())
                .andReturn();

        List<RecipeResponse> RecipeList = getListFromMvcResult(result, RecipeResponse.class);

        assertEquals(storedRecipeList.size(), RecipeList.size());
        assertEquals(storedRecipeList.get(0).getName(), RecipeList.get(0).getName());
        assertEquals(storedRecipeList.get(1).getName(), RecipeList.get(1).getName());
    }

    @Test
    public void testUpdateRecipe() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setName("pesto pasta");
        recipe.setType("OTHER");
        recipe.setInstructions("add pesto");
        recipe.setNumberOfServings(2);

        Recipe savedRecipe = recipeRepository.save(recipe);

        savedRecipe.setName("pesto pasta chicken");
        savedRecipe.setInstructions("add chiken add pesto");

        performPut("/recipe/updateRecipe", savedRecipe)
                .andExpect(status().isOk());

        Optional<Recipe> updatedRecipe = recipeRepository.findById(savedRecipe.getId());

        assertTrue(updatedRecipe.isPresent());
        assertEquals(savedRecipe.getName(), updatedRecipe.get().getName());
        assertEquals(savedRecipe.getNumberOfServings(), updatedRecipe.get().getNumberOfServings());
        assertEquals(savedRecipe.getInstructions(), updatedRecipe.get().getInstructions());
    }

    @Test
    public void testUpdateRecipeWhenIdIsNull() throws Exception {
        Recipe testRecipe = new Recipe();
        testRecipe.setName("pesto pasta");
        testRecipe.setInstructions("add pesto");
        testRecipe.setNumberOfServings(2);
        testRecipe.setType("OTHER");

        performPut("/recipe/updateRecipe", testRecipe)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testUpdateRecipeNotFound() throws Exception {
        Recipe testRecipe = RecipeTestData.createRecipe(1);

        performPut("/recipe/updateRecipe", testRecipe)
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    public void testDeleteRecipe() throws Exception {
        Recipe testRecipe = RecipeTestData.createRecipe();
        Recipe savedRecipe = recipeRepository.save(testRecipe);

        performDelete("/recipe/deleteRecipe", Pair.of("id", String.valueOf(savedRecipe.getId())))
                .andExpect(status().isOk());

        Optional<Recipe> deletedRecipe = recipeRepository.findById(savedRecipe.getId());

        assertTrue(!deletedRecipe.isPresent());
    }

    @Test
    public void testDeleteRecipeNotFound() throws Exception {
        performDelete("/recipe/deleteRecipe", Pair.of("id", "1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    public void testSearchRecipeWithFilter() throws Exception {
       

        RecipeRequest createRecipeRequest = new RecipeRequest("pesto pasta",
                "OTHER", 5, "chicken,pesto", "Instruction");

        MvcResult createdRecipe = performPost("/recipe/createRecipe", createRecipeRequest)
                .andExpect(status().isCreated())
                .andReturn();

        Integer id = readByJsonPath(createdRecipe, "$.id");

        RecipeSearchRequest request = new RecipeSearchRequest();
        List<SearchConditionsRequest> searchCriteriaList = new ArrayList<>();
        SearchConditionsRequest searchCriteria = new SearchConditionsRequest("name",
                "pasta",
                "IN");

        searchCriteriaList.add(searchCriteria);

        request.setSearchConditionsType("ALL");
        request.setSearchConditionsRequests(searchCriteriaList);

        MvcResult result = performPost("/recipe/search", request)
                .andExpect(status().isOk())
                .andReturn();

        Optional<Recipe> optionalRecipe = recipeRepository.findById(id);


        List<RecipeResponse> listRecipeList = getListFromMvcResult(result, RecipeResponse.class);
        assertEquals(listRecipeList.size(), listRecipeList.size());
        Assert.assertTrue(optionalRecipe.isPresent());
        Assert.assertEquals(listRecipeList.get(0).getName(), optionalRecipe.get().getName());
        Assert.assertEquals(listRecipeList.get(0).getInstructions(), optionalRecipe.get().getInstructions());
        Assert.assertEquals(listRecipeList.get(0).getNumberOfServings(), optionalRecipe.get().getNumberOfServings());
    }

    @Test
    public void testSearchRecipeWithFilterFailure() throws Exception {
        performPost("/recipe/search", null)
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.message").exists())
                .andReturn();
    }

}
