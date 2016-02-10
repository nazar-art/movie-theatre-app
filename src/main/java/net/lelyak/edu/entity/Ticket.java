package net.lelyak.edu.entity;

public class Ticket {
    private Integer id;
    private Event event;
    private Double price;
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
