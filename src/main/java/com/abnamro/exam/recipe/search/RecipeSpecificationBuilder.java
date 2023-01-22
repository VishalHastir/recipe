package com.abnamro.exam.recipe.search;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;

import com.abnamro.exam.recipe.entity.Recipe;
import com.abnamro.exam.recipe.enums.SearchConditionsType;

/**
 * @author Vishal
 *
 */
public class RecipeSpecificationBuilder {
    private final List<SearchConditions> searchConditionsList;

    /**
     * @param searchConditionsList
     */
    public RecipeSpecificationBuilder(List<SearchConditions> searchConditionsList) {
        this.searchConditionsList = searchConditionsList;
    }

    /**
     * @param searchConditions
     * @return
     */
    public final RecipeSpecificationBuilder addCriteria(SearchConditions searchConditions) {
    	searchConditionsList.add(searchConditions);
        return this;
    }

    /**
     * @return
     */
    public Optional<Specification<Recipe>> build() {
        if (searchConditionsList.size() == 0) return Optional.empty();

        Specification<Recipe> result = new RecipeSpecification(searchConditionsList.get(0));

        for (int i = 1; i < searchConditionsList.size(); i++) {
            SearchConditions criteria = searchConditionsList.get(i);
            Optional<SearchConditionsType> searchConditionsType = SearchConditionsType.getSearchConditionsType(criteria.getSearchConditionsType());
            if (searchConditionsType.isPresent()) {
                result = (searchConditionsType.get() == SearchConditionsType.ALL)
                        ? Specification.where(result).and(new RecipeSpecification(criteria))
                        : Specification.where(result).or(new RecipeSpecification(criteria));
            }
        }
        return Optional.of(result);
    }
}
