package net.lelyak.edu.entity;

import org.springframework.stereotype.Component;

@Component
public class Auditorium extends BaseEntity {

    private Integer seats;
    private String vip;

    public int getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }

    @Override
    public String toString() {
        return "Auditorium{" +
                ", seats=" + seats +
                ", vip=" + vip +
                '}';
    }
}
