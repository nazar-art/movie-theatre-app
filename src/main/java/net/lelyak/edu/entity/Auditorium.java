package net.lelyak.edu.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Auditorium extends BaseEntity {

    private Integer seats;
    private String vip;
//    private boolean isVip;

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

    public boolean isSeatVip(int number) {
        List<Integer> vipSeats = processVipPlaces();
//        Integer seatNumber = Integer.valueOf(this.name);
//        Logger.debug("Seat number is: " + seatNumber);
        return vipSeats.contains(number);
    }

    private List<Integer> processVipPlaces() {
        StringTokenizer tokenizer = new StringTokenizer(vip, ",");
        List<Integer> vipList = new ArrayList<>();
        while (tokenizer.hasMoreTokens()) {
            String vip = tokenizer.nextToken();
            vipList.add(Integer.valueOf(vip));
        }
        return vipList;
    }

    /*public boolean isVip() {
        return isSeatVip();
    }

    public void setVip(boolean vip) {
        isVip = vip;
    }*/

    @Override
    public String toString() {
        return "Auditorium{" +
                ", seats=" + seats +
                ", vip=" + vip +
                '}';
    }
}
