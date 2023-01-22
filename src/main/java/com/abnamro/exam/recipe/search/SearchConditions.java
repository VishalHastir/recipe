package com.abnamro.exam.recipe.search;

import com.abnamro.exam.recipe.model.SearchConditionsRequest;

/**
 * @author Vishal
 *
 */
public class SearchConditions {
    private String filterKey;
    private Object value;
    private String operation;
    private String searchConditionsType;

    public SearchConditions() {
    }

    /**
     * @param filterKey
     * @param operation
     * @param value
     */
    public SearchConditions(String filterKey, String operation, Object value){
        super();
        this.filterKey = filterKey;
        this.operation = operation;
        this.value = value;
    }

    /**
     * @param searchConditionsRequest
     */
    public SearchConditions(SearchConditionsRequest searchConditionsRequest) {
        this.searchConditionsType = searchConditionsRequest.getSearchConditionsType();
        this.filterKey = searchConditionsRequest.getFilterKey();
        this.operation = searchConditionsRequest.getOperation();
        this.value = searchConditionsRequest.getValue();
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
	public void setValue(Object value) {
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
