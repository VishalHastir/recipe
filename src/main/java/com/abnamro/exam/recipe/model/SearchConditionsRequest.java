package com.abnamro.exam.recipe.model;

import javax.validation.Valid;

import com.abnamro.exam.recipe.config.EnumValidator;
import com.abnamro.exam.recipe.enums.FilterKeyRequestInput;
import com.abnamro.exam.recipe.enums.SearchOperationRequestInput;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author Vishal
 *
 */
@Valid
public class SearchConditionsRequest {

    @ApiModelProperty(notes = "The name of the column you want to search between " +
            "name, " +
            "numberOfServings, " +
            "type, " +
            "instructions, " +
            "ingredients)", example = "name")
    @EnumValidator(enumClass = FilterKeyRequestInput.class, message = "{filterKey.invalid}")
    private String filterKey;


    @ApiModelProperty(notes = "Recipe Name", example = "Pasta")
    private String value;

    @ApiModelProperty(notes = "The search operation type (IN - include, " + " EX - exclude)", example = "IN")
    @EnumValidator(enumClass = SearchOperationRequestInput.class, message = "{searchOperation.invalid}")
    private String operation;

    @ApiModelProperty(hidden = true)
    private String searchConditionsType;

    public SearchConditionsRequest() {
    }

    /**
     * @param filterKey
     * @param value
     * @param operation
     */
    public SearchConditionsRequest(String filterKey, String value, String operation) {
        this.filterKey = filterKey;
        this.value = value;
        this.operation = operation;
    }

	/**
	 * @return the filterKey
	 */
	public String getFilterKey() {
		return filterKey;
	}

	/**
	 * @param filterKey the filterKey to set
	 */
	public void setFilterKey(String filterKey) {
		this.filterKey = filterKey;
	}

	/**
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the operation
	 */
	public String getOperation() {
		return operation;
	}

	/**
	 * @param operation the operation to set
	 */
	public void setOperation(String operation) {
		this.operation = operation;
	}

	/**
	 * @return the searchConditionsType
	 */
	public String getSearchConditionsType() {
		return searchConditionsType;
	}

	/**
	 * @param searchConditionsType the searchConditionsType to set
	 */
	public void setSearchConditionsType(String searchConditionsType) {
		this.searchConditionsType = searchConditionsType;
	}

   
}
