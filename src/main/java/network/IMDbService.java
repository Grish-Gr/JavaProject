package network;

import network.data.ActorResponse;
import network.data.SearchResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IMDbService {

    @GET("/en/API/SearchName/{api_key}/{name_actor}")
    public Call<SearchResponse> searchByName(
            @Path("api_key") String apiKey,
            @Path("name_actor") String name
    );

    @GET("/en/API/Name/{api_key}/{id_actor}")
    public Call<ActorResponse> getActor(
            @Path("api_key") String apiKey,
            @Path("id_actor") String idActor
    );
}
