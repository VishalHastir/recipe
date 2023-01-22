package com.abnamro.exam.recipe.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.abnamro.exam.recipe.entity.Recipe;
import com.abnamro.exam.recipe.enums.SearchOperation;
import com.abnamro.exam.recipe.filter.SearchFilter;
import com.abnamro.exam.recipe.filter.SearchFilterExclude;
import com.abnamro.exam.recipe.filter.SearchFilterInclude;

/**
 * @author Vishal
 *
 */
public class RecipeSpecification implements Specification<Recipe> {
    
	private static final long serialVersionUID = 12L;

	private final SearchConditions searchConditions;

    private static final List<SearchFilter> searchFilters = new ArrayList<>();

    /**
     * @param searchConditions
     */
    public RecipeSpecification(SearchConditions searchConditions) {
        super();
        filterList();
        this.searchConditions = searchConditions;
    }

    @Override
    public Predicate toPredicate(Root<Recipe> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Optional<SearchOperation> operation = SearchOperation.getOperation(searchConditions.getOperation());
        String filterValue = searchConditions.getValue().toString().toLowerCase();
        String filterKey = searchConditions.getFilterKey();

        return operation.flatMap(searchOperation -> searchFilters
                .stream()
                .filter(searchFilter -> searchFilter.getSearchFilter(searchOperation))
                .findFirst()
                .map(searchFilter -> searchFilter.applySearchFilter(cb, filterKey, filterValue, root))).orElse(null);
    }

    private void filterList() {
        searchFilters.add(new SearchFilterInclude());
        searchFilters.add(new SearchFilterExclude());
    }
}
