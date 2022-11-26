import csv.OscarActor;
import csv.ReaderFromCSV;
import network.IMDbService;
import network.data.ActorResponse;
import network.data.SearchResponse;
import network.data.detail.CastMovie;
import network.data.detail.SearchItem;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final String BASE_URL = "https://imdb-api.com";
    private static final String PATH_TO_DOWNLOAD = "src/main/resources/images/";
    private static final String PATH_TO_FEMALE_ACTORS = "src/main/resources/csv/oscar_age_female.csv";
    private static final String PATH_TO_MALE_ACTORS = "src/main/resources/csv/oscar_age_male.csv";
    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private static final IMDbService service = retrofit.create(IMDbService.class);

    public static void main(String[] args){
        List<OscarActor> oscarActors = getOscarActorFromCSV();
        System.out.println("Oscar Actors:");
        for (int i = 0; i < oscarActors.size(); i++){
            System.out.println(String.format("%d) %s", i, oscarActors.get(i).toString()));
        }

        Scanner scannerIn = new Scanner(System.in);
        do{
            System.out.print("\nGet more info (Write index): ");
            OscarActor actor = oscarActors.get(scannerIn.nextInt());
            ActorResponse response = getInfoActor(actor.getName());
            System.out.println(response.toString());

            System.out.print("\nGet list movies (yes/no): ");
            if (scannerIn.next().equalsIgnoreCase("yes")){
                for (CastMovie movie: response.getCastMovies()){
                    System.out.println(movie.toString());
                    System.out.println();
                }
            }

            System.out.print("\nDownload image actor (yes/no): ");
            if (scannerIn.next().equalsIgnoreCase("yes")){
                downloadImage(response.getImage(), response.getName());
            }

            System.out.print("\nWrite 'end' for End Program: ");
        } while (!scannerIn.next().equalsIgnoreCase("end"));
        scannerIn.close();
    }

    private static List<OscarActor> getOscarActorFromCSV(){
        ArrayList<OscarActor> oscarActors = new ArrayList<>();
        ReaderFromCSV reader = new ReaderFromCSV();
        oscarActors.addAll(reader.getListOscarActor(PATH_TO_FEMALE_ACTORS));
        oscarActors.addAll(reader.getListOscarActor(PATH_TO_MALE_ACTORS));
        return oscarActors;
    }

    private static ActorResponse getInfoActor(String nameActor){
        try {
            SearchResponse searchResponse = service.searchByName(nameActor).execute().body();
            assert searchResponse != null;
            SearchItem searchItem = searchResponse.getResults().get(0);
            return service.getActor(searchResponse.getResults().get(0).getId()).execute().body();
        } catch (IOException e) {
            return null;
        }
    }

    private static void downloadImage(String imageUrl, String nameFileForDownload){
        try(InputStream in = new URL(imageUrl).openStream()){
            Files.copy(in, Paths.get(PATH_TO_DOWNLOAD + nameFileForDownload + ".jpg"));
            System.out.println("Download Completed!");
        } catch (Exception e){
            System.out.println("Download Failed!");
        }
    }
}

