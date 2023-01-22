package com.abnamro.exam.recipe.constants;

/**
 * @author Vishal
 *
 */
public class CommonConstants {
    public static final String RECIPE_INGREDIENTS_TABLE_NAME = "recipeIngredients";
    public static final String INGREDIENT_KEY = "ingredient";
    public static final String RECIPE_NAME_PATTERN = "^(?:\\p{L}\\p{M}*|[',. \\-]|\\s)*$";
    public static final String INGREDIENT_NAME_PATTERN = "^(?:\\p{L}\\p{M}*|[',. \\-]|\\s)*$";
    public static final int RECIPE_NAME_MAX_LENGTH = 100;
    public static final int INGREDIENT_NAME_MAX_LENGTH = 100;
    public static final int DEFAULT_MAX_LENGTH = 255;
    public static final String INSTRUCTIONS_FREE_TEXT_PATTERN = "^(?:\\p{L}\\p{M}*|[0-9]*|[\\/\\-+.,?!*();\"]|\\s)*$";


}
