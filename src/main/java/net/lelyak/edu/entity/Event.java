package net.lelyak.edu.entity;

import net.lelyak.edu.utils.datafactory.InjectRandomData;
import net.lelyak.edu.utils.datafactory.RandomType;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends BaseEntity {

    @InjectRandomData(type = RandomType.WORD)
    private String name;

    @InjectRandomData(type = RandomType.PRICE)
    private Double price;

    @InjectRandomData(type = RandomType.EVENT_RATING)
    private EventRating rating;

    @InjectRandomData(type = RandomType.FUTURE_DATE, min = 5, max = 10)
    private Date airDate;

    private Auditorium auditorium;

    //Lazy loading
    private Long auditorium_id;

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

    public EventRating getRating() {
        return rating;
    }

    public void setRating(EventRating rating) {
        this.rating = rating;
    }

    public Date getAirDate() {
        return airDate;
    }

    public void setAirDate(Date date) {
            this.airDate = date;
    }

    public Auditorium getAuditorium() {
        return auditorium;
    }

    public void setAuditorium(Auditorium auditorium) {
        this.auditorium = auditorium;
    }

    public Long getAuditorium_id() {
        return auditorium_id;
    }

    public void setAuditorium_id(Long auditorium_id) {
        this.auditorium_id = auditorium_id;
    }

    @Override
    public String toString() {
        DateFormat df = new SimpleDateFormat("d MM yyyy H:mm");
        return "Event [id=" + getId() + ", name=" + getName() + ", airDate=" + df.format(airDate) + ", auditorium=" + auditorium + ", ticketPrice=" + price
                + ", rating=" + rating + "]";
    }
}
