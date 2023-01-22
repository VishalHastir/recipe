package com.abnamro.exam.recipe.model;

import java.util.List;

import javax.validation.Valid;

import com.abnamro.exam.recipe.config.EnumValidator;
import com.abnamro.exam.recipe.enums.SearchConditionRequestInput;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author Vishal
 *
 */
public class RecipeSearchRequest {
    @JsonProperty("searchFilter")
    @ApiModelProperty(notes = "Search filter you want to search recipe with")
    @Valid
    private List<SearchConditionsRequest> searchConditionsRequests;

    @ApiModelProperty(notes = "All or just one condition is enough to search recipe", example = "all")
    @EnumValidator(enumClass = SearchConditionRequestInput.class, message = "{searchCondition.invalid}")
    @JsonProperty("type")
    private String searchConditionsType;

    
    public RecipeSearchRequest() {
    }

    /**
     * @param searchConditionsRequests
     * @param searchConditionsType
     */
    public RecipeSearchRequest(List<SearchConditionsRequest> searchConditionsRequests, String searchConditionsType) {
        this.searchConditionsRequests = searchConditionsRequests;
        this.searchConditionsType = searchConditionsType;
    }

	/**
	 * @return the searchConditionsRequests
	 */
	public List<SearchConditionsRequest> getSearchConditionsRequests() {
		return searchConditionsRequests;
	}

	/**
	 * @param searchConditionsRequests the searchConditionsRequests to set
	 */
	public void setSearchConditionsRequests(List<SearchConditionsRequest> searchConditionsRequests) {
		this.searchConditionsRequests = searchConditionsRequests;
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
