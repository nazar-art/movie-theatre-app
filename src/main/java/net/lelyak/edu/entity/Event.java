package net.lelyak.edu.entity;

import java.util.Calendar;
import java.util.Set;

public class Event {
    private String name;
    private Integer price;
    private Rating rating;

    private Set<Calendar> eventTime;

    public Event() {
    }

    public Event(String name, Integer price, Rating rating) {
        this.name = name;
        this.price = price;
        this.rating = rating;
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

    public Set<Calendar> getEventTime() {
        return eventTime;
    }

    public void setEventTime(Set<Calendar> eventTime) {
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", rating=" + rating +
                ", eventTime=" + eventTime +
                '}';
    }
}
