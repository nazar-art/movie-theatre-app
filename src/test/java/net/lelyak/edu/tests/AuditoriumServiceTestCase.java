package net.lelyak.edu.tests;

import net.lelyak.edu.BaseTest;
import net.lelyak.edu.entity.Auditorium;
import net.lelyak.edu.utils.CommonIndexes;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

import static org.testng.Assert.assertNotNull;

public class AuditoriumServiceTestCase extends BaseTest {

    @Test
    public void testGetAuditoriums() throws Exception {
        List<Auditorium> auditoriums = auditoriumService.getAuditoriums();
        assertNotNull(auditoriums);

        Auditorium auditorium = auditoriums.get(CommonIndexes.ONE.getIndex());

        int seatsNumber = auditoriumService.getSeatsNumber(auditorium);
        assertNotNull(seatsNumber);

        Set<Integer> vipSeats = auditoriumService.getVipSeats(auditorium);
        assertNotNull(vipSeats);
    }


}
