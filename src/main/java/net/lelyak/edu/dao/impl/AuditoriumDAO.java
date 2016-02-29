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




    /*@Override
    public Integer save(Auditorium auditorium) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource parameterSource = new MapSqlParameterSource("id", auditorium.getId())
                .addValue("name", auditorium.getName())
                .addValue("seats", auditorium.getSeats())
                .addValue("vip", auditorium.getVip());

        getNamedParameterJdbcTemplate()
                .update(SQLStatements.INSERT_INTO_AUDITORIUMS, new BeanPropertySqlParameterSource(auditorium), keyHolder);
        auditorium.setId(keyHolder.getKey().intValue());

        Logger.debug("Insert auditorium: " + auditorium);
        return auditorium.getId();
    }

    @Override
    public Auditorium getById(Integer id) {
        Logger.debug("Looking for Auditorium with id: " + id);
        String sql = SQLStatements.SELECT_FROM_AUDITORIUMS + " WHERE aud_id=:id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        return getNamedParameterJdbcTemplate()
                .queryForObject(sql, parameterSource, new BeanPropertyRowMapper<>(Auditorium.class));
    }

    @Override
    public void update(Auditorium auditorium) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource("id", auditorium.getId())
                .addValue("name", auditorium.getName())
                .addValue("seats", auditorium.getSeats())
                .addValue("vip", auditorium.getVip());
        getNamedParameterJdbcTemplate().update(SQLStatements.UPDATE_AUDITORIUMS, parameterSource);

        Logger.debug("Update auditorium: " + getById(auditorium.getId()));
    }

    @Override
    public void delete(Integer id) {
        String sql = SQLStatements.DELETE_FROM_AUDITORIUMS + " WHERE aud_id=:id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource("id", id);

        Logger.debug("Delete auditorium: " + getById(id));
        getNamedParameterJdbcTemplate().update(sql, parameterSource);
    }

    @Override
    public List<Auditorium> getAll() {
        List<Auditorium> list = getNamedParameterJdbcTemplate().query(SQLStatements.SELECT_FROM_AUDITORIUMS,
                new BeanPropertyRowMapper<>(Auditorium.class));
        Logger.debug("All auditoriums: " + list);
        return list;
    }

    @Override
    public int getTotalCount() {
        int count = getJdbcTemplate().queryForInt(SQLStatements.TOTAL_COUNT_FROM_AUDITORIUMS);
        Logger.debug("Total auditoriums count: " + count);
        return count;
    }

    public void saveAll(List<Auditorium> auditoriums) {
        int counter = 1;
        for (Auditorium auditorium : auditoriums) {
            if (auditorium.getId() == null) {
                auditorium.setId(counter++);
                save(auditorium);
            }
        }

        *//*auditoriums.stream()
                .filter(auditorium -> auditorium.getId() != null && !exists(auditorium.getId()))
                .forEach(auditorium -> {
                    auditorium.setId(counter++);
            save(auditorium);
            Logger.debug("Auditorium saved: " + auditorium);
        });*//*
    }

    public boolean exists(int id) {
        String sql = SQLStatements.SELECT_FROM_AUDITORIUMS + " WHERE aud_id=:id";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource("id", id);

        Integer count = getNamedParameterJdbcTemplate()
                .queryForObject(sql, parameterSource, Integer.class);
        return count > 0;
    }*/
}
