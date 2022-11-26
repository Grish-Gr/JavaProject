package network.data;

import com.google.gson.annotations.SerializedName;

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
        return "ActorResponse{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", image='" + image + '\'' +
                ", summary='" + summary + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", deathDate='" + deathDate + '\'' +
                ", awards='" + awards + '\'' +
                ", height='" + height + '\'' +
                ", knownFor=" + knownFor +
                ", castMovies=" + castMovies +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}

class CastMovie{

    @SerializedName("id")
    public String id;

    @SerializedName("role")
    public String role;

    @SerializedName("title")
    private final String title;

    @SerializedName("year")
    private final String year;

    @SerializedName("description")
    private final String description;
}

class KnownFor{
    public String id;
    public String title;
    public String fullTitle;
    public String year;
    public String role;
    public String image;
}

