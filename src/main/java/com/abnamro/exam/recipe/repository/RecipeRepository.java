/**
 * 
 */
package com.abnamro.exam.recipe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.abnamro.exam.recipe.entity.Recipe;

/**
 * @author Vishal
 *
 */
@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer>, JpaSpecificationExecutor<Recipe> {

}
