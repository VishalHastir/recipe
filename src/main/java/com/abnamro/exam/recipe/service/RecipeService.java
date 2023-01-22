/**
 * 
 */
package com.abnamro.exam.recipe.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.abnamro.exam.recipe.entity.Recipe;
import com.abnamro.exam.recipe.exception.DataNotFoundException;
import com.abnamro.exam.recipe.model.RecipeRequest;
import com.abnamro.exam.recipe.model.RecipeResponse;
import com.abnamro.exam.recipe.model.RecipeSearchRequest;
import com.abnamro.exam.recipe.model.SearchConditionsRequest;
import com.abnamro.exam.recipe.repository.RecipeRepository;
import com.abnamro.exam.recipe.search.RecipeSpecificationBuilder;
import com.abnamro.exam.recipe.search.SearchConditions;
import com.abnamro.exam.recipe.util.CustomMessagesUtil;

/**
 * @author Vishal
 *
 */
@Service
public class RecipeService {

	@Autowired
	private RecipeRepository recipeRepository;

	@Autowired
	private CustomMessagesUtil customMessagesUtil;

	/**
	 * @return
	 */
	public List<RecipeResponse> getAllRecipes() {

		List<RecipeResponse> allRecipes = recipeRepository.findAll().stream().map(RecipeResponse::new)
				.collect(Collectors.toList());
		if (CollectionUtils.isEmpty(allRecipes))
			throw new DataNotFoundException(customMessagesUtil.getMessage("recipe.notFound"));

		return allRecipes;

	}

	/**
	 * @param createRecipeRequest
	 * @return
	 */
	public Integer createRecipe(RecipeRequest createRecipeRequest) {

		Recipe recipeObj = new Recipe();
		recipeObj.setName(createRecipeRequest.getName());
		recipeObj.setInstructions(createRecipeRequest.getInstructions());
		recipeObj.setType(createRecipeRequest.getType());
		recipeObj.setNumberOfServings(createRecipeRequest.getNumberOfServings());
		recipeObj.setIngredients(createRecipeRequest.getIngredients());
		return recipeRepository.save(recipeObj).getId();

	}

	/**
	 * @param id
	 * @return
	 */
	public Recipe getRecipeById(int id) {
		return recipeRepository.findById(id)
				.orElseThrow(() -> new DataNotFoundException(customMessagesUtil.getMessage("recipe.notFound")));
	}

	/**
	 * @param updateRecipeRequest
	 */
	public void updateRecipe(RecipeRequest updateRecipeRequest) {
		Recipe recipe = recipeRepository.findById(updateRecipeRequest.getId())
				.orElseThrow(() -> new DataNotFoundException(customMessagesUtil.getMessage("recipe.notFound")));

		recipe.setName(updateRecipeRequest.getName());
		recipe.setType(updateRecipeRequest.getType());
		recipe.setNumberOfServings(updateRecipeRequest.getNumberOfServings());
		recipe.setInstructions(updateRecipeRequest.getInstructions());
		recipe.setIngredients(updateRecipeRequest.getIngredients());

		recipeRepository.save(recipe);

	}

	/**
	 * @param id
	 */
	public void deleteRecipe(int id) {
		if (!recipeRepository.existsById(id)) {
			throw new DataNotFoundException(customMessagesUtil.getMessage("recipe.notFound"));
		}

		recipeRepository.deleteById(id);
	}

	/**
	 * @param recipeSearchRequest
	 * @return
	 */
	public List<RecipeResponse> findFilteredRecipes(RecipeSearchRequest recipeSearchRequest) {
		List<SearchConditions> searchConditionsList = new ArrayList<>();
		RecipeSpecificationBuilder builder = new RecipeSpecificationBuilder(searchConditionsList);

		Specification<Recipe> recipeSpecification = createRecipeFilter(recipeSearchRequest, builder);
		List<Recipe> filteredRecipesList = recipeRepository.findAll(recipeSpecification);
		
		if(CollectionUtils.isEmpty(filteredRecipesList))
			throw new DataNotFoundException(customMessagesUtil.getMessage("recipe.notFound"));

		return filteredRecipesList.stream().map(RecipeResponse::new).collect(Collectors.toList());
	}

	/**
	 * @param recipeSearchRequest
	 * @param builder
	 * @return
	 */
	private Specification<Recipe> createRecipeFilter(RecipeSearchRequest recipeSearchRequest,
			RecipeSpecificationBuilder builder) {
		List<SearchConditionsRequest> searchConditionsRequestsList = recipeSearchRequest.getSearchConditionsRequests();

		if (Optional.ofNullable(searchConditionsRequestsList).isPresent()) {
			List<SearchConditions> searchConditionsList = searchConditionsRequestsList.stream()
					.map(SearchConditions::new).collect(Collectors.toList());

			if (!searchConditionsList.isEmpty())
				searchConditionsList.forEach(criteria -> {
					criteria.setSearchConditionsType(recipeSearchRequest.getSearchConditionsType());
					builder.addCriteria(criteria);
				});
		}

		return builder.build()
				.orElseThrow(() -> new DataNotFoundException(customMessagesUtil.getMessage("searchConditions.notFound")));
	}

}
