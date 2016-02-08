package net.lelyak.edu.service;

import net.lelyak.edu.dao.mock.DatabaseMock;
import net.lelyak.edu.entity.Auditorium;

import java.util.Map;

/**
 * AuditoriumService - Returns info about auditoriums and places
 *
 * Since auditorium information is usually static, store it in some property file. The information that needs to be stored is:
 * name
 * number of seats
 * vip seats (comma-separated list of expensive seats)
 * Several auditoriums can be stored in separate property files, information from them could be injected into the AuditoriumService
 *
 * getAuditoriums(), getSeatsNumber(), getVipSeats()
 */
public class AuditoriumService {

    private final Map<Integer, Auditorium> auditoriums = DatabaseMock.getAuditoriums();

    public AuditoriumService() {
        auditoriums.put(1, new Auditorium(1, "blue hall", 120, 20));
        auditoriums.put(2, new Auditorium(2, "yellow hall", 150, 40));
        auditoriums.put(3, new Auditorium(3, "grey hall", 200, 60));
    }


}
