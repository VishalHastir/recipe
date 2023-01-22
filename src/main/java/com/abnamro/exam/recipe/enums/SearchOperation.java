package com.abnamro.exam.recipe.enums;

import java.util.Optional;

/**
 * @author Vishal
 *
 */
public enum SearchOperation {

    INCLUDE, EXCLUDE;


    public static Optional<SearchOperation> getOperation(final String input) {
        String lowerInput = input.toLowerCase();
        switch (lowerInput) {
            case "in":
                return Optional.of(INCLUDE);
            case "ex":
                return Optional.of(EXCLUDE);
        }
        return Optional.empty();
    }
}
