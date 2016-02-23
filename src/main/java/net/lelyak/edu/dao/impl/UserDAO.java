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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDAO extends NamedParameterJdbcDaoImpl implements IGenericDao<User, Integer> {

    @Override
    public Integer save(User entity) {
        SqlParameterSource parameterSource =
                new MapSqlParameterSource("id", entity.getId())
                .addValue("name", entity.getName())
                .addValue("birthday", entity.getBirthday())
                .addValue("email", entity.getEmail())
                .addValue("role", entity.getRole());

        Logger.info("Save user: " + entity);
        return getNamedParameterJdbcTemplate()
                .update(SQLStatements.INSERT_INTO_USERS, parameterSource);
    }

    @Override
    public User getById(Integer id) {
        Logger.debug("Looking for user with id: " + id);
        String sql = SQLStatements.SELECT_FROM_USERS + " WHERE user_id=:id ";

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
                .addValue("role", entity.getRole());

        getNamedParameterJdbcTemplate()
                .update(SQLStatements.UPDATE_USERS, parameterSource);

        Logger.info("Update User: " + entity.getName());
    }

    @Override
    public void delete(Integer id) {
        String sql = SQLStatements.DELETE_FROM_USERS + " WHERE user_id=:id ";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);

        Logger.info("Delete following User: " + getById(id));

        getNamedParameterJdbcTemplate()
                .update(sql, parameterSource);
    }

    @Override
    public List<User> getAll() {
        return getNamedParameterJdbcTemplate()
                .query(SQLStatements.SELECT_FROM_USERS, new UserMapper());
    }

    public User getByName(String name) {
        String sql = SQLStatements.SELECT_FROM_USERS + " WHERE user_name=:name";
        SqlParameterSource parameterSource = new MapSqlParameterSource("name", name);
        return getNamedParameterJdbcTemplate()
                .queryForObject(sql, parameterSource, new UserMapper());
    }

    @Override
    public int getTotalCount() {
        int total = getJdbcTemplate().queryForInt(SQLStatements.TOTAL_COUNT_FROM_USERS);
        Logger.info("Total Users count: " + total);
        return total;
    }

    public User getByEmail(String email) {
        String sql = SQLStatements.SELECT_FROM_USERS + " WHERE user_email=:email";
        SqlParameterSource parameterSource = new MapSqlParameterSource("email", email);
        return getNamedParameterJdbcTemplate()
                .queryForObject(sql, parameterSource, new UserMapper());
    }


    private final class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();

            user.setId(rs.getInt("user_id"));
            user.setName(rs.getString("user_name"));
            user.setBirthday(rs.getDate("user_birthday"));
            user.setEmail(rs.getString("user_email"));
            user.setRole(rs.getString("user_role"));

            return user;
        }
    }
}
