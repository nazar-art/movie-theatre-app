package net.lelyak.edu.service;

import net.lelyak.edu.dao.mock.DatabaseMock;
import net.lelyak.edu.entity.Auditorium;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
@Service
public class AuditoriumService {

    private final Map<Integer, Auditorium> auditoriums = DatabaseMock.getAuditoriums();

    public AuditoriumService() {
        auditoriums.put(1, new Auditorium(1, "blue hall", 120, Arrays.asList("1", "3", "6", "8")));
        auditoriums.put(2, new Auditorium(2, "yellow hall", 150, Arrays.asList("2", "4", "6", "8")));
        auditoriums.put(3, new Auditorium(3, "grey hall", 200, Arrays.asList("5", "10", "15", "20")));
    }

    public List<Auditorium> getAuditoriums() {
        return auditoriums.values().stream()
                .collect(Collectors.toList());
    }

    public int getSeatsNumber(Auditorium auditorium) {
        return auditoriums.get(auditorium.getId())
                .getNumberOfSeats();
    }

    public List<String> getVipSeats(Auditorium auditorium) {
        return auditoriums.get(auditorium.getId())
                .getVipSeats();
    }
}
