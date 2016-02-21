package net.lelyak.edu.dao.impl;

import net.lelyak.edu.dao.IGenericDao;
import net.lelyak.edu.dao.NamedParameterJdbcDaoImpl;
import net.lelyak.edu.entity.User;
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
public class UserDaoImpl extends NamedParameterJdbcDaoImpl implements IGenericDao<User, Integer> {

    @Override
    public Integer create(User entity) {
        SqlParameterSource parameterSource =
                new MapSqlParameterSource("id", entity.getId())
                .addValue("name", entity.getName())
                .addValue("birthday", entity.getBirthday())
                .addValue("email", entity.getEmail())
                .addValue("role", entity.getRole())
                .addValue("tickets", entity.getBookedTickets().toString());

        Logger.info("Create user: " + entity);
        return getNamedParameterJdbcTemplate()
                .update(SQLStatements.INSERT_INTO_USERS, parameterSource);
    }

    @Override
    public User read(Integer id) {
        Logger.debug("Looking for user with id: " + id);
        String sql = SQLStatements.SELECT_FROM_USERS + " WHERE user_id=:id";
        SqlParameterSource paramSource = new MapSqlParameterSource("id", id);
        return getNamedParameterJdbcTemplate()
                .queryForObject(sql, paramSource, new UserMapper());
    }

    @Override
    public void update(User entity) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", entity.getId())
                .addValue("name", entity.getName())
                .addValue("birthday", entity.getBirthday())
                .addValue("email", entity.getEmail())
                .addValue("role", entity.getRole())
                .addValue("tickets", entity.getBookedTickets());

        getNamedParameterJdbcTemplate()
                .update(SQLStatements.UPDATE_USERS, parameterSource);

        Logger.info("Update User: " + entity.getName());
    }

    @Override
    public void delete(Integer id) {
        String sql = SQLStatements.DELETE_FROM_USERS + " WHERE user_id=:id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        getNamedParameterJdbcTemplate().update(sql, parameterSource);
        Logger.info("Delete User with id: " + id);
    }

    @Override
    public List<User> getAll() {
        return getNamedParameterJdbcTemplate()
                .query(SQLStatements.SELECT_FROM_USERS, new UserMapper());
    }

    public int totalUsersCount() {
        int total = getJdbcTemplate().queryForInt(SQLStatements.TOTAL_COUNT_FROM_USERS);
        Logger.info("Total Users count: " + total);
        return total;
    }

    // todo add findByName(), findByEmail()

    public User findByName(String name) {
        String sql = SQLStatements.SELECT_FROM_USERS + "WHERE user_name=:name";
        SqlParameterSource parameterSource = new MapSqlParameterSource("name", name);
        return getNamedParameterJdbcTemplate()
                .queryForObject(sql, parameterSource, new UserMapper());
    }

    public User findByEmail(String email) {
        String sql = SQLStatements.SELECT_FROM_USERS + "WHERE user_email=:email";
        SqlParameterSource parameterSource = new MapSqlParameterSource("email", email);
        return getNamedParameterJdbcTemplate()
                .queryForObject(sql, parameterSource, new UserMapper());
    }



    private final class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            Calendar calendar = Calendar.getInstance();

            user.setId(rs.getInt("user_id"));
            user.setName(rs.getString("user_name"));
            Date userBirthday = rs.getDate("user_birthday");
            calendar.setTime(userBirthday);
            user.setBirthday(calendar);
            user.setEmail(rs.getString("user_email"));
            user.setRole(rs.getString("user_role"));
            // todo find solution for this case
//            user.addTicket(rs.getString("user_tickets"));
            return user;
        }
    }
}
