package se.amanda.game;
//private final för att varje enskilt objekt har ett unikt värde
public class Room {
    private final String name;
    private final String description;
    private boolean hasFryingPan;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        this.hasFryingPan = hasFryingPan;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean hasFryingPan() {
        return hasFryingPan;
    }
}
