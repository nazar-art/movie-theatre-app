package net.lelyak.edu.entity;

import net.lelyak.edu.utils.datafactory.InjectRandomData;
import net.lelyak.edu.utils.datafactory.RandomType;

import java.util.Date;

public class Event extends BaseEntity {

//    @InjectRandomData(type = RandomType.WORD)
//    private String name;

    @InjectRandomData(type = RandomType.FUTURE_DATE, min = 5, max = 10)
    private Date airDate;

    @InjectRandomData(type = RandomType.PRICE)
    private Double ticketPrice;

    @InjectRandomData(type = RandomType.EVENT_RATING)
    private Rating rating;
    private Auditorium auditorium;

    //Lazy loading
    private Long auditorium_id;

    /*public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }*/

    public Double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public Rating getEnumRating() {
        return rating;
    }

    public void setEnumRating(Rating rating) {
        this.rating = rating;
    }

    public void setRating(String rating) {
        this.rating = Rating.valueOf(rating);
    }

    public String getRating() {
        return this.rating.toString();
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

    /*@Override
    public String toString() {
        DateFormat df = new SimpleDateFormat("dd MM yyyy H:mm");
        return "Event [id=" + id
                + ", name=" + name
                + ", airDate=" + df.format(airDate)
                + ", auditorium=" + auditorium
                + ", ticketPrice=" + ticketPrice
                + ", rating=" + rating.toString()
                + "]";
    }*/

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name=" + name +
                ", airDate=" + airDate +
                ", ticketPrice=" + ticketPrice +
                ", rating=" + rating +
                ", auditorium=" + auditorium +
                ", auditorium_id=" + auditorium_id +
                '}';
    }
}
