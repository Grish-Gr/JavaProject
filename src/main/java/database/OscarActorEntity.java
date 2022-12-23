package database;

public class OscarActorEntity {

    private final int id;
    private final String nameActor;
    private final String descriptionActor;
    private final String birthDate;

    public OscarActorEntity(int id, String nameActor, String descriptionActor, String birthDate) {
        this.id = id;
        this.nameActor = nameActor;
        this.descriptionActor = descriptionActor;
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public String getNameActor() {
        return nameActor;
    }

    public String getDescriptionActor() {
        return descriptionActor;
    }

    public String getBirthDate() {
        return birthDate;
    }

    @Override
    public String toString() {
        return "Name Actor: " + nameActor +
                "\nDescription: " + descriptionActor +
                "\nBirthDate: " + birthDate;
    }
}
