package net.lelyak.edu.dao.impl;

import net.lelyak.edu.dao.IGenericDao;
import net.lelyak.edu.dao.NamedParameterJdbcDaoImpl;
import net.lelyak.edu.entity.Event;
import net.lelyak.edu.entity.EventRating;
import net.lelyak.edu.utils.Logger;
import net.lelyak.edu.utils.SQLStatements;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

@Repository
public class EventDaoImpl extends NamedParameterJdbcDaoImpl implements IGenericDao<Event, Integer> {

    @Override
    public Integer save(Event event) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", event.getId())
                .addValue("name", event.getName())
                .addValue("price", event.getPrice())
                .addValue("rating", event.getEventRating().getName().toUpperCase())
                .addValue("date", event.getEventDateTime().toString());

        Logger.info("Save event: " + event);
        return getNamedParameterJdbcTemplate()
                .update(SQLStatements.INSERT_INTO_EVENTS, parameterSource);
    }

    @Override
    public Event getById(Integer id) {
        String sql = SQLStatements.SELECT_FROM_EVENTS + " WHERE event_id=:id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        return getNamedParameterJdbcTemplate()
                .queryForObject(sql, parameterSource, new EventMapper());
    }

    @Override
    public void update(Event event) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", event.getId())
                .addValue("name", event.getName())
                .addValue("price", event.getPrice())
                .addValue("rating", event.getEventRating())
                .addValue("date", event.getEventDateTime());

        getNamedParameterJdbcTemplate().update(SQLStatements.UPDATE_EVENTS, parameterSource);
        Logger.info("Update Event: " + event.getName());
    }

    @Override
    public void delete(Integer id) {
        String sql = SQLStatements.DELETE_FROM_EVENTS + " WHERE event_id=:id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);

        Logger.info("Delete following event: " + getById(id));

        getNamedParameterJdbcTemplate()
                .update(sql, parameterSource);
    }

    @Override
    public List<Event> getAll() {
        return getNamedParameterJdbcTemplate()
                .query(SQLStatements.SELECT_FROM_EVENTS, new EventMapper());
    }

    @Override
    public int getTotalCount() {
        int total = getJdbcTemplate().queryForInt(SQLStatements.TOTAL_COUNT_FROM_EVENTS);
        Logger.info("Total count for Events: " + total);
        return total;
    }

    public Event getByName(String name) {
        String sql = SQLStatements.SELECT_FROM_EVENTS + " where event_name=:name";
        SqlParameterSource parameterSource = new MapSqlParameterSource("name", name);
        return getNamedParameterJdbcTemplate()
                .queryForObject(sql, parameterSource, new EventMapper());
    }


    private final class EventMapper implements RowMapper<Event> {
        @Override
        public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
            Event event = new Event();
            String eventRating = rs.getString("event_rating");
            Calendar calendar = Calendar.getInstance();
            Date eventDate = rs.getDate("event_date"); // todo configure Set<Date>, coz it is saved like String
            calendar.setTime(eventDate);

            event.setId(rs.getInt("event_id"));
            event.setName(rs.getString("event_name"));
            event.setPrice(rs.getDouble("event_price"));
            event.setEventRating(EventRating.valueOf(eventRating.toUpperCase()));
            event.setEventDateTime(calendar);

            return null;
        }
    }
}

