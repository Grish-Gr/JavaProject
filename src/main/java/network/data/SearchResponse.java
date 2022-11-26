package network.data;


import com.google.gson.annotations.SerializedName;

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
}

class SearchItem {
    @SerializedName("id")
    private final String id;

    @SerializedName("resultType")
    private final String resultType;

    @SerializedName("image")
    private final String imageUrl;

    @SerializedName("title")
    private final String title;

    @SerializedName("description")
    private final String description;

    SearchItem(String id, String resultType, String imageUrl, String title, String description) {
        this.id = id;
        this.resultType = resultType;
        this.imageUrl = imageUrl;
        this.title = title;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getResultType() {
        return resultType;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}