package net.lelyak.edu.tests.util;


import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;

import static org.testng.Assert.assertTrue;


public class RandomDateTest {

    @Test
    public void testRandomDate() throws ParseException {
        Random rnd = new Random();

        DateFormat idf = new SimpleDateFormat("YYYY.MM.DD"),
                odf = new SimpleDateFormat("YYYY.MM.DD HH:MM");

        RandomDate rd = new RandomDate(idf.parse("1960.3.23"), idf.parse("2054.12.31"));
        for (int i = 0; i < rnd.nextInt(100); i++) {
            System.out.format("%s", odf.format(rd.get()));
        }
        assertTrue(true);
    }

}
