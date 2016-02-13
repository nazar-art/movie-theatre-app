package net.lelyak.edu.entity;

import net.lelyak.edu.utils.Logger;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class User {
    private Integer id;
    private String name;
    private Gender gender;
    private Calendar birthday;
    private String email;
    private Role role;

    private Set<Ticket> bookedTickets = new HashSet<>();

    public User(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
    }

    public User(int id, String name, Gender gender) {
        this.id = id;
        this.name = name;
        this.gender = gender;
    }

    public User(int id, String name, String email, Set<Ticket> bookedTickets) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.bookedTickets = bookedTickets;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<Ticket> getBookedTickets() {
        return bookedTickets;
    }

    public void addTicket(Ticket ticket) {
        if (ticket != null && !bookedTickets.contains(ticket)) {
            bookedTickets.add(ticket);
            Logger.info("Add one ticket: " + ticket + " to user: " + this.toString());
            ticket.setUser(this);
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                ", gender=" + gender +
                ", role=" + role +
                ", bookedTickets=" + bookedTickets +
                '}';
    }
}
