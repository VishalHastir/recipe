package com.abnamro.exam.recipe.filter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.abnamro.exam.recipe.entity.Recipe;
import com.abnamro.exam.recipe.enums.SearchOperation;

/**
 * @author Vishal
 *
 */
public interface SearchFilter  {
    boolean getSearchFilter(SearchOperation opt);
    Predicate applySearchFilter(CriteriaBuilder cb, String filterKey, String filterValue, Root<Recipe> root);
}