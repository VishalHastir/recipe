package com.abnamro.exam.recipe.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author Vishal
 *
 */

@Entity
@Table(name = "recipes")
public class Recipe {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column
    private String name;

    @Column
    private String ingredients;
    @Column
    private String instructions;
    @Column
    private String type;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column
    private int numberOfServings;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
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
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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


    
}
