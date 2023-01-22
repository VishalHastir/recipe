package com.abnamro.exam.recipe.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.abnamro.exam.recipe.entity.Recipe;
import com.abnamro.exam.recipe.model.RecipeRequest;
import com.abnamro.exam.recipe.model.RecipeResponse;
import com.abnamro.exam.recipe.model.RecipeSearchRequest;
import com.abnamro.exam.recipe.service.RecipeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Vishal
 *
 */
@Api(value = "RecipeController", tags = "Recipe Controller", description = "Create, Update, Delete, Get Recipes")
@RestController
@RequestMapping(value = "/recipe")
public class RecipeController {

	private final Logger logger = LoggerFactory.getLogger(RecipeController.class);

	@Autowired
	private RecipeService recipeService;

	/**
	 * @return
	 */
	@ApiOperation(value = "Get all recipes")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Recipes not found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@GetMapping("/allRecipes")
	@ResponseStatus(HttpStatus.OK)
	public List<RecipeResponse> getAllRecipes() {
		logger.info("Fetching all recipes");
		return recipeService.getAllRecipes();
	}
	

	/**
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "Get recipe by ID")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Recipe not found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@GetMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public RecipeResponse getRecipeById(
			@ApiParam(value = "Recipe ID", required = true) @PathVariable(name = "id") Integer id) {
		logger.info("Fetching  recipe by id. Id: {}", id);
		Recipe recipe = recipeService.getRecipeById(id);
		return new RecipeResponse(recipe);
		
	}

	/**
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "Create a recipe")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Recipe created"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@PostMapping("/createRecipe")
	@ResponseStatus(HttpStatus.CREATED)
	public RecipeResponse createRecipe(
			@ApiParam(value = "Recipe request", required = true) @Valid @RequestBody RecipeRequest request) {
		logger.info("Creating recipe ");
		Integer id = recipeService.createRecipe(request);
		return new RecipeResponse(id);
		

	}

	/**
	 * @param updateRecipeRequest
	 */
	@ApiOperation(value = "Update the recipe")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 404, message = "Recipe not found"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@PutMapping("/updateRecipe")
	@ResponseStatus(HttpStatus.OK)
	public void updateRecipe(
			@ApiParam(value = "Recipe Request", required = true) @Valid @RequestBody RecipeRequest updateRecipeRequest) {
		logger.info("Updating the recipe");
		recipeService.updateRecipe(updateRecipeRequest);
		
	}

	/**
	 * @param id
	 */
	@ApiOperation(value = "Delete the recipe")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 404, message = "Recipe not found"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@DeleteMapping("/deleteRecipe")
	@ResponseStatus(HttpStatus.OK)
	public void deleteRecipe(@ApiParam(value = "Recipe ID", required = true) @RequestParam(name = "id") Integer id) {
		logger.info("Deleting the recipe by  id. Id: {}", id);
		recipeService.deleteRecipe(id);
	}

	/**
	 * @param recipeSearchRequest
	 * @return
	 */
	@ApiOperation(value = "Search recipes with given search filters")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 404, message = "Search Conditions or Recipe not found"),
			@ApiResponse(code = 500, message = "Internal Server Error")

	})
	@PostMapping("/search")
	@ResponseStatus(HttpStatus.OK)
	public List<RecipeResponse> searchRecipe(
			@ApiParam(value = "Search Filter Request") @RequestBody @Valid RecipeSearchRequest recipeSearchRequest) {
		logger.info("Fetching the recipe by given conditions");
		return recipeService.findFilteredRecipes(recipeSearchRequest);
		
	}

}
