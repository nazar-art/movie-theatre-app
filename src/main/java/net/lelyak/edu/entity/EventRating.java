package net.lelyak.edu.entity;

public enum EventRating {
    HIGH("High"), MIDDLE("Middle"), LOW("Low");

    private String name;

    EventRating(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
