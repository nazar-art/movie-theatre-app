package net.lelyak.edu.service;

import net.lelyak.edu.entity.Auditorium;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
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

    private List<Auditorium> auditoriums;

    @Autowired
    public AuditoriumService(List<Auditorium> auditoriums) {
        this.auditoriums = auditoriums;
    }

    public List<Auditorium> getAuditoriums() {
        return auditoriums.stream()
                .collect(Collectors.toList());
    }

    public int getSeatsNumber(Auditorium auditorium) {
        return auditorium.getNumberOfSeats();
    }

    public Set<Integer> getVipSeats(Auditorium auditorium) {
        return auditorium.getVipSeats();
    }
}
