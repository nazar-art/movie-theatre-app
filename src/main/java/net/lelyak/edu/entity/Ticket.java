package net.lelyak.edu.entity;

import net.lelyak.edu.utils.datafactory.InjectRandomData;
import net.lelyak.edu.utils.datafactory.RandomType;

public class Ticket {
    @InjectRandomData(type = RandomType.NUMBER, min = 50, max = 1000)
    private Integer id;

    @InjectRandomData(type = RandomType.PRICE, min = 5, max = 10)
    private Double price;

    private Event event;
    private User user;


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
                "id=" + id +
                ", price=" + price +
                ", event=" + event +
                ", user=" + user +
                '}';
    }
}
