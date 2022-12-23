package database;

import javax.persistence.*;

public class FilmEntity {
    private final int id;
    private final String title;
    private final String year;
    private final String description;

    public FilmEntity(int id, String title, String year, String description) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.description = description;
    }

    public int getId() {
        return id;
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
