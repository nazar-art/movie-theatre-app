package net.lelyak.edu.entity;

import java.util.List;

public class Auditorium {
    private Integer id;
    private String name;
    private Integer numberOfSeats;
    private List<String> vipSeats;

    public Auditorium(String name, Integer numberOfSeats, List<String> vipSeats) {
        this.name = name;
        this.numberOfSeats = numberOfSeats;
        this.vipSeats = vipSeats;
    }

    public Auditorium(int id, String name, Integer numberOfSeats, List<String> vipSeats) {
        this.id = id;
        this.name = name;
        this.numberOfSeats = numberOfSeats;
        this.vipSeats = vipSeats;
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

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public List<String> getVipSeats() {
        return vipSeats;
    }

    public void setVipSeats(List<String> vipSeats) {
        this.vipSeats = vipSeats;
    }

    @Override
    public String toString() {
        return "Auditorium{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", numberOfSeats=" + numberOfSeats +
                ", vipSeats=" + vipSeats +
                '}';
    }
}
