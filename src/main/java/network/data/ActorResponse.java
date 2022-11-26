package network.data;

import com.google.gson.annotations.SerializedName;
import network.data.detail.CastMovie;
import network.data.detail.KnownFor;

import java.util.ArrayList;

public class ActorResponse {

    @SerializedName("id")
    private final String id;

    @SerializedName("name")
    private final String name;

    @SerializedName("role")
    private final String role;

    @SerializedName("image")
    private final String image;

    @SerializedName("summary")
    private final String summary;

    @SerializedName("birthDate")
    private final String birthDate;

    @SerializedName("deathDate")
    private final String deathDate;

    @SerializedName("awards")
    private final String awards;

    @SerializedName("height")
    private final String height;

    @SerializedName("knownFor")
    private final ArrayList<KnownFor> knownFor;

    @SerializedName("castMovies")
    private final ArrayList<CastMovie> castMovies;

    @SerializedName("errorMessage")
    private final String errorMessage;

    public ActorResponse(String id, String name,
                         String role, String image,
                         String summary, String birthDate,
                         String deathDate, String awards,
                         String height, ArrayList<KnownFor> knownFor,
                         ArrayList<CastMovie> castMovies, String errorMessage) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.image = image;
        this.summary = summary;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
        this.awards = awards;
        this.height = height;
        this.knownFor = knownFor;
        this.castMovies = castMovies;
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "Name: " + name +
                "\nRole: " + role +
                "\nSummary: " + summary +
                "\nBirthDate: " + birthDate +
                "\nAwards: " + awards;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getImage() {
        return image;
    }

    public String getSummary() {
        return summary;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getDeathDate() {
        return deathDate;
    }

    public String getAwards() {
        return awards;
    }

    public String getHeight() {
        return height;
    }

    public ArrayList<KnownFor> getKnownFor() {
        return knownFor;
    }

    public ArrayList<CastMovie> getCastMovies() {
        return castMovies;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}

