package net.lelyak.edu.entity;

import net.lelyak.edu.utils.InjectRandomData;
import net.lelyak.edu.utils.RandomType;

import java.util.Date;
import java.util.Objects;

public class User {

    @InjectRandomData(type = RandomType.NUMERIC, min = 3, max = 5)
    private Integer id;

    @InjectRandomData(type = RandomType.FIRST_NAME)
    private String name;

    @InjectRandomData(type = RandomType.BIRTH_DATE)
    private Date birthday;

    @InjectRandomData(type = RandomType.EMAIL)
    private String email;

    @InjectRandomData(type = RandomType.ROLE)
    private String role;

    public User() {
    }

    private User(String name) {
        this.name = name;
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void addTicket(Ticket ticket) {
        ticket.setUser(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(birthday, user.birthday) &&
                Objects.equals(email, user.email) &&
                Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        if (this.id != null) {
            return this.id;
        }
        return Objects.hash(name, birthday, email, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                ", role=" + role +
                '}';
    }
}
