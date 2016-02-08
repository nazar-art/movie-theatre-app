package net.lelyak.edu.entity;

import java.util.Calendar;
import java.util.Set;

public class Event {

    private Integer id;
    private String name;
    private Integer price;
    private Rating rating;

    private Set<Calendar> eventDateTime;

    public Event() {
    }

    public Event(String name, Integer price, Rating rating) {
        this.name = name;
        this.price = price;
        this.rating = rating;
    }

    public Event(int id, String name, Integer price, Rating rating) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rating = rating;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Set<Calendar> getEventDateTime() {
        return eventDateTime;
    }

    public void setEventDateTime(Set<Calendar> eventDateTime) {
        this.eventDateTime = eventDateTime;
    }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", rating=" + rating +
                ", eventDateTime=" + eventDateTime +
                '}';
    }
}
