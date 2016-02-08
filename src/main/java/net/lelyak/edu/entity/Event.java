package net.lelyak.edu.entity;

import java.util.Calendar;
import java.util.Set;

public class Event {

    private Integer id;
    private String name;
    private Double price;
    private EventRating eventRating;

    private Set<Calendar> eventDateTime;

    public Event() {
    }

    public Event(String name, Double price, EventRating eventRating) {
        this.name = name;
        this.price = price;
        this.eventRating = eventRating;
    }

    public Event(int id, String name, Double price, EventRating eventRating) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.eventRating = eventRating;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public EventRating getEventRating() {
        return eventRating;
    }

    public void setEventRating(EventRating eventRating) {
        this.eventRating = eventRating;
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
                ", eventRating=" + eventRating +
                ", eventDateTime=" + eventDateTime +
                '}';
    }
}
