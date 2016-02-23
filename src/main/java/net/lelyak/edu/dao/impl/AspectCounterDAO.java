package net.lelyak.edu.dao.impl;

import net.lelyak.edu.dao.IGenericDao;
import net.lelyak.edu.dao.NamedParameterJdbcDaoImpl;
import net.lelyak.edu.entity.AspectModel;

import java.util.List;

public class AspectCounterDAO extends NamedParameterJdbcDaoImpl implements IGenericDao<AspectModel, Integer> {

    @Override
    public Integer save(AspectModel model) {
//        new MapSqlParameterSource("id", model.get)
        return null;
    }

    @Override
    public AspectModel getById(Integer id) {
        return null;
    }

    @Override
    public void update(AspectModel model) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<AspectModel> getAll() {
        return null;
    }

    @Override
    public int getTotalCount() {
        return 0;
    }
}
