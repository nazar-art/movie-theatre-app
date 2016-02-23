package net.lelyak.edu.dao.impl;

import net.lelyak.edu.dao.IGenericDao;
import net.lelyak.edu.dao.NamedParameterJdbcDaoImpl;
import net.lelyak.edu.entity.Ticket;
import net.lelyak.edu.utils.Logger;
import net.lelyak.edu.utils.SQLStatements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
public class TicketDAO extends NamedParameterJdbcDaoImpl implements IGenericDao<Ticket, Integer> {

    @Autowired
    private EventDAO eventDao;

    @Autowired
    private UserDAO userDao;

    @Override
    public Integer save(Ticket ticket) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", ticket.getId())
                .addValue("price", ticket.getPrice())
                .addValue("event", ticket.getEvent())
                .addValue("user", ticket.getUser());

        Logger.info("Save ticket: " + ticket);

        return getNamedParameterJdbcTemplate()
                .update(SQLStatements.INSERT_INTO_TICKETS, parameterSource);
    }

    @Override
    public Ticket getById(Integer id) {
        String sql = SQLStatements.SELECT_FROM_TICKETS + " WHERE tick_id=:id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
//        return getNamedParameterJdbcTemplate().queryForObject(sql, parameterSource, new TicketMapper());
        return getNamedParameterJdbcTemplate()
                .queryForObject(sql, parameterSource, new BeanPropertyRowMapper<>(Ticket.class));
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
        List<Ticket> tickets = getNamedParameterJdbcTemplate()
//                .query(SQLStatements.SELECT_FROM_TICKETS, new TicketMapper());
                .query(SQLStatements.SELECT_FROM_TICKETS, new BeanPropertyRowMapper<>(Ticket.class));
        Logger.debug("Get all tickets: " + tickets);
        return tickets;
    }

    @Override
    public int getTotalCount() {
        int count = getJdbcTemplate()
                .queryForInt(SQLStatements.TOTAL_COUNT_FROM_TICKETS);
        Logger.debug("Total tickets count: " + count);
        return count;
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
