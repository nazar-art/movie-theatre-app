package net.lelyak.edu.entity;

public class Ticket {
    private Event event;
    private Integer price;
    private User user;

    public Ticket(Event event, Integer price, User user) {
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
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
