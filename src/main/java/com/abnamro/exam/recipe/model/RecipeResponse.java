package com.abnamro.exam.recipe.model;


import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.abnamro.exam.recipe.entity.Recipe;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author Vishal
 *
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecipeResponse {
	
    @ApiModelProperty(notes = "Recipe Id", example = "1")
    private int id;

    @ApiModelProperty(notes = "Recipe name", example = "Pasta")
    private String name;

    @ApiModelProperty(notes = "Recipe Type", example = "VEGETARIAN")
    private String type;

    @ApiModelProperty(notes = "Number of servings", example = "1")
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int numberOfServings;

    @ApiModelProperty(notes = "Ingredients used in recipe", example = "pata,pesto")
    @JsonIgnoreProperties("ingredients")
    private String ingredients;

    @ApiModelProperty(notes = "Recipe instructions", example = "add salt and oil to pasta and boil it")
    private String instructions;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime updatedAt;

    public RecipeResponse() {
    }

    /**
     * @param recipe
     */
    public RecipeResponse(Recipe recipe) {
        this.id = recipe.getId();
        this.name = recipe.getName();
        this.type = recipe.getType();
        this.instructions = recipe.getInstructions();
        this.createdAt = recipe.getCreatedAt();
        this.updatedAt = recipe.getUpdatedAt();
        this.numberOfServings = recipe.getNumberOfServings();
        this.ingredients = recipe.getIngredients();
    }

    public RecipeResponse(Integer id) {
    	 this.id = id;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
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
	 * @param type the type to set
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
	 * @param numberOfServings the numberOfServings to set
	 */
	public void setNumberOfServings(int numberOfServings) {
		this.numberOfServings = numberOfServings;
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

	/**
	 * @return the instructions
	 */
	public String getInstructions() {
		return instructions;
	}

	/**
	 * @param instructions the instructions to set
	 */
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	/**
	 * @return the createdAt
	 */
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * @return the updatedAt
	 */
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * @param updatedAt the updatedAt to set
	 */
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	
}
