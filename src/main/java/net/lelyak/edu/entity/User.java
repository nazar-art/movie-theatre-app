package net.lelyak.edu.entity;

import net.lelyak.edu.utils.datafactory.InjectRandomData;
import net.lelyak.edu.utils.datafactory.RandomType;

import java.util.Date;

public class User extends BaseEntity {

    @InjectRandomData(type = RandomType.BIRTH_DATE)
    private Date birthday;

    @InjectRandomData(type = RandomType.EMAIL)
    private String email;

    private String password;

    @InjectRandomData(type = RandomType.ROLE)
    private String role;

    public User() {
        super();
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
//        String s = super.toString();
        return "User{" + /*s +*/
                "id=" + id +
                ", name=" + name +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                ", role=" + role +
                '}';
    }
}
