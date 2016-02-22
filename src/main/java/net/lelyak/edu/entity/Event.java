package net.lelyak.edu.entity;

import net.lelyak.edu.utils.datafactory.InjectRandomData;
import net.lelyak.edu.utils.datafactory.RandomType;

import java.util.Date;
import java.util.Objects;

public class Event {
    @InjectRandomData(type = RandomType.NUMBER, min = 50, max = 1000)
    private Integer id;

    @InjectRandomData(type = RandomType.WORD)
    private String name;

    @InjectRandomData(type = RandomType.PRICE)
    private Double price;

    @InjectRandomData(type = RandomType.EVENT_RATING)
    private EventRating eventRating;

    @InjectRandomData(type = RandomType.FUTURE_DATE, min = 5, max = 10)
    private Date dateTime;

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

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date date) {
            this.dateTime = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(id, event.id) &&
                Objects.equals(name, event.name) &&
                Objects.equals(price, event.price) &&
                eventRating == event.eventRating;
    }

    @Override
    public int hashCode() {
        if (this.id != null) {
            return id;
        }
        return Objects.hash(name, price, eventRating);
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", eventRating=" + eventRating +
                ", dateTime=" + dateTime +
                '}';
    }
}
