package net.lelyak.edu.dao.impl;

import net.lelyak.edu.dao.BaseDAO;
import net.lelyak.edu.entity.Auditorium;

import java.util.Arrays;

public class AuditoriumDAO extends BaseDAO<Auditorium> {

    private static final String AUDITORIUM_TABLE_NAME = "t_auditorium";
    private static final String auditoriumFields[] = {"name", "seats", "vip"};

    public AuditoriumDAO() {
        super(Auditorium.class, AUDITORIUM_TABLE_NAME, Arrays.asList(auditoriumFields));
    }

}
