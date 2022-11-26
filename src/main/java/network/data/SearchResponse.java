package network.data;


import com.google.gson.annotations.SerializedName;
import network.data.detail.SearchItem;

import java.util.ArrayList;

public class SearchResponse {

    @SerializedName("searchType")
    private final String searchType;

    @SerializedName("expression")
    private final String expression;

    @SerializedName("results")
    private final ArrayList<SearchItem> results;

    @SerializedName("errorMessage")
    private final String errorMessage;

    public SearchResponse(String searchType, String expression, ArrayList<SearchItem> results, String errorMessage) {
        this.searchType = searchType;
        this.expression = expression;
        this.results = results;
        this.errorMessage = errorMessage;
    }

    public String getSearchType() {
        return searchType;
    }

    public String getExpression() {
        return expression;
    }

    public ArrayList<SearchItem> getResults() {
        return results;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String toString() {
        return "SearchResponse{" +
                "searchType='" + searchType + '\'' +
                ", expression='" + expression + '\'' +
                ", results=" + results +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}