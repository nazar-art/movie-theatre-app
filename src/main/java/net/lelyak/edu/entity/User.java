package net.lelyak.edu.entity;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class User {
    private Integer id;
    private String firstName;
    private String lastName;
    private Gender gender;
    private Calendar birthday;

    private String email;
    private String password;

    private Role role;

    private Set<Ticket> bookedTickets = new HashSet<>();

    public User() {
    }

    public User(String firstName, String lastName, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    public User(int id, String firstName, String lastName, Gender gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    public User(int id, String firstName, String lastName, String email, Set<Ticket> bookedTickets) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.bookedTickets = bookedTickets;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
            ticket.setUser(this);
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", birthday=" + birthday +
                ", gender=" + gender +
                ", role=" + role +
                ", bookedTickets=" + bookedTickets +
                '}';
    }
}
