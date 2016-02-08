package net.lelyak.edu.entity;

public class Auditorium {
    private Integer id;
    private String name;
    private Integer numberOfSeats;
    private Integer vipSeats;

    public Auditorium(String name, Integer numberOfSeats, Integer vipSeats) {
        this.name = name;
        this.numberOfSeats = numberOfSeats;
        this.vipSeats = vipSeats;
    }

    public Auditorium(int id, String name, Integer numberOfSeats, Integer vipSeats) {
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

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Integer getVipSeats() {
        return vipSeats;
    }

    public void setVipSeats(Integer vipSeats) {
        this.vipSeats = vipSeats;
    }
}
