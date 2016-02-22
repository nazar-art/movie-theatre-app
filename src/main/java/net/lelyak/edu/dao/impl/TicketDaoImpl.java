package net.lelyak.edu.dao.impl;

import net.lelyak.edu.dao.IGenericDao;
import net.lelyak.edu.dao.NamedParameterJdbcDaoImpl;
import net.lelyak.edu.entity.Event;
import net.lelyak.edu.entity.Ticket;
import net.lelyak.edu.entity.User;
import net.lelyak.edu.utils.Logger;
import net.lelyak.edu.utils.SQLStatements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class TicketDaoImpl extends NamedParameterJdbcDaoImpl implements IGenericDao<Ticket, Integer> {

    @Autowired
    private EventDaoImpl eventDao;

    @Autowired
    private UserDaoImpl userDao;

    @Override
    public Integer save(Ticket ticket) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", ticket.getId())
                .addValue("price", ticket.getPrice())
                .addValue("event", ticket.getEvent().getId())
                .addValue("user", ticket.getUser().getId());

        Logger.info("Save ticket: " + ticket);

        if (ticket.getEvent() != null && isTicketPresentedInDB(ticket.getEvent().getId())) {
            eventDao.save(ticket.getEvent());
        }
        if (ticket.getUser() != null && isUserPresentedInDB(ticket.getUser().getId())) {
            userDao.save(ticket.getUser());
        }

        return getNamedParameterJdbcTemplate()
                .update(SQLStatements.INSERT_INTO_TICKETS, parameterSource);
    }

    @Override
    public Ticket getById(Integer id) {
        String sql = SQLStatements.SELECT_FROM_TICKETS + " WHERE tick_id=:id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        return getNamedParameterJdbcTemplate().queryForObject(sql, parameterSource, new TicketMapper());
    }

    @Override
    public void update(Ticket ticket) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", ticket.getId())
                .addValue("price", ticket.getPrice())
                .addValue("event", ticket.getEvent().getId())
                .addValue("user", ticket.getUser().getId());

        getNamedParameterJdbcTemplate().update(SQLStatements.UPDATE_TICKETS, parameterSource);
        Logger.info("Update Ticket: " + ticket);
    }

    @Override
    public void delete(Integer id) {
        String sql = SQLStatements.DELETE_FROM_TICKETS + " WHERE tick_id=:id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        Logger.info("Delete following Ticket: " + getById(id));
        getNamedParameterJdbcTemplate().update(sql, parameterSource);
    }

    @Override
    public List<Ticket> getAll() {
        return getNamedParameterJdbcTemplate()
                .query(SQLStatements.SELECT_FROM_TICKETS, new TicketMapper());
    }

    @Override
    public int getTotalCount() {
        return getJdbcTemplate()
                .queryForInt(SQLStatements.TOTAL_COUNT_FROM_TICKETS);
    }

    public List<Ticket> getByEventId(Integer eventId) {
        return getAll().stream()
                .filter(t -> t.getEvent() != null && Objects.equals(t.getEvent().getId(), eventId))
                .collect(Collectors.toList());
    }

    public List<Ticket> getByUserId(Integer userId) {
        Logger.info("All tickets: " + getAll());

        List<Ticket> tickets = getAll().stream()
                .filter(t -> t.getUser() != null && Objects.equals(t.getUser().getId(), userId))
                .collect(Collectors.toList());
        Logger.info("All user tickets: " + tickets);
        return tickets;
    }

    private boolean isTicketPresentedInDB(Integer id) {
        boolean result;
        try {
            Event event = eventDao.getById(id);
            result = true;
            Logger.debug("Event is presented at DB - " + event);

        } catch (EmptyResultDataAccessException ignore) {
            Logger.debug(String.format("Event with id: %s is not presented at DB", id));
            result = false;
        }
        return result;
    }

    private boolean isUserPresentedInDB(Integer id) {
        boolean result;
        try {
            User user = userDao.getById(id);
            result = true;
            Logger.debug("User is presented at DB - " + user);

        } catch (EmptyResultDataAccessException ignore) {
            Logger.debug(String.format("User with id: %s is not presented at DB", id));
            result = false;
        }
        return result;
    }

    public final class TicketMapper implements RowMapper<Ticket> {

        @Override
        public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {
            Ticket ticket = new Ticket();

            ticket.setId(rs.getInt("tick_id"));
            ticket.setPrice(rs.getDouble("tick_price"));

            int eventId = rs.getInt("event_id");
            int userId = rs.getInt(rs.getInt("user_id"));
            ticket.setEvent(eventDao.getById(eventId));
            ticket.setUser(userDao.getById(userId));

            return ticket;
        }
    }
}
