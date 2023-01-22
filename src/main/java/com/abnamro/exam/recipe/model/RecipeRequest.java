package com.abnamro.exam.recipe.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.abnamro.exam.recipe.config.EnumValidator;
import com.abnamro.exam.recipe.constants.CommonConstants;
import com.abnamro.exam.recipe.enums.RecipeType;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author Vishal
 *
 */
public class RecipeRequest {

	@Positive(message = "{recipe.positive}")
	@ApiModelProperty(notes = "Recipe Id", example = "1")
	private Integer id;

	@NotBlank(message = "{recipeName.notBlank}")
	@Size(max = CommonConstants.RECIPE_NAME_MAX_LENGTH, message = "{recipeName.size}")
	@Pattern(regexp = CommonConstants.RECIPE_NAME_PATTERN, message = "{recipeName.pattern}")
	@ApiModelProperty(notes = "Recipe Name", example = "Pesto Pasta")
	private String name;

	@ApiModelProperty(notes = "Recipe Type", example = "VEGETARIAN")
	@EnumValidator(enumClass = RecipeType.class, message = "{recipeType.invalid}")
	private String type;

	@NotNull(message = "{numberOfServings.notNull}")
	@Positive(message = "{numberOfServings.positive}")
	@ApiModelProperty(notes = "Number of servings per recipe", example = "2")
	private int numberOfServings;
	
	@ApiModelProperty(notes = "Recipe Ingredients comma sepreated", example = "pasta,pesto")
	private String ingredients;

	@NotBlank(message = "{instructions.notBlank}")
	@Size(max = CommonConstants.DEFAULT_MAX_LENGTH, message = "{instructions.size}")
	@Pattern(regexp = CommonConstants.INSTRUCTIONS_FREE_TEXT_PATTERN, message = "{instructions.pattern}")
	@ApiModelProperty(notes = "Instructions to create the recipe", example = "Add pesto after cooking pasta")
	private String instructions;

	public RecipeRequest() {
	}

	

	
	/**
	 * @param name
	 * @param type
	 * @param numberOfServings
	 * @param ingredients
	 * @param instructions
	 */
	public RecipeRequest(
			@NotBlank(message = "{recipeName.notBlank}") @Size(max = 100, message = "{recipeName.size}") @Pattern(regexp = "^(?:\\p{L}\\p{M}*|[',. \\-]|\\s)*$", message = "{recipeName.pattern}") String name,
			@EnumValidator(enumClass = RecipeType.class, message = "{recipeType.invalid}") String type,
			@NotNull(message = "{numberOfServings.notNull}") @Positive(message = "{numberOfServings.positive}") int numberOfServings,
			String ingredients,
			@NotBlank(message = "{instructions.notBlank}") @Size(max = 255, message = "{instructions.size}") @Pattern(regexp = "^(?:\\p{L}\\p{M}*|[0-9]*|[\\/\\-+.,?!*();\"]|\\s)*$", message = "{instructions.pattern}") String instructions) {
		super();
		this.name = name;
		this.type = type;
		this.numberOfServings = numberOfServings;
		this.ingredients = ingredients;
		this.instructions = instructions;
	}



	
	/**
	 * @param id
	 * @param name
	 * @param type
	 * @param numberOfServings
	 * @param ingredients
	 * @param instructions
	 */
	public RecipeRequest(@Positive(message = "{recipe.positive}") Integer id,
			@NotBlank(message = "{recipeName.notBlank}") @Size(max = 100, message = "{recipeName.size}") @Pattern(regexp = "^(?:\\p{L}\\p{M}*|[',. \\-]|\\s)*$", message = "{recipeName.pattern}") String name,
			@EnumValidator(enumClass = RecipeType.class, message = "{recipeType.invalid}") String type,
			@NotNull(message = "{numberOfServings.notNull}") @Positive(message = "{numberOfServings.positive}") int numberOfServings,
			String ingredients,
			@NotBlank(message = "{instructions.notBlank}") @Size(max = 255, message = "{instructions.size}") @Pattern(regexp = "^(?:\\p{L}\\p{M}*|[0-9]*|[\\/\\-+.,?!*();\"]|\\s)*$", message = "{instructions.pattern}") String instructions) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.numberOfServings = numberOfServings;
		this.ingredients = ingredients;
		this.instructions = instructions;
	}



	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the numberOfServings
	 */
	public int getNumberOfServings() {
		return numberOfServings;
	}

	/**
	 * @param numberOfServings
	 *            the numberOfServings to set
	 */
	public void setNumberOfServings(int numberOfServings) {
		this.numberOfServings = numberOfServings;
	}

	/**
	 * @return the instructions
	 */
	public String getInstructions() {
		return instructions;
	}

	/**
	 * @param instructions
	 *            the instructions to set
	 */
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}



	/**
	 * @return the ingredients
	 */
	public String getIngredients() {
		return ingredients;
	}



	/**
	 * @param ingredients the ingredients to set
	 */
	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}
	
	

}
