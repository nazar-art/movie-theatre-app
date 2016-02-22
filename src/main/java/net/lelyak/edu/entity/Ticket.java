package net.lelyak.edu.entity;

import net.lelyak.edu.utils.InjectRandomData;
import net.lelyak.edu.utils.RandomType;

public class Ticket {
    @InjectRandomData(type = RandomType.NUMERIC, min = 3, max = 5)
    private Integer id;

    @InjectRandomData(type = RandomType.FUTURE_DATE, min = 5, max = 10)
    private Double price;

    private Event event;
    private User user;

    public Ticket() {
    }

    public Ticket(Event event, Double price, User user) {
        this.event = event;
        this.price = price;
        this.user = user;
    }

    public Ticket(int id, Event event, Double price, User user) {
        this.id = id;
        this.event = event;
        this.price = price;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "event=" + event +
                ", price=" + price +
                ", user=" + user +
                '}';
    }
}
