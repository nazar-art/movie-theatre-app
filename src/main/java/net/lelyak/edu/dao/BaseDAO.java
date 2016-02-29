package net.lelyak.edu.dao;

import net.lelyak.edu.entity.BaseEntity;
import net.lelyak.edu.utils.Logger;
import net.lelyak.edu.utils.SQLStatements;
import net.lelyak.edu.utils.StringUtilities;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.List;

public abstract class BaseDAO<ENTITY extends BaseEntity> extends NamedParameterJdbcDaoSupport implements IGenericDao<ENTITY> {

    private final String tableName;
    private final Class<ENTITY> entityClass;
    private final List<String> fields;
    private final String insertSQL;
    private final String updateSQL;

    public BaseDAO(Class<ENTITY> entityClass, String tableName, List<String> fields) {
        this.entityClass = entityClass;
        this.tableName = tableName;
        this.fields = fields;

        // init SQLs
        StringBuilder sbInsertSQL = new StringBuilder();
        StringBuilder sbUpdateSQL = new StringBuilder();

        sbInsertSQL.append("INSERT INTO ").append(tableName).append(" (");
        sbUpdateSQL.append("UPDATE ").append(tableName).append(" SET ");

        for (int i = 0; i < fields.size(); i++) {
            if (i > 0) {
                sbInsertSQL.append(", ");
                sbUpdateSQL.append(", ");
            }
            sbInsertSQL.append(fields.get(i));
            sbUpdateSQL.append(fields.get(i)).append("=:").append(fields.get(i));
        }
        sbInsertSQL.append(") ").append("VALUES (");
        for (int i = 0; i < fields.size(); i++) {
            if (i > 0) {
                sbInsertSQL.append(",");
            }
            sbInsertSQL.append(":").append(fields.get(i));
        }

        sbInsertSQL.append(")\n");
        sbUpdateSQL.append(" WHERE id=:id\n");

        this.insertSQL = sbInsertSQL.toString();
        this.updateSQL = sbUpdateSQL.toString();
        Logger.debug("BaseDAO(), insertSQL: [" + insertSQL + "]");
        Logger.debug("BaseDAO(), updateSQL: [" + updateSQL + "]");
    }

    /*@Autowired
    private DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }*/

    @Override
    public Integer save(ENTITY entity) {
        if (entity.getId() == null) {
            insert(entity);
        } else {
            update(entity);
        }
        return null;
    }

    @Override
    public ENTITY getById(long id) {
        ENTITY entity = null;
        if (exists(id)) {
//            String sql = SQLStatements.SELECT_FROM_TABLE + tableName + " WHERE id=:id";
            String sql = StringUtilities.appendStrings(SQLStatements.SELECT_FROM_TABLE_BY_ID, tableName);
            SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
            Logger.debug("QUERY is: " + sql);
            entity = getNamedParameterJdbcTemplate()
                    .queryForObject(sql, parameterSource, new BeanPropertyRowMapper<>(entityClass));
        }
        return entity;
    }

    @Override
    public ENTITY getByName(String name) {
        ENTITY entity = null;
        if (exists(name)) {
            String sql = StringUtilities.buildString(SQLStatements.SELECT_FROM_TABLE_BY_NAME, tableName);
            MapSqlParameterSource parameters = new MapSqlParameterSource("name", name);
            entity = getNamedParameterJdbcTemplate().queryForObject(sql, parameters,
                    new BeanPropertyRowMapper<>(entityClass));
            Logger.debug("getByName( " + name + " ) : " + entity.toString());
        }
        return entity;
    }

    @Override
    public void update(ENTITY entity) {
        getNamedParameterJdbcTemplate().update(updateSQL, new BeanPropertySqlParameterSource(entity));
        Logger.debug("Update entity: " + entity);
    }

    @Override
    public void delete(ENTITY entity) {
//        String sql = SQLStatements.DELETE_FROM_TABLE + tableName + " WHERE id=:id";
        String sql = StringUtilities.buildString(SQLStatements.DELETE_FROM_TABLE, tableName);
        Logger.debug("QUERY is: " + sql);
        MapSqlParameterSource parameterSource = new MapSqlParameterSource("id", entity.getId());

        int rowCount = getNamedParameterJdbcTemplate().update(sql, parameterSource);

        Logger.debug(StringUtilities
                .buildString("Delete %d %s rows deleted: %d", entity.getId(), entity.getName(), rowCount));
    }

    @Override
    public List<ENTITY> getAll() {
        String sql = StringUtilities.buildString(SQLStatements.SELECT_FROM_TABLE, tableName);
        List<ENTITY> entities = getNamedParameterJdbcTemplate().query(sql, new BeanPropertyRowMapper<>(entityClass));
        Logger.debug("getAll(): " + entities.size());
        return entities;
    }

    @Override
    public int getTotalCount() {
        String sql = StringUtilities.buildString(SQLStatements.TOTAL_COUNT_FROM_TABLE, tableName);
        int rowCount = getJdbcTemplate().queryForInt(sql, new BeanPropertyRowMapper<>(entityClass));
        Logger.debug(StringUtilities.buildString("Total table count: %d for table: %s", rowCount, tableName));
        return rowCount;
    }


    private long insert(ENTITY entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        getNamedParameterJdbcTemplate().update(insertSQL, new BeanPropertySqlParameterSource(entity), keyHolder);
        entity.setId(keyHolder.getKey().longValue());
        Logger.debug("Insert user: " + entity.toString());
        return entity.getId();
    }

    private boolean exists(long id) {
        String sql = SQLStatements.SELECT_FROM_TABLE + tableName + " where id=:id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        Long rowCount = getNamedParameterJdbcTemplate().queryForObject(sql, parameterSource, Long.class);
        return rowCount > 0;
    }

    private boolean exists(String name) {
        String sql = SQLStatements.SELECT_FROM_TABLE + tableName + " where name=:name";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource("name", name);
        Long rowCount = getNamedParameterJdbcTemplate().queryForObject(sql, parameterSource, Long.class);
        return rowCount > 0;
    }




}
