package network;

import network.data.ActorResponse;
import network.data.SearchResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IMDbService {

    @GET("/en/API/SearchName/k_4up44bes/{name_actor}")
    public Call<SearchResponse> searchByName(
            @Path("name_actor") String name
    );

    @GET("/en/API/Name/k_4up44bes/{id_actor}")
    public Call<ActorResponse> getActor(
            @Path("id_actor") String idActor
    );
}
