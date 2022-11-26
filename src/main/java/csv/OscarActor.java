package csv;

public class OscarActor {

    private final String name;
    private final int age;
    private final int year;
    private final String movie;

    public OscarActor(String name, int age, int year, String movie){
        this.name = name;
        this.age = age;
        this.year = year;
        this.movie = movie;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getYear() {
        return year;
    }

    public String getMovie() {
        return movie;
    }

    @Override
    public String toString() {
        return "csv.OscarActor{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", year=" + year +
                ", movie='" + movie + '\'' +
                '}';
    }
}
