package database;

import network.data.ActorResponse;
import org.sqlite.JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DBHandler {

    private static final String CON_STR = "jdbc:sqlite:src/main/resources/database/oscar_actors.db";

    private static DBHandler instance = null;

    public static synchronized DBHandler getInstance() throws SQLException {
        if (instance == null)
            instance = new DBHandler();
        return instance;
    }

    private final Connection connection;

    private DBHandler() throws SQLException {
        DriverManager.registerDriver(new JDBC());
        this.connection = DriverManager.getConnection(CON_STR);
    }

    public OscarActorEntity getOscarActorFromBD(int indexActorByCsv){
        try (PreparedStatement statement = this.connection.prepareStatement(
                "SELECT oscar_id, oscar_name, oscar_description, oscar_birthDate" +
                    " FROM oscar_actors WHERE oscar_id = ?")) {
            statement.setObject(1, indexActorByCsv);

            OscarActorEntity oscarActor;
            ResultSet resultSet = statement.executeQuery();
            oscarActor = new OscarActorEntity(
                    resultSet.getInt("oscar_id"),
                    resultSet.getString("oscar_name"),
                    resultSet.getString("oscar_description"),
                    resultSet.getString("oscar_birthDate")
            );
            if (oscarActor.getNameActor() == null){
                return null;
            }
            return oscarActor;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<FilmEntity> getAllMovie(int indexActorByCsv) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "SELECT film_id, film_title, film_year, film_description" +
                    " FROM movies WHERE id_actor = ?")) {
            statement.setObject(1, indexActorByCsv);

            List<FilmEntity> films = new ArrayList<FilmEntity>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                films.add(new FilmEntity(
                        resultSet.getInt("film_id"),
                        resultSet.getString("film_title"),
                        resultSet.getString("film_year"),
                        resultSet.getString("film_description")));
            }
            return films;

        } catch (SQLException e) {
            e.printStackTrace();
            // Если произошла ошибка - возвращаем пустую коллекцию
            return Collections.emptyList();
        }
    }

    public void addEntryActor(int indexActorByCsv, ActorResponse actorResponse) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO oscar_actors(oscar_id, oscar_name, oscar_description, oscar_birthDate) " +
                        "VALUES(?, ?, ?, ?)")) {
            statement.setObject(1, indexActorByCsv);
            statement.setObject(2, actorResponse.getName());
            statement.setObject(3, actorResponse.getSummary());
            statement.setObject(4, actorResponse.getBirthDate());
            System.out.println("IN DB ");
            System.out.print(actorResponse.toString());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        actorResponse.getCastMovies().forEach(movie -> {
            try (PreparedStatement statement = this.connection.prepareStatement(
                    "INSERT INTO movies(film_title, film_year, film_description, id_actor) " +
                            "VALUES(?, ?, ?, ?)")) {
                statement.setObject(1, movie.getTitle());
                statement.setObject(2, movie.getYear());
                statement.setObject(3, movie.getDescription());
                statement.setObject(4, indexActorByCsv);
                statement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public void deleteEntryActor(int indexActorByCsv) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "DELETE FROM oscar_actors WHERE oscar_id = ?")) {
            statement.setObject(1, indexActorByCsv);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllEntryActors(){
        try (PreparedStatement statement = this.connection.prepareStatement(
                "DELETE FROM oscar_actors")) {
            if (statement.execute())
                System.out.println("Delete All");
            else
                System.out.println("Fail");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
