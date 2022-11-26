package csv;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ReaderFromCSV{

    public List<OscarActor> getListOscarActor(String pathToCSV){
        Path path = Paths.get(pathToCSV);
        ArrayList<OscarActor> artists = new ArrayList<>();
        try{
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            for (int i = 1; i < lines.size(); i++){
                String[] entries = lines.get(i).split(",");
                if (entries.length == 5){
                    OscarActor artist = getOscarActorFromEntry(entries);
                    artists.add(artist);
                }
            }
        } catch (IOException exception){
            System.out.println("Don't read this file");
        }
        return artists;
    }

    private OscarActor getOscarActorFromEntry(String[] entries) {
        return new OscarActor(
                entries[3].trim(),
                Integer.parseInt(entries[2].trim()),
                Integer.parseInt(entries[1].trim()),
                entries[4].trim()
        );
    }


}
