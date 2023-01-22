package com.abnamro.exam.recipe.filter;



import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.abnamro.exam.recipe.entity.Recipe;
import com.abnamro.exam.recipe.enums.SearchOperation;

/**
 * @author Vishal
 *
 */
public class SearchFilterExclude implements SearchFilter {

    @Override
    public boolean getSearchFilter(SearchOperation opt) {
        return opt == SearchOperation.EXCLUDE;
    }

    @Override
    public Predicate applySearchFilter(CriteriaBuilder cb, String filterKey, String filterValue, Root<Recipe> root) {
        return cb.notLike(cb.lower(root.get(filterKey).as(String.class)), "%" + filterValue + "%");
    }
}
