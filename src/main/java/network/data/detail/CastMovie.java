package network.data.detail;

import com.google.gson.annotations.SerializedName;

public class CastMovie{

    @SerializedName("id")
    private final String id;

    @SerializedName("role")
    private final String role;

    @SerializedName("title")
    private final String title;

    @SerializedName("year")
    private final String year;

    @SerializedName("description")
    private final String description;

    CastMovie(String id, String role, String title, String year, String description) {
        this.id = id;
        this.role = role;
        this.title = title;
        this.year = year;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Title: " + title +
                "\nYear: " + year +
                "\nDescription: " + description;
    }
}
