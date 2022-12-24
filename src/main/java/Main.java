import csv.OscarActor;
import csv.ReaderFromCSV;
import database.DBHandler;
import database.FilmEntity;
import database.OscarActorEntity;
import graphics.ManagerGraphics;
import graphics.YearMovieJFrame;
import network.IMDbService;
import network.data.ActorResponse;
import network.data.SearchResponse;
import network.data.detail.CastMovie;
import network.data.detail.SearchItem;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.Year;
import org.sqlite.core.DB;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    private static final String BASE_URL = "https://imdb-api.com";
    private static final String API_KEY = "k_4up44bes";
    private static final String PATH_TO_DOWNLOAD = "src/main/resources/images/";
    private static final String PATH_TO_FEMALE_ACTORS = "src/main/resources/csv/oscar_age_female.csv";
    private static final String PATH_TO_MALE_ACTORS = "src/main/resources/csv/oscar_age_male.csv";
    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private static final IMDbService service = retrofit.create(IMDbService.class);
    private static final ManagerGraphics managerGraphics = new ManagerGraphics();

    public static void main(String[] args) throws SQLException {
        DBHandler dbHandler = DBHandler.getInstance();
        List<OscarActor> oscarActors = getOscarActorFromCSV();
        System.out.println("Oscar Actors:");
        for (int i = 0; i < oscarActors.size(); i++){
            System.out.println(String.format("%d) %s", i, oscarActors.get(i).toString()));
        }
        Scanner scannerIn = new Scanner(System.in);
        do{
            System.out.print("\nGet more info (Write index): ");
            int indexActor = scannerIn.nextInt();
            OscarActorEntity oscarActorEntity = dbHandler.getOscarActorFromBD(indexActor);
            if (oscarActorEntity != null){
                System.out.println(oscarActorEntity.toString());

                System.out.print("\nGet list movies (yes/no): ");
                if (scannerIn.next().equalsIgnoreCase("yes")){
                    for (FilmEntity filmEntity: dbHandler.getAllMovie(oscarActorEntity.getId())){
                        System.out.println(filmEntity.toString());
                        System.out.println();
                    }
                }

                System.out.print("\nGet graphics year movies (yes/no): ");
                if (scannerIn.next().equalsIgnoreCase("yes")){
                    new YearMovieJFrame(managerGraphics, dbHandler.getAllMovie(oscarActorEntity.getId())).show();
                }
            } else {
                OscarActor actor = oscarActors.get(indexActor);
                ActorResponse response = getInfoActor(actor.getName());
                System.out.println(response.toString());
                dbHandler.addEntryActor(indexActor, response);

                System.out.print("\nGet list movies (yes/no): ");
                if (scannerIn.next().equalsIgnoreCase("yes")){
                    for (CastMovie movie: response.getCastMovies()){
                        System.out.println(movie.toString());
                        System.out.println();
                    }
                }

                System.out.print("\nGet graphics year movies (yes/no): ");
                if (scannerIn.next().equalsIgnoreCase("yes")){
                    new YearMovieJFrame(managerGraphics, response.getCastMovies()).show();
                }

                System.out.print("\nDownload image actor (yes/no): ");
                if (scannerIn.next().equalsIgnoreCase("yes")){
                    downloadImage(response.getImage(), response.getName());
                }
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
            SearchResponse searchResponse = service.searchByName(API_KEY, nameActor).execute().body();
            assert searchResponse != null;
            SearchItem searchItem = searchResponse.getResults().get(0);
            return service.getActor(API_KEY, searchResponse.getResults().get(0).getId()).execute().body();
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

    private static void showGraphic(List<FilmEntity> filmEntityList){
        Map<String, List<FilmEntity>> mapYearFilms = filmEntityList.stream().collect(
                Collectors.groupingBy(FilmEntity::getYear)
            );
        TimeSeries timeSeries = new TimeSeries("Year - Film");
        for (String keyYear: mapYearFilms.keySet()){
            int countFilm = mapYearFilms.get(keyYear).size();
            timeSeries.add(new Year(Integer.parseInt(keyYear)), countFilm);
        }
    }
}

