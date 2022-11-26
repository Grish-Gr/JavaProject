package network.data.detail;

import com.google.gson.annotations.SerializedName;

public class SearchItem {
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
