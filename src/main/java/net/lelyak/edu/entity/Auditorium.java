package net.lelyak.edu.entity;

import java.util.Set;

public class Auditorium {
    private Integer id;
    private String name;
    private Integer seats;
    private Set<Integer> vip;

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

    public int getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public Set<Integer> getVip() {
        return vip;
    }

    public void setVip(Set<Integer> vip) {
        this.vip = vip;
    }

    @Override
    public String toString() {
        return "Auditorium{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", seats=" + seats +
                ", vip=" + vip +
                '}';
    }
}
