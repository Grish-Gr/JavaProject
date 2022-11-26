package network;

import network.data.SearchResponse;
import retrofit2.Call;

public interface IMDbService {

    public Call<SearchResponse> searchByName(String name);
}
