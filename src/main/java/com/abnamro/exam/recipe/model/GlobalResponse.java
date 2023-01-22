package com.abnamro.exam.recipe.model;

import java.util.Objects;

/**
 * @author Vishal
 *
 */
public class GlobalResponse {

    private final String message;

    public GlobalResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GlobalResponse that = (GlobalResponse) o;

        return Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return message != null ? message.hashCode() : 0;
    }
}
