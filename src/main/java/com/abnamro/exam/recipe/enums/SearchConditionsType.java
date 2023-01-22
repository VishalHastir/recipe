package com.abnamro.exam.recipe.enums;

import java.util.Optional;

/**
 * @author Vishal
 *
 */
public enum SearchConditionsType {
    ANY, ALL;

    public static Optional<SearchConditionsType> getSearchConditionsType(final String dataOption) {
        String lowerDataOption = dataOption.toLowerCase();
        switch (lowerDataOption) {
            case "all":
                return Optional.of(ALL);
            case "any":
                return Optional.of(ANY);
        }
        return Optional.empty();
    }
}
