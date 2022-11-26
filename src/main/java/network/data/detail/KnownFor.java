package network.data.detail;

import com.google.gson.annotations.SerializedName;

public class KnownFor{

    @SerializedName("id")
    private final String id;

    @SerializedName("title")
    private final String title;

    @SerializedName("fullTitle")
    private final String fullTitle;

    @SerializedName("year")
    private final String year;

    @SerializedName("role")
    private final String role;

    @SerializedName("image")
    private final String image;

    KnownFor(String id, String title, String fullTitle, String year, String role, String image) {
        this.id = id;
        this.title = title;
        this.fullTitle = fullTitle;
        this.year = year;
        this.role = role;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getFullTitle() {
        return fullTitle;
    }

    public String getYear() {
        return year;
    }

    public String getRole() {
        return role;
    }

    public String getImage() {
        return image;
    }
}
