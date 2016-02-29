package net.lelyak.edu.dao.impl;

import net.lelyak.edu.dao.BaseDAO;
import net.lelyak.edu.entity.Event;

import java.util.Arrays;

public class EventDAO extends BaseDAO<Event> {

    public static final String EVENT_TABLE_NAME = "t_event";
    public static final String eventFields[] = {"name", "price", "rating", "airDate"};

    public EventDAO() {
        super(Event.class, EVENT_TABLE_NAME, Arrays.asList(eventFields));
    }

    /*@Override
    public Integer save(Event event) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", event.getId())
                .addValue("name", event.getName())
                .addValue("price", event.getPrice())
                .addValue("rating", event.getEventRating().toString())
                .addValue("date", event.getAirDate());

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
                .addValue("date", event.getAirDate());

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
        List<Event> events = getNamedParameterJdbcTemplate()
                .query(SQLStatements.SELECT_FROM_EVENTS, new EventMapper());
        Logger.debug("Get all events: " + events);
        return events;
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

            event.setId(rs.getInt("event_id"));
            event.setName(rs.getString("event_name"));
            event.setPrice(rs.getDouble("event_price"));
            event.setEventRating(EventRating.valueOf(eventRating.toUpperCase()));
            event.setAirDate(rs.getDate("event_date"));

            return event;
        }
    }*/
}

